package ma.projet.service;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class EmployeService implements IDao<Employe> {
    
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public boolean create(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(long id) {
        Employe employe  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe  = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List <Employe> employes = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return employes;
    }   
     
    public List<Employe> getEmployeesByService(Service service){
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "FROM Employe e WHERE e.service = :service";
            Query query = session.createQuery(hql);
            query.setParameter("service", service);
            employes = query.list();
            return employes;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return employes;
        } finally {
            if(session != null)
                session.close();
        }
    }
    
    public List<Employe> getManagedEmployees(Employe manager){
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "FROM Employe e WHERE e.manager = :manager";
            Query query = session.createQuery(hql);
            query.setParameter("manager", manager);
            employes = query.list();
            return employes;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return employes;
        } finally {
            if(session != null)
                session.close();
        }
    }

    public Employe validateEmploye(String username, String password) {
        // Implement your logic to validate the employe from the database
        // You may use JPA queries to fetch employe details based on the provided username and password
        // Return employe if valid, otherwise null
        TypedQuery<Employe> query = entityManager.createQuery("SELECT e FROM Employe e WHERE e.username = :username AND e.password = :password", Employe.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    
}
  
}
