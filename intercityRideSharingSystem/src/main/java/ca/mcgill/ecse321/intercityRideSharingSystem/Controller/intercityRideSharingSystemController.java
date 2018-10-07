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
import ca.mcgill.ecse321.intercityRideSharingSystem.Repository.intercityRideSharingSystemRepository;

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

	@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
	public Integer createUser(@RequestParam(value="name", defaultValue="John") String name, @RequestParam(value="role", defaultValue="Driver") String role) {
		 User u = repository.createUser(name, role);
		 return u.getId();
	}

	@RequestMapping(value = "/userg", method = {RequestMethod.POST, RequestMethod.GET})
	public String queryUser(@RequestParam(value="id", defaultValue="-1000") String id) {
		User user = repository.getUser(id);
		if(user == null) {
			return "Not Found";
		}
		return user.toString();
	}
	@RequestMapping(value = "/createj", method = {RequestMethod.POST, RequestMethod.GET})
	public String  createJourney(@RequestParam(value="time", defaultValue = "now") String startTime, 
	@RequestParam(value="stops", defaultValue = "-1000") String stops, 
	@RequestParam(value="vehicle", defaultValue = "-1000") String vehicleType, 
	@RequestParam(value="availableSeating", defaultValue = "-1000") String availableSeating,
	@RequestParam(value="driver", defaultValue = "nobody") String drivers) {
		
		
		return null; 
	}

}


