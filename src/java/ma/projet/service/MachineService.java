/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Machine;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LACHGAR
 */
public class MachineService implements IDao<Machine>{

    @Override
    public boolean create(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Machine getById(long id) {
        Machine service  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service  = (Machine) session.get(Machine.class, id);
        session.getTransaction().commit();
        return service;
    }

    @Override
    public List<Machine> getAll() {
        List <Machine> services = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services  = session.createQuery("from Servicee").list();
        session.getTransaction().commit();
        return services;
    }
    
    
   /* public List<Object[]> nbservice(){
        List<Object[]> services = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services  = session.createQuery("select count(m.nom), m.nom from Servicee m group by m.nom").list();
        session.getTransaction().commit();
        return services;
    }*/
    
    
    
}
