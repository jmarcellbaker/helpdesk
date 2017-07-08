package help.account;

import help.login.Employee;
import help.login.EmployeeDB;
import help.login.PasswordUtil;
import help.login.Technician;
import java.io.IOException;
import java.io.PrintWriter;
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

public class NewEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        
        // variables for new employee
       String url = "admin/admin.jsp";
       String employeeId;
       String firstName = "";
       String lastName = "";
       String email = "";
       String title = "Employee";
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
           //title = request.getParameter("title");
           email = request.getParameter("email");
           
           // generate employee id
           String prefix = "EMP";
           int suffix = (int)(Math.random()*9000)+1000;
           employeeId = prefix + suffix;
           
           // create new tech
           Employee employee = new Employee();
           
           // set values
           employee.setEmployeeId(employeeId);
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
       
       // get all employees
       else if (action.equals("view_employees")) {
           
           // set url
           url = "/admin/view_employees.jsp";
           
           // get all employees
           List<Employee> employees = EmployeeDB.getEmployees();
           request.setAttribute("employees", employees);

       }
       
       // delete employee
       else if (action.equals("delete_employee")) {
           
           // set url
           url = "/admin/view_employees.jsp";
           
           // get employee
           employeeId = request.getParameter("employeeId");
           Employee employee = EmployeeDB.getEmployeebyID(employeeId);
           
           // Delete the employee
           EmployeeDB.delete(employee);
           
           List<Employee> employees = EmployeeDB.getEmployees();
           request.setAttribute("employees", employees);
           
       }
       
       //  show employee
       else if (action.equals("view_employee")) {
           
           employeeId = request.getParameter("employeeId");
           Employee employee = EmployeeDB.selectEmployee(employeeId);
           
           session.setAttribute("employee", employee);
           
           url = "/admin/edit_employee.jsp";
       }
       
       // update employee
       else if (action.equals("update_employee")) {
           
           // get parameters
           email = request.getParameter("email");
           firstName = request.getParameter("firstName");
           lastName = request.getParameter("lastName");
           
           // update employee
           Employee employee = (Employee) session.getAttribute("employee");
           employee.setEmail(email);
           employee.setFirstName(firstName);
           employee.setLastName(lastName);
           EmployeeDB.update(employee);
           
           // retrieve updated list
           List<Employee> employees = EmployeeDB.getEmployees();
           request.setAttribute("employees", employees);
           
           url = "/admin/view_employees.jsp";
           
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
