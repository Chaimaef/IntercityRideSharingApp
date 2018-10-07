package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrator extends User{
private String Name;

   
   
   public void setName(String value) {
this.Name = value;
    }
public String getName() {
return this.Name;
       }
   }
