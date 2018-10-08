package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Userst04")
public class User {
	private Rating rating;
	private Status Status;
	private String name;
	private String role;

	public void setName(String value) {
		this.name = value;
	}

	@Column(name = "userName")
	public String getName() {
		return this.name;
	}

	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setRole(String value) {
		this.role = value;
	}

	@Column(name = "userRole")
	public String getRole() {
		return this.role;
	}

	public String userToString() {
		return "User [id=" + id + ", userName #" + name + "# userRole=" + role + ",status=" + Status + ",rating="
				+ rating + " ]";
	}
	public String statusToString() {
		return "UserName #" + name + "# userRole=" + role + ",status="+ Status +" ]";
	  }

	public enum Status {
		filter, inAJourney, idling
	}

	public enum Rating {
		filler, one, two, three, four, five
	}

	public void setStatus(Status status) {
		this.Status = status;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Status getStatus() {
		return this.Status;
	}

}
