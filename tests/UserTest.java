import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: UserTest.java
 * Description: This class provides the JUnit Tests cases
 * for the User object in the Appdex application. These
 * tests focus on creating constructors and methods.
 */
class UserTest {

	/**
	 * Tests that the constructor is properly 
	 * created with all fields not null
	 */
	@Test
	void testUser() {
		User u1 = null;
		
		assertTrue(u1 == null);
		
		u1 = new User();
		
		assertTrue(u1 != null);
		assertTrue(u1.appName != null);
		assertTrue(u1.add_comment != null);
		assertTrue(u1.addButton != null);
		assertTrue(u1.alph_asc != null);
		assertTrue(u1.alph_desc != null);
		assertTrue(u1.android != null);
		assertTrue(u1.appTA != null);
		assertTrue(u1.comment != null);
		assertTrue(u1.description != null);
		assertTrue(u1.descriptionTF != null);
		assertTrue(u1.filter != null);
		assertTrue(u1.full != null);
		assertTrue(u1.ios != null);
		assertTrue(u1.link != null);
		assertTrue(u1.linkTF != null);
		assertTrue(u1.Logout != null);
		assertTrue(u1.menuBar != null);
		assertTrue(u1.nameTF != null);
		assertTrue(u1.organization != null);
		assertTrue(u1.orgTF != null);
		assertTrue(u1.price != null);
		assertTrue(u1.price_high != null);
		assertTrue(u1.price_low != null);
		assertTrue(u1.priceTF != null);
		assertTrue(u1.read_comment != null);
		assertTrue(u1.search != null);
		assertTrue(u1.searchTF != null);
		assertTrue(u1.sort != null);
		assertTrue(u1.version != null);
		assertTrue(u1.versionTF != null);
	}

	/**
	 * Checks if apps are written to the file
	 */
	@Test
	void testWriteAppsToFile() {
		User u1 = new User();

		int lines = u1.appTA.getLineCount();
		
		try {
			u1.writeAppsToFile("Facebook", "Pics with friends", "Facebook", "IOS", "facebook.com", "0.0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		u1.readAppsFromFile(new File("app.txt"));
		
		assertTrue(u1.appTA.getLineCount() == lines + 1);
	}

	/**
	 * Tests if items are being retrived from the file
	 */
	@Test
	void testReadAppsFromFile() {
		User u1 = new User();
		
		int lines = u1.appTA.getLineCount();
		
		try {
			u1.writeAppsToFile("Facebook", "Pics with friends", "Facebook", "IOS", "facebook.com", "0.0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		u1.readAppsFromFile(new File("app.txt"));
		u1.readAppsFromFile(new File("apps.txt"));
		assertTrue(u1.appTA.getLineCount() == lines + 1);
	}
}
