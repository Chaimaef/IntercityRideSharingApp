package ca.mcgill.ecse321.intercityRideSharingSystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import ca.mcgill.ecse321.intercityRideSharingSystem.Controller.intercityRideSharingSystemController;
import ca.mcgill.ecse321.intercityRideSharingSystem.Repository.intercityRideSharingSystemRepository;
import ca.mcgill.ecse321.intercityRideSharingSystem.Model.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventregistrationApplicationTests {

	@Mock
private intercityRideSharingSystemRepository participantDao;

@InjectMocks
private intercityRideSharingSystemController controller;

private static final String PARTICIPANT_KEY = "TestParticipant";
private static final String PARTICIPANT_ROLE = "Driver";
private static  Integer PARTICIPANT_ID ;
private static  String TEMPSTRING ;
private User user;


private static final String NONEXISTING_KEY = "NotAParticipant";

@Before
public void setMockOutput() {
  when(participantDao.getUser(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
    if(invocation.getArgument(0).equals(PARTICIPANT_KEY)) {
       user = participantDao.createUser(PARTICIPANT_KEY, PARTICIPANT_ROLE);
      return user;
    } else {
      return null;
    }
  });
  }

	@Test
public void testUserQueryFound() {
	PARTICIPANT_ID = user.getId();
	TEMPSTRING =controller.queryUser(String.valueOf(PARTICIPANT_ID));
	String[] parts = TEMPSTRING.split("|");
	TEMPSTRING = parts[1].trim();
  assertEquals(TEMPSTRING, PARTICIPANT_KEY);
}

@Test
public void testUserQueryNotFound() {
		PARTICIPANT_ID = user.getId();

  assertEquals(controller.queryUser(String.valueOf(PARTICIPANT_ID)), "Not Found");
}

}
