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
// public User (String name) {
// 	this.name = name; 
// }

   public void setName(String value) {
    this.name = value;
    }

public String getName() {
return this.name;
    }
private Integer id;

public void setId(Integer value) {
this.id = value;
    }
@Id
public Integer getId() {
return this.id;
       }
   }
