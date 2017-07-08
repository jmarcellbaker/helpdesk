package help.login;

import help.request.DBUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jmarc
 */

// Employee Database Utility

public class EmployeeDB {
    
    // Insert new Employee Into Database
    public static void insert(Employee employee) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(employee);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    
    // Update Employee in Database
    public static void update(Employee employee) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(employee);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    // Delete Employee
    public static void delete(Employee employee) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(employee));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    // Select Employee by Employee ID
    public static Employee selectEmployee(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT e FROM Employee e " +
                "WHERE e.employeeId = :employeeId";
        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        q.setParameter("employeeId", employeeId);
        try {
            Employee employee = q.getSingleResult();
            return employee;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    // Check in Employee ID exist
    public static boolean employeeExists(String employeeId) {
        Employee e = selectEmployee(employeeId);   
        return e != null;
    }
    
    // get by Employee ID
    public static Employee getEmployeebyID(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Employee employee = em.find(Employee.class, employeeId);
            return employee;
        } finally {
            em.close();
        }
    }
    
    // Login 
    public static Employee login(String employeeId, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT e FROM Employee e " +
                "WHERE e.employeeId = :employeeId AND e.password = :password";
        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        q.setParameter("employeeId", employeeId);
        q.setParameter("password", password);
        try {
            Employee employee = q.getSingleResult();
            return employee;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    // Check if Login Exist
    public static boolean loginExist(String employeeId, String password) {
    Employee e = login(employeeId, password);   
    return e != null;
    }
    
    // List Employees
    public static List<Employee> getEmployees() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT e from Employee e";           
        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        
        List<Employee> employees;
        try {
            employees = q.getResultList();
            if (employees == null || employees.isEmpty())
               employees = null;
        } finally {
            em.close();
        }
        return employees;        
    }
    
    
    
}
