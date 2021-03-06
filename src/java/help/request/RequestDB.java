package help.request;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class RequestDB {
    
        // insert request into database
        public static void insert(Request request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(request);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
      
    // select all request that are open and unassigned  
    public static List<Request> selectRequests() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.requestStatus = 'OPEN'" +
                         "AND r.technician = ''";
                         //"AND r.Technician = 'null'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);

        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }  
    
    // select all request that are closed 
    public static List<Request> selectClosedRequests() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.requestStatus = 'CLOSED'";
                         //"AND r.Technician = 'null'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);

        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
   // select single request by ticket id
   public static Request selectRequest(Long requestId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                "WHERE r.requestId = :requestId";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);
        q.setParameter("requestId", requestId);
        try {
            Request serviceRequest = q.getSingleResult();
            return serviceRequest;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    } 
   
   
   // update request in database
   public static void update(Request serviceRequest) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(serviceRequest);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
   
   // select all employee open request  
    public static List<Request> selectOpenEmployeeRequests(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.employee.employeeId = :employeeId " +
                         "AND r.requestStatus = 'OPEN'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);
        q.setParameter("employeeId", employeeId);
        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
    // select all employee closed request  
    public static List<Request> selectClosedEmployeeRequests(String employeeId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.employee.employeeId = :employeeId " +
                         "AND r.requestStatus = 'CLOSED'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);
        q.setParameter("employeeId", employeeId);
        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
    // select all tech open request  
    public static List<Request> selectOpenTechRequests(String techName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.technician = :techName " +
                         "AND r.requestStatus = 'OPEN'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);
        q.setParameter("techName", techName);
        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
    // select all tech closed request  
    public static List<Request> selectClosedTechRequests(String techName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.technician = :techName " +
                         "AND r.requestStatus = 'CLOSED'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);
        q.setParameter("techName", techName);
        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
    // select all open request  
    public static List<Request> selectAllOpenRequests() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.requestStatus = 'OPEN'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);

        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
    
    // select all closed request 
    public static List<Request> selectAllClosedRequests() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Request r " +
                         "WHERE r.requestStatus = 'CLOSED'";
        TypedQuery<Request> q = em.createQuery(qString, Request.class);

        List<Request> serviceRequests;
        try {
            serviceRequests = q.getResultList();
            if (serviceRequests == null || serviceRequests.isEmpty())
                serviceRequests = null;
        } finally {
            em.close();
        }
        return serviceRequests;
    }
}
