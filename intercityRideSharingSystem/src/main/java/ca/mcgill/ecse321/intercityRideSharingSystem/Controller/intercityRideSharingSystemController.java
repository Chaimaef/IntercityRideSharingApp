package ca.mcgill.ecse321.intercityRideSharingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Rating;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User.Status;
import ca.mcgill.ecse321.intercityRideSharingSystem.Repository.intercityRideSharingSystemRepository;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.*;

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

	@RequestMapping("/user/{name}/{role}/{status}/{rating}")
	public Integer createUser(@PathVariable("name") String name, @PathVariable("role") String role, @PathVariable("status") Status status, @PathVariable("rating") Rating rating) {
		User u = repository.createUser(name, role, status, rating);
		return u.getId();
	}
	/*
	@RequestMapping(value = "/user", method = { RequestMethod.POST, RequestMethod.GET })
	public Integer createUser(@RequestParam(value = "name", defaultValue = "John") String name,
			@RequestParam(value = "role", defaultValue = "Driver") String role,
			@RequestParam(value = "status", defaultValue = "idling") Status status,
			@RequestParam(value = "rating", defaultValue = "five") Rating rating) {
		User u = repository.createUser(name, role, status, rating);
		return u.getId();
	}*/
   
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

	@RequestMapping("/createj/{time}/{stops}/{price}/{vehicle}/{availableSeating}/{driver}")
	public String createJourney(@PathVariable("time") String time, @PathVariable("stops") String stops, 
	                            @PathVariable("price") String price,@PathVariable("vehicle") String vehicle, 
	                            @PathVariable("availableSeating") String availableSeating, @PathVariable("driver") String driver) {
		if (time.equals("now")) {
			time = strDate;
		}
		Journey journey = repository.createJourney(time, stops, price, vehicle, availableSeating, driver);
		return ("Created journey " + journey.toString());
	}


	@RequestMapping("/journeyd/{driver}")
	public String queryJourney(@PathVariable("driver") String driver) {
		String journeyFound = repository.getJourneyWithDriver(driver);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	@RequestMapping("/journeyc/{id}")
	public String closeJourney(@PathVariable("id") String id) {
		String journeyFound = repository.closeJourneyWithID(id);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	@RequestMapping("/journeyupdate/{id}/{time}/{stops}/{price}/{vehicle}/{availableSeating}/{driver}")
	public String updateJourneywithID(@PathVariable("id") String id, 
	                            @PathVariable("time") String time, @PathVariable("stops") String stops, 
	                            @PathVariable("price") String price,@PathVariable("vehicle") String vehicle, 
	                            @PathVariable("availableSeating") String availableSeating, @PathVariable("driver") String driver) {
		String journeyFound = repository.updateJourneyWithID(id, time, stops, price, vehicle, availableSeating, driver);
		if (journeyFound == null) {
			return "Updated journey not found";
		}
		return "Journey being updated" + journeyFound;
	}


	@RequestMapping("/journeyg/{start}/{destination}")
	public String queryJourney(@PathVariable("start") String start, @PathVariable("destination") String destination) {
		String journeyFound = repository.getJourney(start, destination);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}

	@RequestMapping("/availableSeating/{availableSeating}")
	public String queryJourneyWithAvailableSeating(
			@PathVariable("availableSeating") String availableSeating) {
		String journeyFound = repository.getJourneyWithAvailableSeating(availableSeating);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}
	
	@RequestMapping("/carType/{carType}")
	public String queryJourneyWithCarType(@PathVariable("carType") String carType) {
		String journeyFound = repository.getJourneyWithCarType(carType);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}
	//String start, String destination, String carType, String price, String seating, String date
	@RequestMapping("/journeysort/{start}/{destination}/{carType}/{price}/{seating}/{date}")
	public String sortJourney(@PathVariable("start") String start, 
	                            @PathVariable("destination") String destination, @PathVariable("carType") String carType, 
	                            @PathVariable("price") String price,@PathVariable("seating") String seating, 
	                            @PathVariable("date") String date) {
		String journeyFound = repository.sortJourney(start, destination, carType, price, seating, date);
		if (journeyFound == null) {
			return "journey not found";
		}
		return "sorted Journey found" + journeyFound;
	}
	
//	@RequestMapping("/date/{date}")
//	public String queryJourneyWithDate(@PathVariable("date") String date) {
//		String journeyFound = repository.getJourneyWithDate(date);
//		if (journeyFound == null) {
//			return "Not Found";
//		}
//		return journeyFound;
//	}


	// @RequestMapping(value = "/journeyg", method = { RequestMethod.POST, RequestMethod.GET })
	// public String queryJourney(@RequestParam(value = "stop", defaultValue = "montreal") String stop) {
	// 	String journeyFound = repository.getJourney(stop);
	// 	if (journeyFound == null) {
	// 		return "Not Found";
	// 	}
	// 	return journeyFound;
	// }
	@RequestMapping(value = "/checks", method = {RequestMethod.POST, RequestMethod.GET})
	public String checkUserStatus(@RequestParam(value="name", defaultValue="John") String username) {
		String userstatus = repository.getUserStatus(username); 
		if(userstatus == null) {
			return "User Not Found";
		}
		return userstatus; 
	}
}
