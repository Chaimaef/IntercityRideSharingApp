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


	// @RequestMapping("/journeyg/{stop}")
	// public String queryJourney(@PathVariable("stop") String stop) {
	// 	String journeyFound = repository.getJourney(stop);
	// 	if (journeyFound == null) {
	// 		return "Not Found";
	// 	}
	// 	return journeyFound;
	// }

	@RequestMapping("/journeyg/{start}/{destination}")
	public String queryJourney(@PathVariable("start") String start, @PathVariable("destination") String destination) {
		String journeyFound = repository.getJourney(start, destination);
		if (journeyFound == null) {
			return "Not Found";
		}
		return journeyFound;
	}


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
