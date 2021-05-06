import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Comment_AddTest.java
 * Description: This class provides the JUnit Tests cases
 * for the User object in the Appdex application. These
 * tests focus on creating constructors and methods.
 */
class Comment_AddTest {

	/**
	 * Test that the Comment_Add is made properly
	 */
	@Test 
	void testComment_Add() {
		Comment_Add ca1 = null;
		
		assertEquals(ca1, null);
		
		ca1 = new Comment_Add();
		
		assertTrue(ca1 != null);
		assertTrue(ca1.sub != null);
		assertTrue(ca1.tname != null);
	}
	
	// Tests that coments are created and read
	@Test
	void testAppListCreateList() throws IOException {
		Comment_Add ca1 = new Comment_Add();
		ca1.createList();
		ca1.appList();
	}
}
