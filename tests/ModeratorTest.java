import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/14/2021
 * Assignment: Appdex
 * File: ModeratorTest.java
 * Description: This class provides the JUnit Tests cases
 * for the User object in the Appdex application. These
 * tests focus on creating constructors and various getters
 * and mutators along with the request feature.
 */
class ModeratorTest {
	/**
	 * Checks if the existing moderator constructor is developed correctly
	 */
	@Test
	void testModeratorStringStringArrayListOfString() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
		
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));

		// Creates null moderator
		Moderator m1 = null;

		// Tests null moderator
		assertTrue(m1 == null);

		// Initializes moderator
		m1 = new Moderator("example", "user1234", apps);

		// Checks that moderator isnt null
		assertTrue(m1 != null);

		// Checks for initialization of fields
		assertTrue(m1.getUserName() != null);
		assertTrue(m1.getPassword() != null);
		assertTrue(m1.getCreatedApps() != null);

		// Checks validity of username
		assertTrue(m1.getUserName().equals("example"));

		// Checks the validity of the password
		assertTrue(m1.getPassword().equals("user1234"));

		// Ensure created apps are in list
		assertTrue(m1.getCreatedApps().size() != 0);
		assertTrue(m1.getCreatedApps().get(0).getName().equals("Instagram"));
	}
	
	/**
	 * Checks if the getUserName() method has expected outputs
	 */
	@Test
	void testGetUserName() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
		
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));
		
		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		
		// Checks validity of username
		assertTrue(m1.getUserName().equals("example"));
		assertFalse(m1.getUserName().equals("user1234"));
	}

	/**
	 * Checks if the getPassword() method has expected outputs
	 */
	@Test
	void testGetPassword() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");

		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));

		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		
		// Checks the validity of the password
		assertFalse(m1.getPassword().equals("p@ssW0rD"));
		assertTrue(m1.getPassword().equals("user1234"));
	}

	/**
	 * Checks if the getCreatedApps() method has expected outputs
	 */
	@Test
	void testGetCreatedApps() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
			
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));
				
		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);

		// Ensure created apps are in list
		assertEquals(m1.getCreatedApps().get(0).getName(), "Instagram");
		assertFalse(m1.getCreatedApps().get(0).getName().equals("ESPN"));
		assertFalse(m1.getCreatedApps().get(0).getName().equals("Twitter"));
	}

	/**
	 * Checks if the changePassword() method has expected outputs
	 */
	@Test
	void testChangePassword() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
		
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));
		
		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		
		// Checks the validity of the password
		assertTrue(m1.getPassword().equals("user1234"));
		
		// Changes the password
		assertTrue(m1.changePassword("pass"));
		
		// Checks for change
		assertTrue(m1.getPassword().equals("pass"));
		
		// Tries to change nothing
		assertFalse(m1.changePassword("pass"));
		
		// Tests invalid passwords
		assertFalse(m1.getPassword().equals("user1234"));
		assertFalse(m1.getPassword().equals("fngjtg"));
	}

	/**
	 * Checks if the requestNewApp() method has expected outputs
	 */
	@Test
	void testRequestNewApp() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
		
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));
		
		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		
		// Checks if the if the string is formatted and details are correct
		assertTrue(m1.requestNewApp("Twitter", "Speak in a 140 chars or less", platforms, "twitter.com").equals("Your app request:\n  Name: Twitter\n  Desc: Speak in a 140 chars or less\n  Link: twitter.com"));
		assertFalse(m1.requestNewApp("Twitter", "Speak in a 140 chars or less", platforms, "twitter.com").equals("Twitter, Speak in a 140 chars or less, (ios, android), twitter.com"));
	}
	
	/**
	 * Checks if the deletComment() method has expected outputs
	 */
	@Test
	void testDeleteComment() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
			
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));
				
		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		
		// Creates an app
		App a1 = new App("Instagram", "Share memories with friends", platforms, "instagram.com");
		
		// Creates a comment for the app
		a1.addComment(new Comment("This app rocks", "example"));
		
		// Ensures comment was added
		assertTrue(a1.getComments().size() == 1);
		
		// Deletes the comment
		assertTrue(m1.deleteComment(a1.getComments().get(0)));
		
		// Ensures comment was deleted
		assertTrue(a1.getComments().size() == 0);
	}

	/**
	 * Checks if the equals method has expected outputs
	 */
	@Test
	void testEquals() {
		// Creates a dummy list of platforms
		ArrayList<String> platforms = new ArrayList<String>();
		platforms.add("ios");
		platforms.add("android");
		
		// Creates a dummy list of apps
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("Instagram", "Share memories with friends", platforms, "instagram.com"));
		apps.add(new App("ESPN", "Worldwide leader in sports news", platforms, "espn.com"));

		// Initializes moderator
		Moderator m1 = new Moderator("example", "user1234", apps);
		Moderator m2 = new Moderator("example2", "user1234", apps);
		Moderator m3 = new Moderator("example", "user1234", apps);
		
		// Checks if the username is the same
		assertTrue(m1.equals(m3));
		assertFalse(m1.equals(m2));
		assertFalse(m2.equals(m3));
	}
}
