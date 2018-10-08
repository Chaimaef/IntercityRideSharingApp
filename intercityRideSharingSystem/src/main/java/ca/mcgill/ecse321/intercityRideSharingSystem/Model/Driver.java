package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="Drivert04")
public class Driver{
private String name;
private Status status;
private Rating rating;
private Integer id;
public void setName(String value) {
this.name = value;
    }

@Column(name="driverName")   
public String getName() {
return this.name;
    }

public void setId(Integer value) {
this.id = value;
    }

@Id
@Column(name="driverid")
//@GeneratedValue(strategy = GenerationType.AUTO)
public Integer getId() {
return this.id;
    }
private Set<Journey> journey;

@ManyToMany
@Column(name="driverJourney") 
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

public enum Rating {
    one,two,three,four,five
}
    
public Rating getRating() { 
	return rating;
	}

public void setRating(Rating rating) {
	this.rating = rating;
	}





public void setUser(Set<User> user) {
   this.user = user;
}
public enum Status {
    inAJourney,idling
}
    
public Status getStatus() { 
	return status;
	}
public void setStatus(Status status) {
	this.status = status;
	}

}
