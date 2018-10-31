package ca.mcgill.ecse321.intercityRideSharingSystem.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administratort04")
public class Administrator {
	private String Name;
	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	@Column(name = "adminstratorid")
	public Integer getId() {
		return this.id;
	}

	public void setName(String value) {
		this.Name = value;
	}

	@Column(name = "administratorName")
	public String getName() {
		return this.Name;
	}
}
