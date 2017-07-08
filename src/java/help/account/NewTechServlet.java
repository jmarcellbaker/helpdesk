package help.account;

import help.login.PasswordUtil;
import help.login.Technician;
import help.login.TechnicianDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.random;
import java.security.NoSuchAlgorithmException;
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

public class NewTechServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // variables for new tech
       String url = "admin/admin.jsp";
       String employeeId;
       String firstName = "";
       String lastName = "";
       String email = "";
       String title = "Technician";
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
           //title = request.getParameter("title");
           
           // generate employee id
           String prefix = "TEC";
           int suffix = (int)(Math.random()*9000)+1000;
           employeeId = prefix + suffix;
           
           // create new tech
           Technician tech = new Technician();
           
           // set values
           tech.setEmployeeId(employeeId);
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
       
       // get all techs
       else if (action.equals("view_techs")) {
           
           // set url
           url = "/admin/view_techs.jsp";
           
           // get all techs
           List<Technician> techs = TechnicianDB.getTechnicians();
           request.setAttribute("techs", techs);

       }
       
       // delete tech
       else if (action.equals("delete_tech")) {
           
           // set url
           url = "/admin/view_techs.jsp";
           
           // get tech
           employeeId = request.getParameter("employeeId");
           Technician tech = TechnicianDB.getTechnicianbyID(employeeId);
           
           // Delete the tech
           TechnicianDB.delete(tech);
           
           List<Technician> techs = TechnicianDB.getTechnicians();
           request.setAttribute("techs", techs);
           
       }
       
       //  show tech
       else if (action.equals("view_tech")) {
           
           employeeId = request.getParameter("employeeId");
           Technician tech = TechnicianDB.selectTechnician(employeeId);
           
           session.setAttribute("tech", tech);
           
           url = "/admin/edit_tech.jsp";
       }
       
       // update tech
       else if (action.equals("update_tech")) {
           
           // get parameters
           email = request.getParameter("email");
           firstName = request.getParameter("firstName");
           lastName = request.getParameter("lastName");
           
           // update employee
           Technician tech = (Technician) session.getAttribute("tech");
           tech.setEmail(email);
           tech.setFirstName(firstName);
           tech.setLastName(lastName);
          TechnicianDB.update(tech);
           
           // retrieve updated list
           List<Technician> techs = TechnicianDB.getTechnicians();
           request.setAttribute("techs", techs);
           
           url = "/admin/view_techs.jsp";
           
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
