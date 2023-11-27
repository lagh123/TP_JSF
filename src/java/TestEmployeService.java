
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
 * @author hp
 */
public class TestEmployeService {
    public static void main(String[] args) {
        EmployeService es = new EmployeService();
        ServiceService ss = new ServiceService();
        
        es.create(new Employe("LAGHRIDAT", "Hasnae", null, null));
    }
}
