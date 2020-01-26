package artmin.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="Users")
public class User {
  //    Attributen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;
    
    @Size(min=6, max=50)
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "lastLogin", nullable = true)
    private LocalDateTime lastLogin;
    
    @Column(name = "registerValidateCode", nullable = true)
    private String registerValidateCode;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="userID")
    private Set<UserArtist> users;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="userID")
    private Set<Company> companies;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="userID")
    private Set<EventLocation> eventLocations;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="userID")
    private Set<Client> clients;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="modUserID")
    private Set<Note> notes;

    @ManyToMany(mappedBy = "users",
    fetch = FetchType.EAGER,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Artist> artists = new HashSet<>();

    public Set<Artist> getArtists() {
        return artists;
    }

//    Properties

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRegisterValidateCode() {
        return registerValidateCode;
    }

    public void setRegisterValidateCode(String registerValidateCode) {
        this.registerValidateCode = registerValidateCode;
    }

    public Set<UserArtist> getUsers() {
        return users;
    }

    public void setUsers(Set<UserArtist> users) {
        this.users = users;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<EventLocation> getEventLocations() {
        return eventLocations;
    }

    public void setEventLocations(Set<EventLocation> eventLocations) {
        this.eventLocations = eventLocations;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }


    
    

//    Methodes
    @Override
    public int hashCode() {
        final int prime = 31;
        long result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return (int)result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
