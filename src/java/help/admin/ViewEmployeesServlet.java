package help.admin;

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

/**
 *
 * @author jmarc
 */
@WebServlet(name = "ViewEmployeesServlet", urlPatterns = {"/ViewEmployeesServlet"})
public class ViewEmployeesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // set url
        String url = "/admin/view_employees.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "view_employees";
        }
        
        // show all employees
        if (action.equals("view_employees")) {
            
            // get employees
            List<Employee> employees = EmployeeDB.getEmployees();
            request.setAttribute("employees", employees);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
