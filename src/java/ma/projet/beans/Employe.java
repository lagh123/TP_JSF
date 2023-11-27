package ma.projet.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String prenom;
    private String username;
    private String password;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @Lob
    private byte[] photo;
    
    @ManyToOne
    private Service service;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employe manager;

    @OneToMany(mappedBy = "manager" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Employe> employeesManaged;


    public Employe() {
        service = new Service();
    }

    public Employe(String nom, String prenom, Date dateNaissance, byte[] photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
    }

    public Employe(String nom, String prenom, String username, String password, Date dateNaissance, byte[] photo, Service service, Employe manager, List<Employe> employeesManaged) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
        this.service = service;
        this.manager = manager;
        this.employeesManaged = employeesManaged;
    }

  

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public List<Employe> getManagedEmployees() {
        return employeesManaged;
    }

    public void setManagedEmployees(List<Employe> employeesManaged) {
        this.employeesManaged = employeesManaged;
    }
}
