/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.request;

import help.login.Employee;
import help.login.EmployeeDB;
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
@WebServlet(name = "EmployeeRequestServlet", urlPatterns = {"/EmployeeRequestServlet"})
public class EmployeeRequestServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/employee_view_request.jsp";
        
        //String employeeId = employee.getEmployeeId();
        String employeeId = "";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null)
        {
            // default action
            action = "open_requests";
        } 
        
        // show all employee opened tickets
        if (action.equals("open_requests")) {
            
             // create session object to hold session
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employee");
            
            employeeId = employee.getEmployeeId();
            
            employee = EmployeeDB.getEmployeebyID(employeeId);

            request.setAttribute("employee", employee);
            
            // get requests
            //List<Request> serviceRequests = RequestDB.selectOpenEmployeeRequests(employee.getEmployeeId());
            List<Request> serviceRequests = RequestDB.selectOpenEmployeeRequests(employeeId);
            request.setAttribute("serviceRequests", serviceRequests);
        }
        
        // show all employee closed tickets
        else if (action.equals("closed_requests")) {
            
            url = "/employee_closed_request.jsp";
             // create session object to hold session
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employee");
            
            employeeId = employee.getEmployeeId();
            
            employee = EmployeeDB.getEmployeebyID(employeeId);

            request.setAttribute("employee", employee);
            
            // get requests
            List<Request> serviceRequests = RequestDB.selectClosedEmployeeRequests(employeeId);
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
