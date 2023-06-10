package edu.towson.cis.cosc442.project4.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;

	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		r2 = new Recipe();
		r2.setPrice(100);
		r2.setAmtCoffee(100);
		r2.setAmtMilk(100);
		r2.setAmtSugar(100);
		r2.setAmtChocolate(100);
		
		r3 = new Recipe();
		r3.setName("Absurdly Low");
		r3.setPrice(-10);
		r3.setAmtCoffee(-10);
		r3.setAmtMilk(-10);
		r3.setAmtSugar(-10);
		r3.setAmtChocolate(-10);
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}
	
	public void testCorrectRecipe1() {
		assertEquals("Absurdly Low", r3.toString());
		assertEquals(0, r3.getAmtChocolate());
		assertEquals(0, r3.getAmtCoffee());
		assertEquals(0, r3.getAmtMilk());
		assertEquals(0, r3.getAmtSugar());
		assertEquals(0, r3.getPrice());
	}
	
	public void testFaultRecipeComparison1() {
		assertFalse(r3.equals(r2));
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}

	public void testAddInventory1() {
		assertTrue(cm.addInventory(5, 5, 5, 5));
		assertEquals(20, i.getChocolate());
		assertEquals(20, i.getCoffee());
		assertEquals(20, i.getMilk());
		assertEquals(20, i.getSugar());
	}
	
	public void testAddInventory2() {
		assertFalse(cm.addInventory(-10, 0, 0, 0));
	}
	
	public void testAddRecipe2() {
		cm.addRecipe(r1);
		assertFalse(cm.addRecipe(r1));
	}
	
	public void testcheckInventory() {
		assertEquals(15, cm.checkInventory().getChocolate());
		assertEquals(15, cm.checkInventory().getCoffee());
		assertEquals(15, cm.checkInventory().getMilk());
		assertEquals(15, cm.checkInventory().getSugar());
	}
	
	public void testPurchaseBeverage1() {
		cm.addRecipe(r1);
		assertEquals(0, cm.makeCoffee(r1, 50));
	}
	
	public void testPurchaseBeverage2() {
		cm.addRecipe(r1);
		assertEquals(0, cm.makeCoffee(r1, 0));
	}
	
	public void testPurchaseBeverage3() {
		cm.addRecipe(r1);
		i.setChocolate(0);
		i.setCoffee(0);
		i.setMilk(0);
		i.setSugar(0);
		assertEquals(100, cm.makeCoffee(r1, 100));
	}
	
	public void testPurchaseBeverage4() {
		cm.addRecipe(r2);
		assertEquals(100, cm.makeCoffee(r2, 100));
	}
	
	public void testNegInventory() {
		i.setChocolate(-1);
		i.setCoffee(-1);
		i.setMilk(-1);
		i.setSugar(-1);
		assertEquals(0, i.getChocolate());
		assertEquals(0, i.getCoffee());
		assertEquals(0, i.getMilk());
		assertEquals(0, i.getSugar());
	}
	
	public void testInventoryToString() {
		assertEquals("Coffee: " + 15 + System.lineSeparator() +
				"Milk: " + 15 + System.lineSeparator() +
				"Sugar: " + 15 + System.lineSeparator() +
				"Chocolate: " + 15 + System.lineSeparator(), i.toString());
	}
	
	public void testGetRecipeName() {
		cm.addRecipe(r1);
		assertSame(r1, cm.getRecipeForName("Coffee"));
	}
}