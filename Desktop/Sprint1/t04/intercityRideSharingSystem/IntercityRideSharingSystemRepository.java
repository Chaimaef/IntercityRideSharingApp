package ca.mcgill.ecse321.intercityRideSharingSystem;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class IntercityRideSharingSystemRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name) {
		User participant = new User();
		participant.setName(name);
		entityManager.persist(participant);
		return participant;
	}

	@Transactional
	public User getNAI(String name) {
		User participant = entityManager.find(NAI.class, name);
		return participant;
	}

}