package ca.mcgill.ecse321.intercityRideSharingSystem.Repository;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.List;
import java.util.Collection;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Rating;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.Driver;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.Passenger;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.Administrator;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey;

@Repository
public class intercityRideSharingSystemRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name, String role, Status status, Rating rating) {
		User u = new User();
		u.setName(name);
		u.setRole(role);
		u.setStatus(status);
		u.setRating(rating);
		entityManager.persist(u);
		if (role.equals("Passenger")) {
			Passenger p = new Passenger();
			p.setName(name);
			p.setPassengerId(u.getId());
			p.setStatus(status);
			p.setRating(rating);
			entityManager.persist(p);
		} else if (role.equals("Driver")) {
			Driver d = new Driver();
			d.setName(name);
			d.setId(u.getId());
			d.setStatus(status);
			d.setRating(rating);
			entityManager.persist(d);
		} else {
			Administrator a = new Administrator();
			a.setName(name);
			a.setId(u.getId());
			entityManager.persist(a);
		}
		return u;
	}

	@Transactional
	public String getUser(String name) {
		List<User> users = findUserWithName(name);
		String userlist = "";
		for (User u : users) {
			userlist += u.userToString();
		}
		return userlist;
	}
	@Transactional
	public User getUserbyName(String name) {
		List<User> users = findUserWithName(name);
		for (User u : users) {
			if ((u.getName()).equals(name)){
				return u;
			}
		}
		return null; 
	}

	@Transactional
	public Journey createJourney(String startTime, String stops, String price, String vehicleType,
			String avilableSeating, String driver) {
		Journey journey = new Journey();
		String finalPrice = "0_"+price; 
		journey.setStartTime(startTime);
		journey.setStop(stops);
		journey.setPrice(finalPrice);
		journey.setVehicleType(vehicleType);
		journey.setAvailableSeating(avilableSeating);
		journey.setDriver(driver);
		entityManager.persist(journey);
		return journey;
	}
	

	@SuppressWarnings("unchecked")
	public List<User> findUserWithName(String name) {
		return (List<User>) entityManager.createQuery("SELECT c FROM User c WHERE strpos(c.name, :userName) > 0")
				.setParameter("userName", name)
				// .setMaxResults(20)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithStop(String stop) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE strpos(j.stop, :stops) > 0")
				.setParameter("stops", stop)
				// .setMaxResults(20)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithDriver(String driver) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE j.driver = :driver")
				 .setParameter("driver", driver)
				// .setMaxResults(20)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithID(Integer id) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE j.journeyId = :id")
				 .setParameter("id", id)
				// .setMaxResults(20)
				.getResultList();
	}

	@Transactional
	public String getJourneyWithDriver(String driver) {
		List<Journey> journeys = findJourneyWithDriver(driver);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		// User user = entityManager.find(User.class, Integer.parseInt(id));
		return journeylist;
	}

    @Transactional
	public String updateJourneyWithID(String id, String startTime, String stops, String price, String vehicleType,
	String avilableSeating, String driver) {
		int target = Integer.parseInt(id); 
		List<Journey> journeys = findJourneyWithID(target);
		String journeylist = "";
		for (Journey j : journeys) {
			j.setStartTime(startTime);
		    j.setStop(stops);
		    j.setPrice(price);
		    j.setVehicleType(vehicleType);
		    j.setAvailableSeating(avilableSeating);
		    j.setDriver(driver);
		    entityManager.persist(j);
			journeylist += j.toString() + "<br>";
		}
		// User user = entityManager.find(User.class, Integer.parseInt(id));
		return journeylist;
	}
    
    
//	@SuppressWarnings("unchecked")
//	public List<Journey> findJourneyWithDate(String date) {
//		Date date1 = null;
//		int i = 0;
//		try {
//			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
//			System.out.println(date1);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		List<Journey> journeys = (List<Journey>) entityManager
//				.createQuery("SELECT j FROM Journey j WHERE strpos(j.startTime, :date) > 0").setParameter("date", date)
//				.getResultList();
//		List<Journey> wantedJourneys = new ArrayList();
//		for (Journey journey : journeys) {
//			Date date2 = null;
//			try {
//				date2 = new SimpleDateFormat("dd-MM-yyyy").parse(
//						(String) entityManager.createQuery("SELECT startTime FROM Journey j").getResultList().get(i));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			if (date2.before(date1)) {
//				wantedJourneys.add(journey);
//			}
//			i++;
//		}
//
//		return wantedJourneys;
//	}

	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithCarType(String carType) {
		return (List<Journey>) entityManager
				.createQuery("SELECT j FROM Journey j WHERE strpos(j.vehicleType, :carType)>0")
				.setParameter("carType", carType).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithAvailableSeating(String wantedSeating) {
		Integer Seating = Integer.parseInt(wantedSeating);
		int i = 0;
		List<Journey> wantedJourneys = new ArrayList();
		List<Journey> journeys = (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j").getResultList();
		for (Journey journey : journeys) {
			List temp = entityManager.createQuery("SELECT journey.availableSeating FROM Journey journey")
					.getResultList();
			int availableSeating = Integer.parseInt((String) temp.get(i));
			i++;
			if (availableSeating >= Seating) {
				wantedJourneys.add(journey);
			}
		}
		return wantedJourneys;
	}

	@Transactional
	public String getJourneyWithStop(String stops) {
		List<Journey> journeys = findJourneyWithStop(stops);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

//	@Transactional
//	public String getJourneyWithDate(String date) {
//		List<Journey> journeys = findJourneyWithDate(date);
//		String journeylist = "";
//		for (Journey j : journeys) {
//			journeylist += j.toString() + "<br>";
//		}
//		return journeylist;
//	}

	@Transactional
	public String getJourneyWithAvailableSeating(String availableSeating) {
		List<Journey> journeys = findJourneyWithAvailableSeating(availableSeating);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	@Transactional
	public String getJourneyWithCarType(String carType) {
		List<Journey> journeys = findJourneyWithCarType(carType);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}
	@Transactional
	public String getJourney(String start, String destination) {
		List<Journey> journeys = findJourneyWithStop(start);
		List<Journey> journeyd = findJourneyWithStop(destination);
		List<Journey> resultJourney = new ArrayList<Journey>(); 
		List<Journey> finalresultJourney = new ArrayList<Journey>(); 
		for(Journey s : journeys){
			for (Journey d: journeyd ){
				if(s.getJourneyId() == d.getJourneyId()){
					int i = 0; 
					resultJourney.add(i, d);
					i ++; 
				}
			}
		}
		for(Journey r : resultJourney){
			String allStops = r.getStop(); 
			List<String> stops = Arrays.asList(allStops.split("\\s*_\\s*"));
			if (stops.indexOf(start) < stops.indexOf(destination)){
				int i = 0; 
				finalresultJourney.add(i, r);
				i ++; 
			}
		}
		String journeylist = "";
		for (Journey j : finalresultJourney) {
			journeylist += j.toString() + "<br>";
		}
		// User user = entityManager.find(User.class, Integer.parseInt(id));
		return journeylist;
	}
	@Transactional
	public String getUserStatus(String username) {
		List <User> users = findUserWithName(username); 
		 String userstatus = ""; 
		 for (User u : users){
			  userstatus += u.statusToString()+ "<br>"; 
		 }
		 return userstatus; 
	}
}
