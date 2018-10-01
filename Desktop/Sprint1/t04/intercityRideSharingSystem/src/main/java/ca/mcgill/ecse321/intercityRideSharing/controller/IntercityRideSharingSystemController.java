package ca.mcgill.ecse321.intercityRideSharing.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.intercityRideSharing.model.User;
import ca.mcgill.ecse321.intercityRideSharing.Repository.IntercityRideSharingSystemRepository;

@RestController
public class IntercityRideSharingSystemController {
	@Autowired
	IntercityRideSharingSystemRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	@PostMapping("user/{name}")
	public String createParticipant(@PathVariable("name") String name) {
		User participant = repository.createUser(name);
		return participant.getName();
	}

	@GetMapping("/user/{name}")
	public String queryParticipant(@PathVariable("name") String name) {
		User participant = repository.getUser(name);
		if(participant == null) {
			return "NOT FOUND";
		}
		return participant.getName();
	}
}





