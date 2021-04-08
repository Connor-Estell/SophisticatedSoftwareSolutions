import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/8/2021
 * Assignment: Appdex
 * File: UserTest.java
 * Description: This class provides the JUnit Tests cases
 * for the User object in the Appdex application. These
 * tests focus on creating constructors and various getters
 * and mutators along with the request feature.
 */
public class UserTest {

	/**
	 * Checks if the new user account constructor is developed correctly
	 */
	@Test
	void testUserStringString() {
		// Creates null user
		User u1 = null;
		
		// Tests null user
		assertTrue(u1 == null);
		
		// Initializes user
		u1 = new User("example", "user1234");
		
		// Checks that user isnt null
		assertTrue(u1 != null);
		
		// Checks for initialization of fields
		assertTrue(u1.getUserName() != null);
		assertTrue(u1.getPassword() != null);
		assertTrue(u1.getCreatedApps() != null);
		
		// Checks validity of username
		assertTrue(u1.getUserName().equals("example"));
		assertFalse(u1.getUserName().equals("user1234"));
		
		// Checks the validity of the password
		assertTrue(u1.getPassword().equals("user1234"));
		assertFalse(u1.getPassword().equals("p@ssW0rD"));
		
		// Ensure no created apps in list
		assertTrue(u1.getCreatedApps().size() == 0);
	}

	/**
	 * Checks if the existing user account constructor is developed correctly
	 */
	@Test
	void testUserStringStringArrayListOfString() {
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		apps.add("Instagram, Share memories with friends, (ios, android), instagram.com");
		apps.add("ESPN, Worldwide leader in sports news, (ios, android), espn.com");
		
		// Creates null user
		User u1 = null;
		
		// Tests null user
		assertTrue(u1 == null);
		
		// Initializes user
		u1 = new User("example", "user1234", apps);
		
		// Checks that user isnt null
		assertTrue(u1 != null);
		
		// Checks for initialization of fields
		assertTrue(u1.getUserName() != null);
		assertTrue(u1.getPassword() != null);
		assertTrue(u1.getCreatedApps() != null);
		
		// Checks validity of username
		assertTrue(u1.getUserName().equals("example"));
		
		// Checks the validity of the password
		assertTrue(u1.getPassword().equals("user1234"));
		
		// Ensure created apps are in list
		assertTrue(u1.getCreatedApps().size() != 0);
		assertTrue(u1.getCreatedApps().get(0).equals("Instagram, Share memories with friends, (ios, android), instagram.com"));
	}

	/**
	 * Checks if the getUserName() method has expected outputs
	 */
	@Test
	void testGetUserName() {
		// Initializes user
		User u1 = new User("example", "user1234");
		
		// Checks validity of username
		assertTrue(u1.getUserName().equals("example"));
		assertFalse(u1.getUserName().equals("user1234"));
		
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		
		// Initializes user
		User u2 = new User("example2", "fhfbsf", apps);
		
		// Checks validity of username
		assertTrue(u2.getUserName().equals("example2"));
		assertFalse(u2.getUserName().equals("example"));
	}

	/**
	 * Checks if the getPassword() method has expected outputs
	 */
	@Test
	void testGetPassword() {
		// Initializes user
		User u1 = new User("example", "user1234");
		
		// Checks the validity of the password
		assertTrue(u1.getPassword().equals("user1234"));
		assertFalse(u1.getPassword().equals("p@ssW0rD"));
		
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		
		// Initializes user
		User u2 = new User("example2", "p@ssW0rD", apps);
		
		// Checks the validity of the password
		assertTrue(u2.getPassword().equals("p@ssW0rD"));
		assertFalse(u2.getPassword().equals("user1234"));
	}

	/**
	 * Checks if the getCreatedApps() method has expected outputs
	 */
	@Test
	void testGetCreatedApps() {
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		apps.add("Instagram, Share memories with friends, (ios, android), instagram.com");
		apps.add("ESPN, Worldwide leader in sports news, (ios, android), espn.com");
		
		// Initializes user
		User u1 = new User("example", "user1234", apps);
		
		// Ensure created apps are in list
		assertEquals(u1.getCreatedApps().get(0), "Instagram, Share memories with friends, (ios, android), instagram.com");
		assertFalse(u1.getCreatedApps().get(0).equals("ESPN, Worldwide leader in sports news, (ios, android), espn.com"));
		assertFalse(u1.getCreatedApps().get(0).equals("Twitter, Speak in a 140 chars or less, (ios, android), twitter.com"));
	}

	/**
	 * Checks if the changePassword() method has expected outputs
	 */
	@Test
	void testChangePassword() {
		// Initializes user
		User u1 = new User("example", "user1234");
		
		// Checks the validity of the password
		assertTrue(u1.getPassword().equals("user1234"));
		
		// Changes the password
		assertTrue(u1.changePassword("pass"));
		
		// Checks for change
		assertTrue(u1.getPassword().equals("pass"));
		
		// Tries to change nothing
		assertFalse(u1.changePassword("pass"));
		
		// Tests invalid passwords
		assertFalse(u1.getPassword().equals("user1234"));
		assertFalse(u1.getPassword().equals("fngjtg"));
	}

	/**
	 * Checks if the requestNewApp() method has expected outputs
	 */
	@Test
	void testRequestNewApp() {
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		apps.add("Instagram, Share memories with friends, (ios, android), instagram.com");
		apps.add("ESPN, Worldwide leader in sports news, (ios, android), espn.com");
	
		// Initializes user
		User u1 = new User("example", "user1234", apps);
		
		// Checks if the if the string is formatted and details are correct
		assertTrue(u1.requestNewApp("Twitter", "Speak in a 140 chars or less", "(ios, android)", "twitter.com").equals("Your app request:\n  Name: Twitter\n  Desc: Speak in a 140 chars or less\n  Platform(s): (ios, android)\n  Link: twitter.com"));
		assertFalse(u1.requestNewApp("Twitter", "Speak in a 140 chars or less", "(ios, android)", "twitter.com").equals("Twitter, Speak in a 140 chars or less, (ios, android), twitter.com"));
	
		// Initializes user
		User u2 = new User("example2", "user1234");
		
		// Ensures it wont pull from existing apps
		assertTrue(u2.getCreatedApps() != null);
		
		// Checks if the if the string is formatted and details are correct
		assertTrue(u2.requestNewApp("Twitter", "Speak in a 140 chars or less", "(ios, android)", "twitter.com").equals("Your app request:\n  Name: Twitter\n  Desc: Speak in a 140 chars or less\n  Platform(s): (ios, android)\n  Link: twitter.com"));
		assertFalse(u2.requestNewApp("Twitter", "Speak in a 140 chars or less", "(ios, android)", "twitter.com").equals("Twitter, Speak in a 140 chars or less, (ios, android), twitter.com"));
	}

	/**
	 * Checks if the equals method has expected outputs
	 */
	@Test
	void testEquals() {
		// Creates a dummy list of apps
		ArrayList<String> apps = new ArrayList<String>();
		apps.add("Instagram, Share memories with friends, (ios, android), instagram.com");
		apps.add("ESPN, Worldwide leader in sports news, (ios, android), espn.com");
	
		// Initializes users
		User u1 = new User("example", "user1234", apps);
		User u2 = new User("example2", "user1234");
		User u3 = new User("example", "user1234");
		
		// Checks if the username is the same
		assertTrue(u1.equals(u3));
		assertFalse(u1.equals(u2));
		assertFalse(u2.equals(u3));
	}
}
