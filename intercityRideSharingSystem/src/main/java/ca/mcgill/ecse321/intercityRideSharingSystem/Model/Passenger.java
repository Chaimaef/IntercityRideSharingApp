package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
@Table(name="Passengert04")
public class Passenger{

private Set<Journey> journey;
private int passengerId;

@Id
@Column(name="passengerid")
@GeneratedValue(strategy = GenerationType.AUTO)
public int getPassengerId() {
	return passengerId;
}

public void setPassengerId(int passengerId) {
	this.passengerId = passengerId;
}

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
