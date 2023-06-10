package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {

	VendingMachineItem test1;
	VendingMachineItem empty;
	VendingMachineItem constructorTest;
	
	@Before
	public void setUp() throws Exception {
		test1 = new VendingMachineItem("Coke", 0);
		empty = new VendingMachineItem("", 1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Tests case where the price is less than 0.
	@Test(expected = VendingMachineException.class)
	public void testNegativePrice() {
		new VendingMachineItem("Coke", -.01);
	}
	
	@Test
	public void testVendingMachineItem() {
		constructorTest = new VendingMachineItem("Lays", 1.50);
		//Tests to ensure name is assigned correctly
		assertEquals("Lays", constructorTest.getName());
		//Tests to see if value is what it should be
		assertEquals(1.50, constructorTest.getPrice(), .001);
	}

	@Test
	public void testGetName() {
		//Tests that the method is acting as it should
		assertEquals("Regular case", "Coke", test1.getName());
		//Tests the method on an empty string
		assertEquals("Empty string case", "" , empty.getName());
	}

	@Test
	public void testGetPrice() {
		//Tests the get price method when its value is 0.
		assertEquals("Price is 0.", 0, test1.getPrice(), .01);
		//tests the get price method under normal conditions
		assertEquals("Price is greater than 0.", 1, empty.getPrice(), .01);
	}

}
