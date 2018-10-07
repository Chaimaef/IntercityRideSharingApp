package ca.mcgill.ecse321.intercityRideSharingSystem.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 


import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User; 
import ca.mcgill.ecse321.intercityRideSharingSystem.Repository.intercityRideSharingSystemRepository;

@RestController
public class intercityRideSharingSystemController {

	@Autowired
	intercityRideSharingSystemRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
	public String createUser(@RequestParam(value="name", defaultValue="John") String name, @RequestParam(value="role", defaultValue="Driver") String role) {
		 User u = repository.createUser(name, role);
		 return u.toString();
	}

	@RequestMapping(value = "/userg", method = {RequestMethod.POST, RequestMethod.GET})
	public String queryUser(@RequestParam(value="id", defaultValue="John") String id) {
		User user = repository.getUser(id);
		if(user == null) {
			return "NOT FOUND";
		}
		return user.toString();
	}
	// @RequestMapping(value = "/userg", method = {RequestMethod.POST, RequestMethod.GET})
	// public String queryUser(@RequestParam(value="id", defaultValue="John") String id) {
	// 	User user = repository.getUser(id);
	// 	if(user == null) {
	// 		return "NOT FOUND";
	// 	}
	// 	return user.getName();
	// }

}


