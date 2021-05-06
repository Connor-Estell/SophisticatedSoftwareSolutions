import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: AppTest.java
 * Description: This class provides the JUnit Tests cases
 * for the App object in the Appdex application. These
 * tests focus on creating constructors and methods.
 */
class AppTest {
	// Creates two app objects
	App a1 = new App("Facebook", "Stuff", "Facebook", "IOS", "facebook.com", 0.00);
	App a2 = new App("Snapchat", "Stuff", "SNAP", "IOS", "snapchat.com", 0.00);
	
	/**
	 * Tests the app construtor
	 */
	@Test
	void testApp() {
		assertTrue(a1 != null);
		assertTrue(a1.desc.equals("Stuff"));
		assertTrue(a1.link.equals("facebook.com"));
		assertTrue(a1.name.equals("Facebook"));
		assertTrue(a1.price - 0.0 < 0.000001);
		assertTrue(a1.ver.equals("IOS"));
		assertTrue(a1.getComments().size() == 0);
	}

	/**
	 * Tests the printout of the formatted string
	 */
	@Test
	void testPrint() {
		assertEquals(a1.print(), "Application name: Facebook : Description: Stuff : Organization: Facebook : Versions: IOS : Link: facebook.com : Price: 0.0");
		assertFalse(a1.print().equals("Application name: test : Description: none : Organization: org : Versions: IOS : Link: example.com : Price: 4.0"));
		assertEquals(a2.print(), "Application name: Snapchat : Description: Stuff : Organization: SNAP : Versions: IOS : Link: snapchat.com : Price: 0.0");
	}

	/**
	 * Tests an output string is formatted properly
	 */
	@Test
	void testPrintData() {
		assertEquals(a1.printData(), "Facebook,Stuff,Facebook,IOS,facebook.com,0.0");
		assertFalse(a1.printData().equals("Snapchat,Stuff,SNAP,IOS,snapchat.com,0.0"));
	}

	/**
	 * Tests that app is saved to the file
	 */
	@Test
	void testSave() {
		a1.save();
	}

	/**
	 * Test that the app is read from a file
	 */
	@Test
	void testRead() {
		a1.read();
	}

	/**
	 * Tests that comments can be retrieved
	 */
	@Test
	void testGetComments() {
		int size = a1.getComments().size();
		
		a1.addComment("Example");
		assertEquals(a1.getComments().size(), size + 1);
	}

	/**
	 * Adds a comment to the list
	 */
	@Test
	void testAddComment() {
		int size = a1.getComments().size();
		
		a1.addComment("Example");
		
		assertEquals(a1.getComments().size(), size + 1);
		
		assertEquals(a1.getComments().get(0), "Example");
		
		assertFalse(!a1.getComments().get(0).equals("Example"));
	}

	/**
	 * Tests that the name of the app can be displayed
	 */
	@Test
	void testPrintName() {
		assertEquals("Facebook", a1.printName());
		assertFalse(a1.printName().equals("Snapchat"));
		
		assertEquals("Snapchat", a2.printName());
		assertFalse(a2.printName().equals("test"));
	}

}
