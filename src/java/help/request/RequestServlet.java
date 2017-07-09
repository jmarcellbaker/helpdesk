package help.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jmarc
 */
@WebServlet(name = "RequestServlet", urlPatterns = {"/RequestServlet"})
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String url = "";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            
            // default action
            action = "view_open";
        }
        
        // show all open tickets
        if (action.equals("view_open")) {
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectAllOpenRequests();
            request.setAttribute("serviceRequests", serviceRequests);
            url = "/admin/view_open.jsp";
        }
        
        // show all closed tickets
        else if (action.equals("view_closed")) {
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectAllClosedRequests();
            request.setAttribute("serviceRequests", serviceRequests);
            url = "/admin/view_closed.jsp";
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
