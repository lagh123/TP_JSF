/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author hp
 */
@ManagedBean(name = "serviceBean")
public class ServiceBean implements Serializable {

    private Service service;
    private Employe employe;

    private List<Service> services;
    
    private ServiceService serviceService;
    private EmployeService employeService;
   

    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
        
        employe = new Employe();
        employeService = new EmployeService();
    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    
    public String onCreateAction() {
        serviceService.create(service);
        service = new Service();
        return null;
    }

    public String onDeleteAction() {
        serviceService.delete(serviceService.getById(service.getId()));
        return null;
    }
  

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();       
        serviceService.update(service);
    }

    public void onCancel(RowEditEvent event) {
    }
   
    
    private TreeNode root;
     
    
    
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        List<Service> services = serviceService.getAll();

        for (Service service : services) {
            TreeNode serviceNode = new DefaultTreeNode(service.getNom(), root);

            List<Employe> employees = employeService.getEmployeesByService(service);

            for (Employe employee : employees) {
                if (employee.getManager() == null) {
                    TreeNode managerNode = new DefaultTreeNode(employee.getNom()+" "+employee.getPrenom(), serviceNode);

                    List<Employe> managedEmployees = employeService.getManagedEmployees(employee);

                    for (Employe managedEmployee : managedEmployees) {
                        new DefaultTreeNode(managedEmployee.getNom()+" "+managedEmployee.getPrenom(), managerNode);
                    }
                }
            }
        }

    }
    public TreeNode getRoot() {
        return root;
    }
}
