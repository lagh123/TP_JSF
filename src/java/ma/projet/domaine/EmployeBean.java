/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import java.util.Arrays;

/**
 *
 * @author hp
 */
@ManagedBean
public class EmployeBean {

    private Employe employe;
    private Employe manager;
    private Service service;
    private String username;
    private String password;
    private EmployeService employeService;
    private ServiceService serviceService;
    private List<Employe> employes;

    public EmployeBean() {
        service = new Service();
        employe = new Employe();
        manager = new Employe();
        employeService = new EmployeService();
        serviceService = new ServiceService();
    }
      
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employe getManager() {
        return manager;
    }

    public void setManager(Employe manager) {
        this.manager = manager;
    }
            

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    

    
    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        System.out.println(manager.getNom());
        manager = employeService.getById(manager.getId());
        getEmployes();
    }
    
    public String onCreateAction() {
        employeService.create(employe);
        employe = new Employe();
        return null;
    }
    
     public void onEdit(RowEditEvent event) {
        employe = (Employe) event.getObject();
        Service service = serviceService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }
     
    public String onDeleteAction() {
        employeService.delete(employeService.getById(employe.getId()));
        return null;
    }

    public void onCancel(RowEditEvent event) {
    }
    public String login() {
        Employe employe = employeService.validateEmploye(username, password);

        if (employe != null) {
            // Store user information in session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("employe", employe);
            return "dashboard?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
        
    
    
    
}
