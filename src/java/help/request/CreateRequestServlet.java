package help.request;

import help.login.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.request.Request;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;


/**
 *
 * @author jmarc
 */
@WebServlet(name = "CreateRequestServlet", urlPatterns = {"createRequest"})
public class CreateRequestServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get user from session
        HttpSession session = request.getSession();
        
        Employee employee = (Employee) session.getAttribute("employee");   
        
        // create variables for request form
        String url = "index.jsp";
        String requestedBy = employee.getFirstName() + " " + employee.getLastName();
        String contactInfo = employee.getEmail();
        String description = "";
        String requestDate = "";
        String requestStatus = "OPEN";
        String notes = "";
        String completionDate = "";
        String technician = "";
        
        // set action to create request
        String action = request.getParameter("action");
        if (action == null) {
            action = "createRequest";
        }
        
        if (action.equals("createRequest")) {
            // get name of person who requested the service
           // requestedBy = request.getParameter("requestedBy");
            
            // get contact info
            //contactInfo = request.getParameter("contactInfo");
            
            // get description of request
            description = request.getParameter("description");
            
            // Create new Service Request
            Request serviceRequest = new Request();
            
            // set requested by value
           serviceRequest.setRequestedBy(requestedBy);
           
           // set contact info
           serviceRequest.setContactInfo(contactInfo);
           
           // set description
           serviceRequest.setDescription(description);
           
           // set status to open
           serviceRequest.setRequestStatus("OPEN");
           
           // set employee
           serviceRequest.setEmployee(employee);
           
           // set tech, notes request date and completion date to blank values
           serviceRequest.setTechnician("");
           serviceRequest.setNotes("");
           serviceRequest.setCompletionDate("");
           serviceRequest.setRequestDate("");
 
           // Create message to display in case fields are not completed
           String message;
           
           
           if (description == null || description.isEmpty()) {
               
               // display error message if form not fully completed
               message = "Please provide a description";
               request.setAttribute("message", message);
               
               // redirect page
               url = "/index.jsp";
           }
           
           else {
               
               // no message to display
               message = "";
               request.setAttribute("message", message);
               
               // redirect to page with submission results
               url = "/success_ticket.jsp";
               
               // set request date
               String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
               serviceRequest.setRequestDate(date);
               
               // insert request into the database
               RequestDB.insert(serviceRequest);

           }
           
           request.setAttribute("serviceRequest", serviceRequest);
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
