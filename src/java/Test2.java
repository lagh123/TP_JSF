
import java.util.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Test2 {
    
     public static void main(String[] args) {
        ServiceService ss = new ServiceService();
        
        /*ss.create(new Service("SeviceJSF1"));
        ss.create(new Service("SeviceJSF2"));
        ss.create(new Service("SeviceJSF3"));*/
        EmployeService es = new EmployeService();
        //es.create(new Employe("nom1", "prenom1", "login1","password1",new Date(), null, ss.getById(1L), null, null));
        //es.create(new Employe("nom2", "prenom2","login2","password2", new Date(), null, ss.getById(1L), es.getById(1L), null));
        es.create(new Employe("nom4", "prenom4","login4","password4", new Date(), null, ss.getById(1L), es.getById(1L), null));

    }
    
}
