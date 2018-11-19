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

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;
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

	// Method to get a user using all the available fields, it uses a method in
	// repository which communicates with the database
	@RequestMapping("/user/{name}/{role}/{status}/{rating}")
	public Integer createUser(@PathVariable("name") String name, @PathVariable("role") String role,
			@PathVariable("status") Status status, @PathVariable("rating") Rating rating) {
		User u = repository.createUser(name, role, status, rating);
		return u.getId();
	}

	// Method to get a user using the name field only, it uses a method in
	// repository which communicates with the database
	@RequestMapping("/userg/{name}")
	public String queryUser(@PathVariable("name") String name) {
		String user = repository.getUser(name);
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

	// Method to create a journey,(user has to fill in all the fields), it calls a
	// method in repository
	// which communicates with the database and inserts the data in the Journeyt04
	// table;
	@RequestMapping("/createj/{time}/{stops}/{price}/{vehicle}/{availableSeating}/{driver}")
	public String createJourney(@PathVariable("time") String time, @PathVariable("stops") String stops,
			@PathVariable("price") String price, @PathVariable("vehicle") String vehicle,
			@PathVariable("availableSeating") String availableSeating, @PathVariable("driver") String driver) {
		if (time.equals("now")) {
			time = strDate;
		}
		Journey journey = repository.createJourney(time, stops, price, vehicle, availableSeating, driver);
		return ("Created journey " + journey.toString());
	}

	// Uses the driver's name to return the journeys with a matching driver
	@RequestMapping("/journeyd/{driver}")
	public String queryJourney(@PathVariable("driver") String driver) {
		String journeyFound = repository.getJourneyWithDriver(driver);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	// Uses an id number, to return the journey with the corresponding id number
	@RequestMapping("/journeyc/{id}")
	public String closeJourney(@PathVariable("id") String id) {
		String journeyFound = repository.closeJourneyWithID(id);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	// Method to add a passenger to a journey
	@RequestMapping("/journeyp/{id}/{passenger}")
	public String joinJourney(@PathVariable("id") String id, @PathVariable("passenger") String passenger) {
		String journeyFound = repository.joinJourneyWithID(id, passenger);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	// Modifies a journey based on the new data entered by the user (all fields must
	// be filled)
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

	// Returns a journey with the corresponding start and destination
	@RequestMapping("/journeyg/{start}/{destination}")
	public String queryJourney(@PathVariable("start") String start, @PathVariable("destination") String destination) {
		String journeyFound = repository.getJourney(start, destination);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	// Returns the journeys with available seating greater than the inputed seating
	@RequestMapping("/availableSeating/{availableSeating}")
	public String queryJourneyWithAvailableSeating(@PathVariable("availableSeating") String availableSeating) {
		String journeyFound = repository.getJourneyWithAvailableSeating(availableSeating);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	// Returns the journeys with the same vehicle type as the inputed one
	@RequestMapping("/carType/{carType}")
	public String queryJourneyWithCarType(@PathVariable("carType") String carType) {
		String journeyFound = repository.getJourneyWithCarType(carType);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	@RequestMapping("/rankDrivers")
	public Map<Integer, String> rankDrivers() {
		Map<Integer, String> rankedDrivers = repository.rankDrivers();
		if (rankedDrivers == null) {
			return null;
		}
		return rankedDrivers;
	}
	
	// Filters the available journeys based on the entered data by the user
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
