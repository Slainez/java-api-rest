
package fr.m2i.java.api.rest.tpuser.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class User {
    
     @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     
     @Column(name = "lastname", length = 100)
    private String lastname;
    
    @Column(name = "firstname", length = 100)
    private String firstname;
    
    @Column(name = "role" , columnDefinition ="ENUM('normal','admin')")
    private String role;
    
    @Column(name = "email", length = 40)
    private String email;
    
    @Column(name = "password", length = 100)
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    public User() {
        
    }

    public User(String lastname, String firstname, String role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", lastname=").append(lastname);
        sb.append(", firstname=").append(firstname);
        sb.append(", role=").append(role);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
    
    
     public void copy(User userData) {

        if (userData == null) {
            return;
        }
        if(userData.getLastname() != null){
            this.setLastname(userData.getLastname());
        }
         if(userData.getFirstname() != null){
            this.setFirstname(userData.getFirstname());
        }
          if(userData.getRole() != null){
            this.setRole(userData.getRole());
        }
         if(userData.getEmail() != null){
            this.setEmail(userData.getEmail());
        }
          if(userData.getPassword() != null){
            this.setPassword(userData.getPassword());
        }
     }
     
     public boolean isNotNull (User userData){
        
        if (userData == null) {
           return false ;
        }
        if(userData.getLastname() == null){
           return false ;
        }
         if(userData.getFirstname() == null){
           return false ;
        }
          if(userData.getRole() == null){
            return false ;
        }
         if(userData.getEmail() == null){
            return false ;
        }
          if(userData.getPassword() == null){
            return false ;
        }
         return true ;
     }
    
    
}
