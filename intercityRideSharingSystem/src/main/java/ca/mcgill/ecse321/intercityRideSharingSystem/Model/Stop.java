package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Stop{
private String name;

private int stopId;

@Id
@Column(name="stopId")
@GeneratedValue(strategy = GenerationType.AUTO)
public int getStopId() {
	return stopId;
}

public void setStopId(int stopId) {
	this.stopId = stopId;
}
   
   public void setName(String value) {
this.name = value;
    }
public String getName() {
return this.name;
    }
private double price;

public void setPrice(double value) {
this.price = value;
    }
public double getPrice() {
return this.price;
    }
private Set<Journey> journey;

@ManyToMany(mappedBy="stop")
public Set<Journey> getJourney() {
   return this.journey;
}

public void setJourney(Set<Journey> journeys) {
   this.journey = journeys;
}

}
