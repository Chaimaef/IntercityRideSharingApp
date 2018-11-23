package ca.mcgill.ecse321.intercityRideSharingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Rating;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status;
import ca.mcgill.ecse321.intercityRideSharingSystem.Repository.intercityRideSharingSystemRepository;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.*;

@CrossOrigin
@RestController
public class intercityRideSharingSystemController {
	Date date = new Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	String strDate = dateFormat.format(date);

	@Autowired
	intercityRideSharingSystemRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	/**
	 * Method to get a user using all the available fields, it uses a method in
	 * repository which communicates with the database
	 */
	@RequestMapping("/user/{name}/{role}/{status}/{rating}")
	public String createUser(@PathVariable("name") String name, @PathVariable("role") String role,
			@PathVariable("status") Status status, @PathVariable("rating") Rating rating) {
		// User u = repository.createUser(name, role, status, rating);
		return repository.createUser(name, role, status, rating);
	}

	/**
	 * Method to get a user using the name field only, it uses a method in
	 * repository which communicates with the database
	 */
	@RequestMapping("/userg/{name}")
	public String queryUser(@PathVariable("name") String name) {
		String user = repository.getUser(name);
		if (user == null) {
			return "Not Found";
		}
		return user;
	}

	/**
	 * Method to get a driver using the name field only, it uses a method in
	 * repository which communicates with the database
	 */
	@RequestMapping("/driverg/{name}")
	public String queryDriver(@PathVariable("name") String name) {
		String user = repository.getActiveDriver(name);
		if (user == null) {
			return "Not Found";
		}
		return user;
	}

	/**
	 * method to get all the active drivers using a method from repository tha
	 * communicates with the database
	 */
	@RequestMapping("/driverg/")
	public String queryAllActiveDriver() {
		String user = repository.getAllActiveDriver();

		if (user == null) {
			return "Not Found";
		}
		return user;
	}

	/**
	 * method to get all the active passengers using a method from repository tha
	 * communicates with the database
	 */

	@RequestMapping("/passengerg/")
	public String queryAllActivePassenger() {
		String user = repository.getAllActivePassenger();
		if (user == null) {
			return "Not Found";
		}
		return user;
	}

	/**
	 * method to get all the active journeys using a method from repository tha
	 * communicates with the database
	 */
	@RequestMapping("/journeysg/")
	public String queryAllActiveJourney() {
		String journey = repository.getAllActiveJourney();
		if (journey == null) {
			return "Not Found";
		}
		return journey;
	}

	/**
	 * method to get a particular active journeys using the name field only using a
	 * method from repository tha communicates with the database
	 */
	@RequestMapping("/journeysg/{stop}")
	public String queryActiveJourney(@PathVariable("stop") String stop) {
		String journey = repository.getActiveJourney(stop);
		if (journey == null) {
			return "Not Found";
		}
		return journey;
	}

	/**
	 * method to get a particular active passenger using the name field only using a
	 * method from repository tha communicates with the database
	 */
	@RequestMapping("/passengerg/{name}")
	public String queryActivePassenger(@PathVariable("name") String name) {
		String user = repository.getActivePassenger(name);
		if (user == null) {
			return "Not Found";
		}
		return user;
	}

	@RequestMapping(value = "/u", method = { RequestMethod.POST, RequestMethod.GET })
	public String queryUserName(@RequestParam(value = "name", defaultValue = "-1000") String name) {
		User u = repository.getUserbyName(name);
		if (u == null) {
			return "Not Found";
		}
		return u.getName();
	}

	/**
	 * Method to create a journey,(user has to fill in all the fields), it calls a
	 * method in repository which communicates with the database and inserts the
	 * data in the Journeyt04 table
	 */
	@RequestMapping("/createj/{time}/{stops}/{price}/{vehicle}/{availableSeating}/{driver}")
	public String createJourney(@PathVariable("time") String time, @PathVariable("stops") String stops,
			@PathVariable("price") String price, @PathVariable("vehicle") String vehicle,
			@PathVariable("availableSeating") String availableSeating, @PathVariable("driver") String driver) {
		if (time.equals("now")) {
			time = strDate;
		}
		String journey = repository.createJourney(time, stops, price, vehicle, availableSeating, driver);
		return ("Created journey " + journey);
	}

	/** Uses the driver's name to return the journeys with a matching driver */
	@RequestMapping("/journeyd/{driver}")
	public String queryJourney(@PathVariable("driver") String driver) {
		String journeyFound = repository.getJourneyWithDriver(driver);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/** Uses an id number, to return the journey with the corresponding id number */
	@RequestMapping("/journeyc/{id}")
	public String closeJourney(@PathVariable("id") String id) {
		String journeyFound = repository.closeJourneyWithID(id);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/** Method to add a passenger to a journey */
	@RequestMapping("/journeyp/{id}/{passenger}")
	public String joinJourney(@PathVariable("id") String id, @PathVariable("passenger") String passenger) {
		String journeyFound = repository.joinJourneyWithID(id, passenger);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/**
	 * Modifies a journey based on the new data entered by the user (all fields must
	 * be filled)
	 */
	@RequestMapping("/journeyupdate/{id}/{time}/{stops}/{price}/{vehicle}/{availableSeating}/{driver}")
	public String updateJourneywithID(@PathVariable("id") String id, @PathVariable("time") String time,
			@PathVariable("stops") String stops, @PathVariable("price") String price,
			@PathVariable("vehicle") String vehicle, @PathVariable("availableSeating") String availableSeating,
			@PathVariable("driver") String driver) {
		String journeyFound = repository.updateJourneyWithID(id, time, stops, price, vehicle, availableSeating, driver);
		if (journeyFound == null) {
			return "Updated journey not found";
		}
		return "Journey being updated" + journeyFound;
	}

	/** Returns a journey with the corresponding start and destination */
	@RequestMapping("/journeyg/{start}/{destination}")
	public String queryJourney(@PathVariable("start") String start, @PathVariable("destination") String destination) {
		String journeyFound = repository.getJourney(start, destination);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/**
	 * Returns the journeys with available seating greater than the inputed seating
	 */
	@RequestMapping("/availableSeating/{availableSeating}")
	public String queryJourneyWithAvailableSeating(@PathVariable("availableSeating") String availableSeating) {
		String journeyFound = repository.getJourneyWithAvailableSeating(availableSeating);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/** Returns the journey with the same ID that is inserted as an input Id */
	@RequestMapping("/Id/{Id}")
	public String queryJourneyWithId(@PathVariable("Id") String Id) {
		int IdNumber = Integer.parseInt(Id);
		Journey journeyFound = repository.getJourneyWithId(IdNumber);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound.toString();
	}

	/** Returns the journeys with the same vehicle type as the inputed one */
	@RequestMapping("/carType/{carType}")
	public String queryJourneyWithCarType(@PathVariable("carType") String carType) {
		String journeyFound = repository.getJourneyWithCarType(carType);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	/**
	 * Returns the list of the drivers that made journeys, they are listed in
	 * descending order in terms of ranks
	 */
	@RequestMapping("/rankDrivers")
	public String rankDrivers() {
		String rankedDrivers = repository.rankDrivers();
		if (rankedDrivers == null) {
			return null;
		}
		return rankedDrivers;
	}

	/**
	 * Returns the list of passengers that made the journey inbetween the entered
	 * start and end date name field in descending order of ranks
	 */
	@RequestMapping("/rankPassengersDate/{startDate}/{endDate}")
	public String rankPassengersWithDate(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		String rankedDrivers = repository.rankPassengersWithDate(startDate, endDate);
		if (rankedDrivers == null) {
			return null;
		}
		return rankedDrivers;
	}

	/**
	 * Method that Returns the list of drivers who made journeys inbetween the
	 * entered start and end date name field in descending order of ranks
	 */
	@RequestMapping("/rankDriversDate/{startDate}/{endDate}")
	public String rankDriversWithDate(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		String rankedDrivers = repository.rankDriversWithDate(startDate, endDate);
		if (rankedDrivers == null) {
			return null;
		}
		return rankedDrivers;
	}

	/**
	 * Method that Returns the list of passengers who made journeys in descending
	 * order of ranks
	 */
	@RequestMapping("/rankPassengers")
	public String rankPassengers() {
		String rankedPassengers = repository.rankPassengers();
		if (rankedPassengers == null) {
			return "There are currently no passengers";
		}
		return rankedPassengers;
	}

	/**
	 * Method that Returns the list of stops inbetween the entered start and end
	 * date name field in descending order of ranks
	 */
	@RequestMapping("/rankStops/{startDate}/{endDate}")
	public String rankStops(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
		String rankedStops = repository.rankStops(startDate, endDate);
		if (rankedStops == null) {
			return "There are currently no stops";
		}
		return rankedStops;
	}

	/** Filters the available journeys based on the entered data by the user */
	@RequestMapping("/journeysort/{start}/{destination}/{carType}/{price}/{seating}/{date}")
	public String sortJourney(@PathVariable("start") String start, @PathVariable("destination") String destination,
			@PathVariable("carType") String carType, @PathVariable("price") String price,
			@PathVariable("seating") String seating, @PathVariable("date") String date) {
		String journeyFound = repository.sortJourney(start, destination, carType, price, seating, date);
		if (journeyFound == null) {
			return "journey not found";
		}
		return "sorted Journey found" + journeyFound;
	}

	@RequestMapping(value = "/checks", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkUserStatus(@RequestParam(value = "name", defaultValue = "John") String username) {
		String userstatus = repository.getUserStatus(username);
		if (userstatus == null) {
			return "User Not Found";
		}
		return userstatus;
	}
}
