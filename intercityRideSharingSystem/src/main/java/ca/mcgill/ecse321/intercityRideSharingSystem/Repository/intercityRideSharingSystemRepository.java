package ca.mcgill.ecse321.intercityRideSharingSystem.Repository;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

	// Method called in the controller, it uses the data received from the mapping
	// to set the according fields in the database
	@Transactional
	public User createUser(String name, String role, Status status, Rating rating) {
		User u = new User();
		u.setName(name);
		u.setRole(role);
		if(status!= null) {
		u.setStatus(status);
		}
		else {
		u.setStatus(Status.idling);
		}		
		u.setRating(rating);
		entityManager.persist(u);
		if (role.equals("Passenger")) {
			Passenger p = new Passenger();
			p.setName(name);
			p.setPassengerId(u.getId());
			p.setStatus(status);
			p.setRating(rating);
			p.setNumberOfJourneys(0);
			entityManager.persist(p);
		} else if (role.equals("Driver")) {
			Driver d = new Driver();
			d.setName(name);
			d.setId(u.getId());
			d.setStatus(status);
			d.setRating(rating);
			d.setNumberOfJourneys(0);
			entityManager.persist(d);
		} else {
			Administrator a = new Administrator();
			a.setName(name);
			a.setId(u.getId());
			entityManager.persist(a);
		}
		return u;
	}

	// Method used to convert the list received from the method finUserWithName to a
	// long string
	@Transactional
	public String getUser(String name) {
		List<User> users = findUserWithName(name);
		String userlist = "";
		for (User u : users) {
			userlist += u.userToString();
		}
		return userlist;
	}

	public String getAllDrivers() {
		List<Driver> drivers = queryAllDrivers();
		String driverList = "";
		for (Driver d : drivers) {
			driverList += d.getName() + d.getNumberOfJourneys() + "<br>";
		}
		return driverList;
	}
	
	// Method used to convert the list received from the method finUserWithName to a
	// long string
	@Transactional
	public String getActiveDriver(String name) {
		List<Driver> drivers = getDriverWithName(name);
		String driverlist = "";
		for (Driver d : drivers) {
			if(d.getStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status.active){
				driverlist += d.driverToString();
			}
		}
		return driverlist;
	}

	@Transactional
	public String getAllActiveDriver() {
		List<Driver> drivers = queryAllDrivers();
		String driverlist = "";
		for (Driver d : drivers) {
			if(d.getStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status.active){
				driverlist += d.driverToString();
			}
		}
		return driverlist;
	}

	@Transactional
	public String getActivePassenger(String name) {
		List<Passenger> passengers = getPassengerWithName(name);
		String passengerlist = "";
		 for (Passenger p : passengers) {
			if(p.getStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status.active){
		 		passengerlist += p.passengerToString();
		 	}
		 }
		return passengerlist;
	}

	@Transactional
	public String getAllActivePassenger() {
		List<Passenger> passengers = queryAllPassengers();
		String passengerlist = "";
		 for (Passenger p : passengers) {
			if(p.getStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status.active){
		 		passengerlist += p.passengerToString();
		 	}
		 }
		return passengerlist;
	}

	@Transactional
	public String getAllActiveJourney() {
		List<Journey> journeys = queryAllJourneys();
		String journeylist = "";
		 for (Journey p : journeys) {
			if(p.getJourneyStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status.active){
		 		journeylist += p.toString();
		 	}
		 }
		return journeylist;
	}

	@Transactional
	public String getActiveJourney(String stop) {
		List<Journey> journeys = queryJourneysWithStop(stop);
		String journeylist = "";
		 for (Journey p : journeys) {
			if(p.getJourneyStatus() == ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status.active){
		 		journeylist += p.toString();
		 	}
		 }
		return journeylist;
	}

	// Method used to convert the list received from the method finUserWithName to a
	// long string
	@Transactional
	public User getUserbyName(String name) {
		List<User> users = findUserWithName(name);
		for (User u : users) {
			if ((u.getName()).equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	public Driver getDriverbyName(String name) {
		List<Driver> drivers = findDriverWithName(name);
		for (Driver d : drivers) {
			if ((d.getName()).equals(name)) {
				return d;
			}
		}
		return null;
	}
	
	public Passenger getPassengerbyName(String name) {
		List<Passenger> passengers = findPassengerWithName(name);
		for (Passenger p : passengers) {
			if ((p.getName()).equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	

	// Method which creates a journey and sets its fields to the inputed data
	// received from the controller
	@Transactional
	public Journey createJourney(String startTime, String stops, String price, String vehicleType,
			String avilableSeating, String driver) {
		Journey journey = new Journey();
		Driver d= getDriverbyName(driver);
		d.setNumberOfJourneys(d.getNumberOfJourneys()+1);
		String finalPrice = "0_" + price;
		journey.setStartTime(startTime);
		journey.setStop(stops);
		journey.setPrice(finalPrice);
		journey.setVehicleType(vehicleType);
		journey.setAvailableSeating(avilableSeating);
		journey.setDriver(driver);
		ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status journeyStatus = ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status.active;
		journey.setJourneyStatus(journeyStatus);
		entityManager.persist(journey);
		return journey;
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> queryAllDrivers() {
		return (List<Driver>) entityManager.createQuery("SELECT c FROM Driver c")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Passenger> queryAllPassengers() {
		return (List<Passenger>) entityManager.createQuery("SELECT c FROM Passenger c")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Journey> queryAllJourneys() {
		return (List<Journey>) entityManager.createQuery("SELECT c FROM Journey c")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Journey> queryJourneysWithStop(String stop) {
		return (List<Journey>) entityManager.createQuery("SELECT c FROM Journey c WHERE c.stop LIKE CONCAT('%', :stops, '%')")
				.setParameter("stops", stop).getResultList();
	}

	// Creates a query to retrieve data from the database: Returns a user with the
	// same name as the one inputed
	@SuppressWarnings("unchecked")
	public List<User> findUserWithName(String name) {
		return (List<User>) entityManager.createQuery("SELECT c FROM User c WHERE strpos(c.name, :userName) > 0")
				.setParameter("userName", name).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Driver> findDriverWithName(String name) {
		return (List<Driver>) entityManager.createQuery("SELECT c FROM Driver c WHERE strpos(c.name, :userName) > 0")
				.setParameter("drivername", name).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Passenger> findPassengerWithName(String name) {
		return (List<Passenger>) entityManager.createQuery("SELECT c FROM Passenger c WHERE strpos(c.name, :userName) > 0")
				.setParameter("userName", name).getResultList();
	}
	
	


	//Create a query to retrieve data from the database: Returns an active driver with
	//the same name as the one inputed 
	@SuppressWarnings("unchecked")
	public List<Driver> getDriverWithName(String name) {
		return (List<Driver>) entityManager.createQuery("SELECT c FROM Driver c WHERE c.name LIKE CONCAT('%', :userName, '%')")
				.setParameter("userName", name).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Passenger> getPassengerWithName(String name) {
		return (List<Passenger>) entityManager.createQuery("SELECT c FROM Passenger c WHERE c.name LIKE CONCAT('%', :passengername, '%')")
				.setParameter("passengername", name).getResultList();
	}


	// Creates a query to retrieve data from the database: Returns the journeys
	// which contain the wanted stop
	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithStop(String stop) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE strpos(j.stop, :stops) > 0")
				.setParameter("stops", stop).getResultList();
	}

	// Creates a query to retrieve data from the database: Returns the journeys
	// which have been opened by a certain driver
	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithDriver(String driver) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE j.driver = :driver")
				.setParameter("driver", driver).getResultList();
	}

	// Creates a query to retrieve data from the database: Returns the journey with
	// the corresponding Id
	@SuppressWarnings("unchecked")
	public List<Journey> findJourneyWithID(Integer id) {
		return (List<Journey>) entityManager.createQuery("SELECT j FROM Journey j WHERE j.journeyId = :id")
				.setParameter("id", id).getResultList();
	}

	// Creates a query to modify data from the database: sets the status field of
	// the chosen journey to closed
	@Transactional
	public String closeJourneyWithID(String id) {
		List<Journey> journeys = findJourneyWithID(Integer.parseInt(id));
		String journeylist = "";
		for (Journey j : journeys) {
			ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status journeyStatus = ca.mcgill.ecse321.intercityRideSharingSystem.Model.Journey.Status.closed;
			j.setJourneyStatus(journeyStatus);
			entityManager.persist(j);
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Creates a query to modify data from the database: Adds a passenger to a
	// journey using the journey Id
	@Transactional
	public String joinJourneyWithID(String id, String passengers) {
		List<Journey> journeys = findJourneyWithID(Integer.parseInt(id));
		String journeylist = "";
		Passenger p= getPassengerbyName(passengers);
		p.setNumberOfJourneys(p.getNumberOfJourneys()+1);
		for (Journey j : journeys) {
			if (j.getPassenger() == null) {
				j.setPassenger(passengers);
			} else {
				j.setPassenger(j.getPassenger() + "_" + passengers);
			}

			entityManager.persist(j);
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Converts the list returned by findJourneyWithDriver to a long string
	@Transactional
	public String getJourneyWithDriver(String driver) {
		List<Journey> journeys = findJourneyWithDriver(driver);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Creates a query to modify data from the database: Modifies the chosen journey
	// by updating the fields with the received data from the controller
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
		return journeylist;
	}

	// Creates a query to get data from the database, which returns the journeys
	// with matching vehicle type as the chosen one
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
	

	// Converts the list returned by findJourneyWithStop to a long string
	@Transactional
	public String getJourneyWithStop(String stops) {
		List<Journey> journeys = findJourneyWithStop(stops);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Converts the list returned by findJourneyWithAvailableSeating to a long
	// string
	@Transactional
	public String getJourneyWithAvailableSeating(String availableSeating) {
		List<Journey> journeys = findJourneyWithAvailableSeating(availableSeating);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Converts the list returned by findJourneyWithCar to a long string
	@Transactional
	public String getJourneyWithCarType(String carType) {
		List<Journey> journeys = findJourneyWithCarType(carType);
		String journeylist = "";
		for (Journey j : journeys) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Filters the journey in the database based on the wanted fields inputed by the
	// user
	@Transactional
	public String getJourney(String start, String destination) {
		List<Journey> journeys = findJourneyWithStop(start);
		List<Journey> journeyd = findJourneyWithStop(destination);
		List<Journey> resultJourney = new ArrayList<Journey>();
		List<Journey> finalresultJourney = new ArrayList<Journey>();
		for (Journey s : journeys) {
			for (Journey d : journeyd) {
				if (s.getJourneyId() == d.getJourneyId()) {
					int i = 0;
					resultJourney.add(i, d);
					i++;
				}
			}
		}
		for (Journey r : resultJourney) {
			String allStops = r.getStop();
			List<String> stops = Arrays.asList(allStops.split("\\s*_\\s*"));
			if (stops.indexOf(start) < stops.indexOf(destination)) {
				int i = 0;
				finalresultJourney.add(i, r);
				i++;
			}
		}
		String journeylist = "";
		for (Journey j : finalresultJourney) {
			journeylist += j.toString() + "<br>";
		}
		return journeylist;
	}

	// Converts the list returned by findUserWithName to a long string
	@Transactional
	public String getUserStatus(String username) {
		List<User> users = findUserWithName(username);
		String userstatus = "";
		for (User u : users) {
			userstatus += u.statusToString() + "<br>";
		}
		return userstatus;
	}

	@Transactional
	public String sortJourney(String start, String destination, String carType, String price, String seating,
			String date) {
		List<Journey> journeys = findJourneyWithStop(start);
		List<Journey> journeyd = findJourneyWithStop(destination);
		List<Journey> journeyCar = findJourneyWithCarType(carType);
		List<Journey> journeyseating = findJourneyWithAvailableSeating(seating);
		List<Journey> resultJourney = new ArrayList<Journey>();
		List<Journey> finalresultJourney = new ArrayList<Journey>();
		List<Journey> sortedJourney = new ArrayList<Journey>();
		List<Journey> finalsortedJourney = new ArrayList<Journey>();
		List<Journey> tempsortedJourney = new ArrayList<Journey>();
		
		for (Journey s : journeys) {
			for (Journey d : journeyd) {
				if (s.getJourneyId() == d.getJourneyId()) {
					int i = 0;
					resultJourney.add(i, d);
					i++;
				}
			}
		}
		
		for (Journey r : resultJourney) {
			String allStops = r.getStop();
			List<String> stops = Arrays.asList(allStops.split("\\s*_\\s*"));
			if (stops.indexOf(start) < stops.indexOf(destination)) {
				int i = 0;
				finalresultJourney.add(i, r);
				i++;
			}
		}
		
		for (Journey f : finalresultJourney) {
			for (Journey c : journeyCar) {
					if (c.getJourneyId() == f.getJourneyId()) {				
					int i = 0;
					sortedJourney.add(i, c);
					i++;
					}
				}

			}
		
		
		for (Journey c : sortedJourney) {
			for (Journey s : journeyseating) {
				if (s.getJourneyId() == c.getJourneyId() ) {
				int i = 0;
				tempsortedJourney.add(i, s);
				i++;
				}
			}
		}
		
		int targetPrice = Integer.parseInt(price);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss");

		for (Journey journey : tempsortedJourney) {
			String allstops = journey.getStop();
			List<String> stop = Arrays.asList(allstops.split("\\s*_\\s*"));
			String prices = journey.getPrice();
			List<String> allPrice = Arrays.asList(prices.split("\\s*_\\s*"));
			int startIndex = stop.indexOf(start);
			int endIndex = stop.indexOf(destination);
			int realPrice = 0;
			for (int i = startIndex; i < (endIndex + 1); i++) {
				realPrice += Integer.parseInt(allPrice.get(i));
			}
			int timeDiff = 0;
			try {
				Date finalDate = formatter.parse(date);
				Date realDate = formatter.parse(journey.getStartTime());
				timeDiff = finalDate.compareTo(realDate);
			} catch (Exception e) {
				return e.getMessage();
			}
			if (realPrice <= targetPrice && timeDiff <= 0) {
				int j = 0;
				finalsortedJourney.add(j, journey);
				j++;
			}

		}
		String sortedjouneys = "";
		for (Journey result : finalsortedJourney) {
			sortedjouneys += result.toString() + "<br>";
		}
		return sortedjouneys;

	}
		
	public Map<Integer, String> rankDrivers() {
		String driverList= getAllDrivers();
			List<String> list = Arrays.asList(driverList.split("\\s*_\\s*"));
			//List <Integer> driverJourneys;
			HashMap<Integer, String> hmap = new HashMap<Integer, String>();
			for(String d : list) {
				Driver driver= getDriverbyName(d);
				hmap.put(driver.getNumberOfJourneys(),d);							
			}
			Map<Integer, String> map = new TreeMap<Integer, String>(hmap);
			return map;
		}
	}

