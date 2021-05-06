import java.util.Comparator;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Price_Compare.java
 * Description: This class compares the prices of app objects,
 * and is used in the implementation of the sorting feature
 * on the HomePage.
 */
public class Price_Compare implements Comparator<App>{

	@Override
	public int compare(App a, App b) {
		if (a.price > b.price) return 1;
		if (a.price < b.price) return -1;
		return 0;
	}

}