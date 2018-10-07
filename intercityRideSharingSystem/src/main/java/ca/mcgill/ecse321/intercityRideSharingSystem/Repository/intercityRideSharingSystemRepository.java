package ca.mcgill.ecse321.intercityRideSharingSystem.Repository;
import javax.persistence.EntityManager;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.*;

@Repository
public class intercityRideSharingSystemRepository {
	 
	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name, String role) {
		User u = new User();
		u.setName(name);
		u.setRole(role);
	    entityManager.persist(u);		
		if(role.equals("Passenger")){
		   Passenger p = new Passenger(); 
		   p.setName(name);
		   p.setPassengerId(u.getId());
		   entityManager.persist(p);
		}
		else if(role.equals("Driver")){
			Driver d = new Driver(); 
			d.setName(name);
			d.setId(u.getId());
			entityManager.persist(d);
		 }
		else{
			Administrator a = new Administrator(); 
			a.setName(name);
			a.setId(u.getId());
			entityManager.persist(a);
		 }
		return u;
	}

	@Transactional
	public User getUser(String id) {
		 User user = entityManager.find(User.class, Integer.parseInt(id));
		 return user;
	}

	@Transactional
	public Journey createJourney(String startTime, String stops, String vehicleType, String avilableSeating, String driver) {
		Journey journey = new Journey(); 
		journey.setStartTime(startTime); 
		journey.setStop(stops);
		journey.setVehicleType(vehicleType); 
		journey.setAvailableSeating(avilableSeating); 
		journey.setDriver(driver);
		entityManager.persist(journey);
		return journey;
	}
	}

