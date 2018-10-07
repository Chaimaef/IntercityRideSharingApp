package ca.mcgill.ecse321.intercityRideSharingSystem.Repository;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;
import java.util.concurrent.atomic.AtomicInteger; 

@Repository
public class intercityRideSharingSystemRepository {
	 private final AtomicInteger idInput = new AtomicInteger();

	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String name) {
		User u = new User();
		u.setName(name);
		u.setId(idInput.incrementAndGet());
		entityManager.persist(u);
		return u;
	}

	@Transactional
	public User getUser(String id) {
		 User user = entityManager.find(User.class, Integer.parseInt(id));
		 return user;
	}
	}

