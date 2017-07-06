package help.request;

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
        
        // assign request
        else if (action.equals("assign_request")) {
            
            // get values from form
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String requestStatus = request.getParameter("requestStatus");
            //String requestedBy = request.getParameter("requestedBy");
            String contactInfo = request.getParameter("contactInfo");
            String requestDate = request.getParameter("requestDate");
            String description = request.getParameter("description");
            String technician = request.getParameter("technician");
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
        else if (action.equals("close_request")) {
            
            // get the request
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            Request serviceRequest = RequestDB.selectRequest(requestId);
            
            String message;
            
            if (serviceRequest.getTechnician().isEmpty() ||
                serviceRequest.getTechnician() == null) {
                
                // display error message
                message = "Ticket has not been assigned";
                url = "/open_requests.jsp";
            }
            
            else {
                
                // no error
                message = "";
                request.setAttribute("message", message);
                
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
