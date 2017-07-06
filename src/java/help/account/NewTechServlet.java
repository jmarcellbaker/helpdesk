package help.account;

import help.login.PasswordUtil;
import help.login.Technician;
import help.login.TechnicianDB;
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

public class NewTechServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // variables for new tech
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
           action = "newTech";
       }
       
       if (action.equals("newTech")) {
           
           // grab values from request
           //employeeId = Long.parseLong(request.getParameter("employeeId"));
           firstName = request.getParameter("firstName");
           lastName = request.getParameter("lastName");
           email = request.getParameter("email");
           title = request.getParameter("title");
           
           // create new tech
           Technician tech = new Technician();
           
           // set values
           //tech.setEmployeeId(employeeId);
           tech.setFirstName(firstName);
           tech.setLastName(lastName);
           tech.setEmail(email);
           tech.setTitle(title);
           tech.setPassword("welcome1");
           tech.setSalt("");
           
           // create hashed password
           String hashedPassword;
           
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
           request.setAttribute("hashedPassword", hashedPassword);
           request.setAttribute("salt", salt);
           request.setAttribute("tech", tech);
           
           url = "/admin/success_tech.jsp";
           
           // insert into database
           TechnicianDB.insert(tech);
       }
       
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
    }

}
