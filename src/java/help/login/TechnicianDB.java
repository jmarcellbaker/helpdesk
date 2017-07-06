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
    
    // Select Tech by Employee ID
    public static Technician selectTechnician(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM Technician t " +
                "WHERE t.email = :email";
        TypedQuery<Technician> q = em.createQuery(qString, Technician.class);
        q.setParameter("email", email);
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
    public static boolean techExists(String email) {
        Technician t = selectTechnician(email);   
        return t != null;
    }
    
    // get by Employee ID
    public static Technician getTechnicianbyEmail(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Technician tech = em.find(Technician.class, email);
            return tech;
        } finally {
            em.close();
        }
    }
    
    // Login 
    public static Technician login(String email, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM Technician t " +
                "WHERE t.email = :email AND t.password = :password";
        TypedQuery<Technician> q = em.createQuery(qString, Technician.class);
        q.setParameter("email", email);
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
    public static boolean loginExist(String email, String password) {
    Technician t = login(email, password);   
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