package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Journeyt04")
public class Journey {
	private Status Status;
	private String startTime;
	private int journeyId;
	private String vehicleType;
	private String availableSeating;
	private String price;
	//private String journeyStatus;

	@Id
	@Column(name = "journeyId", updatable = false)
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
		
	public enum Status {
		active,closed
	}

	@Column(name = "journeyStartTime", updatable = true)
	public String getStartTime() {
		return this.startTime;
	}

	// private Set<Stop> stop;
	private String stop;

	// @ManyToMany
	@Column(name = "journeyStops", updatable = true)
	public String getStop() {
		return this.stop;
	}

	public void setStop(String stops) {
		this.stop = stops;
	}

	@Column(name = "prices", updatable = true)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String value) {
		this.price = value;
	}

	// private Set<Driver> driver;
	private String driver;

	// @ManyToMany(mappedBy="journey")
	@Column(name = "journey_driver", updatable = true)
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String drivers) {
		this.driver = drivers;
	}

	// private Set<Passenger> passenger;
	private String passenger;

	// @ManyToMany(mappedBy="journey")
	@Column(name = "journeyPassengers", updatable = true)
	public String getPassenger() {
		return this.passenger;
	}

	public void setPassenger(String passengers) {
		this.passenger = passengers;
	}

	public void setVehicleType(String value) {
		this.vehicleType = value;
	}

	@Column(name = "journeyVehicleType", updatable = true)
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setAvailableSeating(String value) {
		this.availableSeating = value;
	}

	@Column(name = "journeyAvailableSeating", updatable = true)
	public String getAvailableSeating() {
		return this.availableSeating;
	}
	@Id
	@Column(name = "journey_status", updatable = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Status getJourneyStatus() {
		return this.Status;
	}

	public void setJourneyStatus(Status status) {
		this.Status = status;
	}

	public String toString() {
		return "Journey [id=" + journeyId + ", startTime=" + startTime + ", vehicleType=" + vehicleType
				+ ", available Seating=" + availableSeating + ", stops=" + stop + ", associated price=" + price
				+ ", drivers=" + driver + "]";
	}

}
