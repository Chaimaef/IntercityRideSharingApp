package ca.mcgill.ecse321.intercityRideSharingSystem.Model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Table(name="Userst04")
public class User{
private String name;
private String role;

public void setName(String value) {
    this.name = value;
    }

@Column(name="userName")
public String getName() {
return this.name;
    }
private Integer id;

public void setId(Integer value) {
this.id = value;
    }
@Id
@Column(name="userid")
@GeneratedValue(strategy = GenerationType.AUTO)
public Integer getId() {
return this.id;
       }
public void setRole(String value) {
        this.role = value;
        }
    
@Column(name="userRole")
public String getRole() {
    return this.role;
        }
   }
