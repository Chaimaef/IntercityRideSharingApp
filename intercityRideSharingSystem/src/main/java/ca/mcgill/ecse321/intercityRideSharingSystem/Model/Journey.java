package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Journey{
private String startTime;

private int journeyId;

@Id
@Column(name="journeyId")
@GeneratedValue(strategy = GenerationType.AUTO)
public int getJourneyId() {
	return journeyId;
}

public void setJourneyId(int journeyId) {
	this.journeyId = journeyId;
}
   
   public void setStartTime(String value) {
this.startTime = value;
    }
public String getStartTime() {
return this.startTime;
    }
private Set<Stop> stop;

@ManyToMany
public Set<Stop> getStop() {
   return this.stop;
}

public void setStop(Set<Stop> stops) {
   this.stop = stops;
}

private Set<Driver> driver;

@ManyToMany(mappedBy="journey")
public Set<Driver> getDriver() {
   return this.driver;
}

public void setDriver(Set<Driver> drivers) {
   this.driver = drivers;
}

private Set<Passenger> passenger;

@ManyToMany(mappedBy="journey")
public Set<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(Set<Passenger> passengers) {
   this.passenger = passengers;
}

}
