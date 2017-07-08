/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.request;

import help.login.Employee;
import help.login.EmployeeDB;
import help.login.Technician;
import help.login.TechnicianDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jmarc
 */
@WebServlet(name = "TechRequestServlet", urlPatterns = {"/TechRequestServlet"})
public class TechRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/tech_view_request.jsp";
        
        //String employeeId = employee.getEmployeeId();
        String employeeId = "";
        
        String techName = "";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null)
        {
            // default action
            action = "open_requests";
        } 
        
        // show all assigned opened tickets
        if (action.equals("open_requests")) {
            
             // create session object to hold session
            HttpSession session = request.getSession();
            Technician tech = (Technician) session.getAttribute("tech");
            
            techName = tech.getFirstName() + " " + tech.getLastName();
            tech = TechnicianDB.getTechnicianbyID(employeeId);

            request.setAttribute("tech", tech);
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectOpenTechRequests(techName);
            request.setAttribute("serviceRequests", serviceRequests);
        }
        
        // show all assigned closed tickets
        else if (action.equals("closed_requests")) {
            
            url = "/tech_closed_request.jsp";
            
             // create session object to hold session
            HttpSession session = request.getSession();
            Technician tech = (Technician) session.getAttribute("tech");
            
            techName = tech.getFirstName() + " " + tech.getLastName();
            tech = TechnicianDB.getTechnicianbyID(employeeId);

            request.setAttribute("tech", tech);
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectClosedTechRequests(techName);
            request.setAttribute("serviceRequests", serviceRequests);
        }
        
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
