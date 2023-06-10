package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine myMachine = new VendingMachine();
	VendingMachineItem sunchips = new VendingMachineItem("Sunchips", 1.25);
	VendingMachineItem lays = new VendingMachineItem("Lays", 1.10);
	VendingMachineItem iphone = new VendingMachineItem("Iphone", 1000.78);
	VendingMachineItem tilapia = new VendingMachineItem("Tilapia", 35.41);
	VendingMachineItem weddingRing = new VendingMachineItem("Diamond Ring", 10.0001);
	VendingMachineItem air = new VendingMachineItem("Air", 0);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Tests the exception where an item is added to an occupied slot
	@Test(expected = VendingMachineException.class)
	public void testAddItemException(){
		myMachine.addItem(sunchips, "B");
		myMachine.addItem(iphone, "B");
	}
	
	//Tests the exception where an invalid code is given
	@Test(expected = VendingMachineException.class)
	public void testInvalidCode() {
		myMachine.addItem(lays, "G");
	}

	@Test
	public void testAddItem() {
		//Testing to see that all slots are empty upon instantiation
		assertEquals(null, myMachine.getItem("A"));
		assertEquals(null, myMachine.getItem("B"));
		assertEquals(null, myMachine.getItem("C"));
		assertEquals(null, myMachine.getItem("D"));
		//Testing all 4 slots to ensure they are working properly.
		myMachine.addItem(weddingRing, "A");
		assertSame("Testing Slot A", weddingRing, myMachine.getItem("A"));
		myMachine.addItem(sunchips, "B");
		assertSame("Testing Slot B", sunchips, myMachine.getItem("B"));
		myMachine.addItem(iphone, "C");
		assertSame("Testing Slot C", iphone, myMachine.getItem("C"));
		myMachine.addItem(tilapia, "D");
		assertSame("Testing Slot D", tilapia, myMachine.getItem("D"));
	}

	//Tests the case where an item is removed from an empty slot
	@Test(expected = VendingMachineException.class)
	public void testRemoveEmptySlot() {
		myMachine.removeItem("A");
	}
	
	//Tests the case where an incorrect code is given for item removal
		@Test(expected = VendingMachineException.class)
		public void testRemoveInvalidSlot() {
			myMachine.removeItem("Y");
		}
	
	@Test
	public void testRemoveItem() {
		myMachine.addItem(iphone, "A");
		myMachine.removeItem("A");
		//Tests the standard case for an item being removed
		assertEquals("Regular Case", null, myMachine.getItem("A"));
	}
	
	//Tests the case where a negative value is inserted into the machine
	@Test(expected = VendingMachineException.class)
	public void testInsertNegative() {
		myMachine.insertMoney(-1.50);
	}

	@Test
	public void testInsertMoney() {
		myMachine.insertMoney(0);
		//Tests the case where 0 is inserted into the machine
		assertEquals("Zero Case", 0, myMachine.getBalance(), .01);
		myMachine.insertMoney(1.00);
		myMachine.insertMoney(1.50);
		//Testing the postcondition where the balance is the previous balance + amount.
		assertEquals("Balance = balance + insert", 2.50, myMachine.getBalance(), .01);
	}

	@Test
	public void testGetBalance() {
		//Tests that the vending machine always starts with balance 0
		assertEquals("Initial Balance", 0, myMachine.getBalance(), .01);
		myMachine.insertMoney(1.75);
		//Tests the regular case where it should work
		assertEquals("Balance after insert", 1.75, myMachine.getBalance(), .01);
		//Tests the method after getBalance has been called to ensure the value remains the same
		assertEquals("Balance after insert", 1.75, myMachine.getBalance(), .01);
	}

	@Test
	public void testMakePurchase() {
		double expected = 1050 - 1000.78;
		
		//Tests the case where a purchase is being made on an empty slot
		assertFalse("Empty slot purchase", myMachine.makePurchase("A"));
		myMachine.addItem(iphone, "A");
		//Tests the case where there are insufficient funds
		assertFalse("Insufficient funds purchase", myMachine.makePurchase("A"));
		myMachine.addItem(air, "B");
		//Tests the case where an item of price 0 is purchased
		assertTrue(myMachine.makePurchase("B"));
		myMachine.insertMoney(1050);
		//Tests a valid purchase being made
		assertTrue("Valid purchase", myMachine.makePurchase("A"));
		//Tests to see that the item is removed after purchase
		assertEquals("Item removed", null, myMachine.getItem("A"));
		//Tests to see the balance has changed as a result
		assertEquals("Correct Balance after purchase", expected, myMachine.getBalance(), .01);
	}

	@Test
	public void testReturnChange() {
		//Testing for negative values in the vending machine
		assertFalse(myMachine.getBalance()<0);
		myMachine.insertMoney(.75);
		//Test that the correct change is being returned
		assertEquals("Testing regular case", .75, myMachine.returnChange(), .01);
		//Tests that the balance is being reset
		assertEquals("Testing balance reset", 0, myMachine.getBalance(), .01);
	}

}
