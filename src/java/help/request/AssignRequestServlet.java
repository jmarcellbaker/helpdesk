package help.request;

import help.login.Technician;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jmarc
 */

public class AssignRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // create session object to hold session
        HttpSession session = request.getSession();
        
        // get tech from session
        Technician tech = (Technician) session.getAttribute("tech");
        
        // set tech attribute
        request.setAttribute("tech", tech);
        
        // set technician
        String technician = tech.getFirstName() + " " + tech.getLastName();
        
        String url = "/open_requests.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            
            // default action
            action = "display_requests";
        }
        
        // show all open unassigned tickets
        if (action.equals("display_requests")) {
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectRequests();
            request.setAttribute("serviceRequests", serviceRequests);
        }
        
        // show selected ticket
        else if (action.equals("display_request")) {
            
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            Request serviceRequest = RequestDB.selectRequest(requestId);
            session.setAttribute("serviceRequest", serviceRequest);
            url = "/request.jsp";
        }

        // show selected ticket
        else if (action.equals("addNotes")) {
            
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            Request serviceRequest = RequestDB.selectRequest(requestId);
            session.setAttribute("serviceRequest", serviceRequest);
            url = "/tech_addnotes.jsp";
        }
        
        // save notes
        else if (action.equals("notes_request")) {
            
            // get values
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String requestStatus = request.getParameter("requestStatus");
            //String requestedBy = request.getParameter("requestedBy");
            String contactInfo = request.getParameter("contactInfo");
            String requestDate = request.getParameter("requestDate");
            String description = request.getParameter("description");
            String completionDate = "";
            String notes = request.getParameter("notes");
            
            // Pull the request and update it
            Request serviceRequest = (Request) session.getAttribute("serviceRequest");
            serviceRequest.setRequestId(requestId);
            serviceRequest.setRequestStatus(requestStatus);
            //serviceRequest.setRequestedBy(requestedBy);
            serviceRequest.setContactInfo(contactInfo);
            serviceRequest.setRequestDate(requestDate);
            serviceRequest.setDescription(description);
            serviceRequest.setTechnician(technician);
            serviceRequest.setCompletionDate(completionDate);
            serviceRequest.setNotes(notes);
            RequestDB.update(serviceRequest);

            // Display updated list of requests
            List<Request> serviceRequests = RequestDB.selectRequests();
            request.setAttribute("serviceRequests", serviceRequests);
            
            url = "/TechRequestServlet?action=open_requests&amp;techName=${tech.firstName} ${tech.lastName}";
        }
        
        // assign request
        else if (action.equals("assign_request")) {
            
            // get values
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String requestStatus = request.getParameter("requestStatus");
            //String requestedBy = request.getParameter("requestedBy");
            String contactInfo = request.getParameter("contactInfo");
            String requestDate = request.getParameter("requestDate");
            String description = request.getParameter("description");
            String completionDate = "";
            String notes = request.getParameter("notes");
            
            // Pull the request and update it
            Request serviceRequest = (Request) session.getAttribute("serviceRequest");
            serviceRequest.setRequestId(requestId);
            serviceRequest.setRequestStatus(requestStatus);
            //serviceRequest.setRequestedBy(requestedBy);
            serviceRequest.setContactInfo(contactInfo);
            serviceRequest.setRequestDate(requestDate);
            serviceRequest.setDescription(description);
            serviceRequest.setTechnician(technician);
            serviceRequest.setCompletionDate(completionDate);
            serviceRequest.setNotes(notes);
            RequestDB.update(serviceRequest);

            // Display updated list of requests
            List<Request> serviceRequests = RequestDB.selectRequests();
            request.setAttribute("serviceRequests", serviceRequests);
        }
        
        // close request
        else if (action.equals("close")) {
            
            // get the request
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            Request serviceRequest = RequestDB.selectRequest(requestId);
            session.setAttribute("serviceRequest", serviceRequest);
            url = "/tech_close.jsp";
 
            // set close date
            String date = new SimpleDateFormat("MM.dd.yyyy").format(new Date());
            serviceRequest.setCompletionDate(date);

            // change status to closed
            String closed = "CLOSED";
            serviceRequest.setRequestStatus(closed);

            // update request
            RequestDB.update(serviceRequest);

            // Display list of request
            List<Request> serviceRequests = RequestDB.selectRequests();
            request.setAttribute("serviceRequests", serviceRequests);

        }
        
        else if (action.equals("close_request")) {
            
            // get values
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            //String requestedBy = request.getParameter("requestedBy");
            String contactInfo = request.getParameter("contactInfo");
            String requestDate = request.getParameter("requestDate");
            String description = request.getParameter("description");
            String notes = request.getParameter("notes");
            
            // Pull the request and update it
            Request serviceRequest = (Request) session.getAttribute("serviceRequest");
            serviceRequest.setRequestId(requestId);
            //serviceRequest.setRequestedBy(requestedBy);
            serviceRequest.setContactInfo(contactInfo);
            serviceRequest.setRequestDate(requestDate);
            serviceRequest.setDescription(description);
            serviceRequest.setTechnician(technician);
            serviceRequest.setNotes(notes);
            RequestDB.update(serviceRequest);
            
            // set close date
            String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
            serviceRequest.setCompletionDate(date);

            // change status to closed
            String closed = "CLOSED";
            serviceRequest.setRequestStatus(closed);

            // update request
            RequestDB.update(serviceRequest);

            // Display updated list of requests
            List<Request> serviceRequests = RequestDB.selectRequests();
            request.setAttribute("serviceRequests", serviceRequests);
            
            url = "/TechRequestServlet?action=closed_requests&amp;techName=${tech.firstName} ${tech.lastName}";
            
            
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
 

}
