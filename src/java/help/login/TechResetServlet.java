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

@WebServlet(name = "TechResetServlet", urlPatterns = {"/TechResetServlet"})
public class TechResetServlet extends HttpServlet {

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
            action = "techReset";
        }
        
        if (action.equals("techReset")) {
            
            // get parameters from request
            String employeeId = request.getParameter("employeeId");
            String password = request.getParameter("password");
            String message = "";
            String url = "/tech_reset.jsp";
            
            // create new employee
            Technician tech = new Technician();
            
            // set parameters
            tech.setEmployeeId(employeeId);
            tech.setPassword(password);
            
            HttpSession session = request.getSession();
            
            // Check if employee exist
            if (TechnicianDB.techExists(tech.getEmployeeId())) {
                
                tech = TechnicianDB.getTechnicianbyID(employeeId);
                tech.setPassword(password);
                
                String hashedPassword;
                String salt = "";

                try {
                        salt = PasswordUtil.getSalt();
                        hashedPassword = PasswordUtil.hashPassword(salt + tech.getPassword());
                    } catch (NoSuchAlgorithmException ex) {
                        hashedPassword = ex.getMessage();
                    }

                // set password and salt
                tech.setPassword(hashedPassword);
                tech.setSalt(salt);
                
                // set Attributes
                session.setAttribute("tech", tech);
                request.setAttribute("hashedPassword", hashedPassword);
                request.setAttribute("salt", salt);
                
                // direct to tech landing page
                response.sendRedirect("tech.jsp");
                message = "";
                
                // update database
                TechnicianDB.update(tech);
   
        }
            
            else {
                
                message = "ID does not exist";
                url = "/tech_reset.jsp";
                
                request.setAttribute("message", message);
                
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
        request.setAttribute("tech", tech);
        request.setAttribute("message", message);
        
        processRequest(request, response);
    }
   }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
