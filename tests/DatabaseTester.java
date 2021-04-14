import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

class DatabaseTester {
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
	
	@Test
	void testSearchAndSaveToFile() throws IOException {
		
		// Wipe any previous info in file
		PrintWriter writer = new PrintWriter("Log-ins.txt");
		writer.print("");
		writer.close();
		
		// Create user 1
		Database.saveToFile(goodLoginUserName, goodLoginPassword);
		// Create user 3
		Database.saveToFile(secondGoodUserName, goodPass);
		// Make sure user 1 is valid
		assertTrue(Database.search(goodLoginUserName));
		// Make sure user 2 is not valid
		assertFalse(Database.search(badLoginUserName));
		// Make sure you can't search for passwords
		assertFalse(Database.search(badPass));
		// back slash
		assertFalse(Database.search(backlashNuserName));
	}
	
	@Test
	void testLogin() throws IOException {
		
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

}
