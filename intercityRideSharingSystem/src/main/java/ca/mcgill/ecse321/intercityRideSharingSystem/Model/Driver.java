package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="Drivert04")
public class Driver{
private String name;

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
@Column(name="driverid")
@GeneratedValue(strategy = GenerationType.AUTO)
public Integer getId() {
return this.id;
    }
private Set<Journey> journey;

@ManyToMany
public Set<Journey> getJourney() {
   return this.journey;
}

public void setJourney(Set<Journey> journeys) {
   this.journey = journeys;
}

private Set<User> user;

@ManyToMany
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

}
