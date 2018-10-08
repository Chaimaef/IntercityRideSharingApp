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
@Table(name = "Journeyt04")
public class Journey {
	private String startTime;
	private int journeyId;
	private String vehicleType;
	private String availableSeating;
	private String price;

	@Id
	@Column(name = "journeyId")
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

	@Column(name = "journeyStartTime")
	public String getStartTime() {
		return this.startTime;
	}

	// private Set<Stop> stop;
	private String stop;

	// @ManyToMany
	@Column(name = "journeyStops")
	public String getStop() {
		return this.stop;
	}

	public void setStop(String stops) {
		this.stop = stops;
	}

	@Column(name = "prices")
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String value) {
		this.price = value;
	}

	// private Set<Driver> driver;
	private String driver;

	// @ManyToMany(mappedBy="journey")
	@Column(name = "journey_driver")
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String drivers) {
		this.driver = drivers;
	}

	// private Set<Passenger> passenger;
	private String passenger;

	// @ManyToMany(mappedBy="journey")
	@Column(name = "journeyPassengers")
	public String getPassenger() {
		return this.passenger;
	}

	public void setPassenger(String passengers) {
		this.passenger = passengers;
	}

	public void setVehicleType(String value) {
		this.vehicleType = value;
	}

	@Column(name = "journeyVehicleType")
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setAvailableSeating(String value) {
		this.availableSeating = value;
	}

	@Column(name = "journeyAvailableSeating")
	public String getAvailableSeating() {
		return this.availableSeating;
	}

	public String toString() {
		return "Journey [id=" + journeyId + ", startTime=" + startTime + ", vehicleType=" + vehicleType
				+ ", available Seating=" + availableSeating + ", stops=" + stop + ", associated price" + price
				+ ", drivers=" + driver + "]";
	}

}
