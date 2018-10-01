package ca.mcgill.ecse321.intercityRideSharing.Repository;

import javax.persistence.EntityManager;
import ca.mcgill.ecse321.intercityRideSharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class IntercityRideSharingSystemRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name) {
		User participant = new User(name);
		participant.setName(name);
		entityManager.persist(participant);
		return participant;
	}

	@Transactional
	public User getUser(String name) {
		User participant = entityManager.find(User.class, name);
		return participant;
	}

}