package help.account;

import help.login.Employee;
import help.login.EmployeeDB;
import help.login.PasswordUtil;
import help.login.Technician;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jmarc
 */

public class NewEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // variables for new employee
       String url = "admin/admin.jsp";
       //Long employeeId;
       String firstName = "";
       String lastName = "";
       String email = "";
       String title = "";
       String password = "";
       String salt = "";
       
       String action = request.getParameter("action");
       if (action == null) {
           
           // set action to new tech
           action = "newEmployee";
       }
       
       if (action.equals("newEmployee")) {
           
           // grab values from request
           //employeeId = Long.parseLong(request.getParameter("employeeId"));
           firstName = request.getParameter("firstName");
           lastName = request.getParameter("lastName");
           title = request.getParameter("title");
           email = request.getParameter("email");
           
           // create new tech
           Employee employee = new Employee();
           
           // set values
           //employee.setEmployeeId(employeeId);
           employee.setFirstName(firstName);
           employee.setLastName(lastName);
           employee.setTitle(title);
           employee.setEmail(email);
           employee.setPassword("welcome1");
           employee.setSalt("");
           
           // create hashed password
           String hashedPassword;
           
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
           request.setAttribute("hashedPassword", hashedPassword);
           request.setAttribute("salt", salt);
           request.setAttribute("employee", employee);
           
           url = "/admin/success_employee.jsp";
           
           
           // insert into datbase
           EmployeeDB.insert(employee);
       }
       
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
    }

    
}
