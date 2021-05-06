import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: DatabaseTest.java
 * Description: This class provides the JUnit Tests cases
 * for the Database object in the Appdex application. These
 * tests focus on creating constructors and methods.
 */
class DatabaseTest {
	// User 1 (for testing positive outcomes)
	String goodLoginUserName = "123abc!@# |";
	String goodLoginPassword = "123abc!@# |1234";
		
	// User 2 (for testing negative outcomes, aka not a real account)
	String badLoginUserName = "I'm not in the database";
	String badLoginPassword = "I'm also not in the database";
		
	// User 3 (for testing username password combinations)
	String secondGoodUserName = "I'm in the database";
	String badPass = "But I am not";
	String goodPass = "I am in the database";
		
	// User 4 (trying to break with \n)
	String backlashNuserName = "lmao \n";
	String backlashNpassword = "lmao \n";

	/**
	 * Tests if users are saved to the file
	 */
	@Test
	void testSaveToFile() throws IOException {
		// Wipe any previous info in file
		PrintWriter writer;
		try {
			writer = new PrintWriter("Log-ins.txt");
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Create user 1
		Database.saveToFile(goodLoginUserName, goodLoginPassword);
		// Create user 3
		Database.saveToFile(secondGoodUserName, goodPass);
	}

	/**
	 * Searches for a user in the file
	 * @throws IOException throws if the file is invalid
	 */
	@Test
	void testSearch() throws IOException {
		
		// Make sure user 1 is valid
		assertTrue(Database.search(goodLoginUserName));
		// Make sure user 2 is not valid
		assertFalse(Database.search(badLoginUserName));
		// Make sure you can't search for passwords
		assertFalse(Database.search(badPass));
		// back slash
		assertFalse(Database.search(backlashNuserName));
	}

	/**
	 * Tests if a user can login
	 * @throws IOException throws if file is invalid
	 */
	@Test
	void testLogging_in() throws IOException {
		// Wipe any previous info in file
		PrintWriter writer = new PrintWriter("Log-ins.txt");
		writer.print("");
		writer.close();
				
		// Create user 1
		Database.saveToFile(goodLoginUserName, goodLoginPassword);
		// Create user 2
		Database.saveToFile(secondGoodUserName, goodPass);
		// Make sure user 1 is allowed to login
		assertTrue(Database.logging_in(goodLoginUserName, goodLoginPassword));
		// Make sure a non-valid account can't login (user 2)
		assertFalse(Database.logging_in(badLoginUserName, badLoginPassword));
		// Make sure a valid username and bad password can't login
		assertFalse(Database.logging_in(secondGoodUserName, badPass));
		// Make sure reversing username and password doesn't let you login
		assertFalse(Database.logging_in(goodLoginPassword, goodLoginUserName));
		// Make sure a bad username but good password doesn't login
		assertFalse(Database.logging_in(badLoginUserName, goodPass));
	}

	/**
	 * Filters app by a specific version
	 * @throws IOException throws if a file is invalid
	 */
	@Test
	void testFilter_version() throws IOException {
		// Creates file
		File f1 = null;
				
		// Ensures it is null
		assertTrue(f1 == null);
		assertFalse(f1 != null);
		
		// Returns filtered file
		f1 = Database.filter_version("IOS");
				
		// Ensures it is not null
		assertTrue(f1 != null);
	}

	/**
	 * Orders listing in alphabetical order descending
	 * @throws ClassNotFoundException if class of object does not exist
	 * @throws IOException if file is invalid
	 */
	@Test
	void testAlphabet_sort_desc() throws ClassNotFoundException, IOException {
		// Creates file
		File f1 = null;
		
		// Ensures it is null
		assertTrue(f1 == null);
		
		// Returns filtered file
		f1 = Database.alphabet_sort_desc();
		
		// Ensures it is not null
		assertTrue(f1 != null);
	}

	/**
	 * Orders listing in alphabetical order ascending
	 * @throws ClassNotFoundException if class of object does not exist
	 * @throws IOException if file is invalid
	 */
	@Test
	void testAlphabet_sort_asc() throws ClassNotFoundException, IOException {
		// Creates file
		File f1 = null;
				
		// Ensures it is null
		assertTrue(f1 == null);
				
		// Returns filtered file
		f1 = Database.alphabet_sort_asc();
				
		// Ensures it is not null
		assertTrue(f1 != null);
	}

	/**
	 * Orders listing by price ascending
	 * @throws ClassNotFoundException if class of object does not exist
	 * @throws IOException if file is invalid
	 */
	@Test
	void testLow_price() throws ClassNotFoundException, IOException {
		// Creates file
		File f1 = null;
						
		// Ensures it is null
		assertTrue(f1 == null);
						
		// Returns filtered file
		f1 = Database.low_price();
						
		// Ensures it is not null
		assertTrue(f1 != null);
	}

	/**
	 * Orders listing by price descending
	 * @throws ClassNotFoundException if class of object does not exist
	 * @throws IOException if file is invalid
	 */
	@Test
	void testHigh_price() throws ClassNotFoundException, IOException {
		// Creates file
		File f1 = null;
								
		// Ensures it is null
		assertTrue(f1 == null);
								
		// Returns filtered file
		f1 = Database.high_price();
								
		// Ensures it is not null
		assertTrue(f1 != null);
	}

	/**
	 * Compares two app objects
	 */
	@Test
	void testCompare() {
		App a1 = new App("Facebook", "Stuff", "FaceBook", "IOS", "facebook.com", 0.00);
		App a2 = new App("Instagram", "Stuff", "FaceBook", "IOS", "facebook.com", 0.00);
		App a3 = null;
		
	}

}
