package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name="Journeyt04")
public class Journey{
private String startTime;
private int journeyId;
private String vehicleType; 
private int availableSeating; 

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

@Column(name="journeyStartTime")    
public String getStartTime() {
return this.startTime;
    }
private Set<Stop> stop;

@ManyToMany
@Column(name="journeyStops")  
public Set<Stop> getStop() {
   return this.stop;
}

public void setStop(Set<Stop> stops) {
   this.stop = stops;
}

private Set<Driver> driver;

@ManyToMany(mappedBy="journey")
@Column(name="journeyDriver")  
public Set<Driver> getDriver() {
   return this.driver;
}

public void setDriver(Set<Driver> drivers) {
   this.driver = drivers;
}

private Set<Passenger> passenger;

@ManyToMany(mappedBy="journey")
@Column(name="journeyPassengers")  
public Set<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(Set<Passenger> passengers) {
   this.passenger = passengers;
}

public void setVehicleType(String value) {
    this.vehicleType = value;
        }

@Column(name="journeyVehicleType")  
public String getVehicleType() {
    return this.vehicleType;
        }

public void setAvailableSeating(int value) {
            this.availableSeating = value;
                }
@Column(name="journeyAvailableSeating")  
public int getAvailableSeating() {
            return this.availableSeating;
                }   
public String stopsToString(){
    String stops= ""; 
    for (Stop s : stop){
        stops += s.toString(); 
    }
    return stops; 

}
public String toString() {
                    return "Journey [id=" + journeyId + ", startTime=" + startTime + ", vehicleType=" + vehicleType + ", available Seating=" + availableSeating +"]";
                  }
    

}
