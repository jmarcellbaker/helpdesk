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

public class EmployeeLoginServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "employeeLogin";
        }
        
        if (action.equals("employeeLogin")) {
            
            // create login parameters
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String url = "";
            
            // Create new employee
            Employee employee = new Employee();
            
            // Set id and password
            employee.setEmail(email);
            employee.setPassword(password);
            
            HttpSession session = request.getSession();
            
            // Check if user exist
            if (EmployeeDB.employeeExists(email)) {
                
                // Match with info in database
                employee = EmployeeDB.getEmployeebyEmail(email);
                
                // 
                String saltedPassword = employee.getSalt() + password;
                String hashedPassword;
                
                try {
                    hashedPassword = PasswordUtil.hashPassword(saltedPassword);
                    
                } catch (NoSuchAlgorithmException ex) {
                    hashedPassword = ex.getMessage();
                }
                
                // If password matches
                if (hashedPassword.equals(employee.getPassword())) {
                    
                    // set session to employee
                    session.setAttribute("employee", employee);
                    
                    // set send to create_request page
                    url = "/employee.jsp";
                    
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                    
                }
                
                else {
                    
                    // login failure
                    url = "/login_failure.jsp";
                    
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }
  
            }
            
            else {
                
                // login failure
                url = "/login_failure.jsp";
                
                getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            }
            
            request.setAttribute("employee", employee);
 
            processRequest(request, response);
            
            }   
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
        }

}
