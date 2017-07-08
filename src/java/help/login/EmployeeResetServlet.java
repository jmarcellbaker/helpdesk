/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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

public class EmployeeResetServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get  action
        String action = request.getParameter("action");
        if (action == null) {
            action = "employeeReset";
        }
        
        if (action.equals("employeeReset")) {
            
            // get parameters from request
            String employeeId = request.getParameter("employeeId");
            String password = request.getParameter("password");
            String message = "";
            String url = "/employee_reset.jsp";
            
            // create new employee
            Employee employee = new Employee();
            
            // set parameters
            employee.setEmployeeId(employeeId);
            employee.setPassword(password);
            
            HttpSession session = request.getSession();
            
            // Check if employee exist
            if (EmployeeDB.employeeExists(employee.getEmail())) {
                
                employee = EmployeeDB.getEmployeebyID(employeeId);
                employee.setPassword(password);
                
                String hashedPassword;
                String salt = "";

                try {
                        salt = PasswordUtil.getSalt();
                        hashedPassword = PasswordUtil.hashPassword(salt + employee.getPassword());
                    } catch (NoSuchAlgorithmException ex) {
                        hashedPassword = ex.getMessage();
                    }

                // set password and salt
                employee.setPassword(hashedPassword);
                employee.setSalt(salt);
                
                // set Attributes
                session.setAttribute("employee", employee);
                request.setAttribute("hashedPassword", hashedPassword);
                request.setAttribute("salt", salt);
                
                // direct to employee landing page
                response.sendRedirect("employee.jsp");
                message = "";
                
                // update database
                EmployeeDB.update(employee);
   
        }
            
            else {
                
                message = "ID does not exist";
                url = "/employee_reset.jsp";
                
                request.setAttribute("message", message);
                
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
        request.setAttribute("employee", employee);
        request.setAttribute("message", message);
        
        processRequest(request, response);
    }
   }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
