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

public class TechnicianDB {
    
    // Insert new Tech Into Database
    public static void insert(Technician tech) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(tech);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    
    // Update Tech in Database
    public static void update(Technician tech) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(tech);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    // Delete Tech
    public static void delete(Technician tech) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(tech));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    // Select Tech by Employee ID
    public static Technician selectTechnician(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM Technician t " +
                "WHERE t.employeeId = :employeeId";
        TypedQuery<Technician> q = em.createQuery(qString, Technician.class);
        q.setParameter("employeeId", employeeId);
        try {
            Technician tech = q.getSingleResult();
            return tech;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    // Check in Employee ID exist
    public static boolean techExists(String employeeId) {
        Technician t = selectTechnician(employeeId);   
        return t != null;
    }
    
    // get by Employee ID
    public static Technician getTechnicianbyID(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Technician tech = em.find(Technician.class, employeeId);
            return tech;
        } finally {
            em.close();
        }
    }
    
    // Login 
    public static Technician login(String employeeId, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM Technician t " +
                "WHERE t.employeeId = :employeeId AND t.password = :password";
        TypedQuery<Technician> q = em.createQuery(qString, Technician.class);
        q.setParameter("employeeId", employeeId);
        q.setParameter("password", password);
        try {
            Technician tech = q.getSingleResult();
            return tech;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    // Check if Login Exist
    public static boolean loginExist(String employeeId, String password) {
    Technician t = login(employeeId, password);   
    return t != null;
    }
    
    // List Techs
    public static List<Technician> getTechnicians() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t from Technician t";           
        TypedQuery<Technician> q = em.createQuery(qString, Technician.class);
        
        List<Technician> techs;
        try {
            techs = q.getResultList();
            if (techs == null || techs.isEmpty())
               techs = null;
        } finally {
            em.close();
        }
        return techs;        
    }
    
    
    
}