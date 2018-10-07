package ca.mcgill.ecse321.intercityRideSharingSystem.Repository;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;

@Repository
public class intercityRideSharingSystemRepository {
	 
	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name) {
		User u = new User();
		u.setName(name);
		entityManager.persist(u);
		return u;
	}

	@Transactional
	public User getUser(String id) {
		 User user = entityManager.find(User.class, Integer.parseInt(id));
		 return user;
	}
	}

