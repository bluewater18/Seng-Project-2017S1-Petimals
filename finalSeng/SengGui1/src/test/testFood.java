package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import main.Food;
import main.Toy;

public class testFood {
	Food food;
	Food foodClone;
	Food testFood;
	Food testFood1;
	Food testFood2;
	@Before
	public void setUp()throws Exception{
		testFood = new Food("name", 25, 5);
	}
	@Test
	public void testConstructor3Args() {
		assertTrue(testFood.getName() == "name");
		assertTrue(testFood.getCost() == 25);
		assertTrue(testFood.getFoodValue() == 5);
		assertTrue(testFood.getImgString() == "/64.png"); 
	}
	@Test
	public void testEquals(){
		Food food1 = new Food("name", 25, 5);
		Food food2 = new Food("name", 20, 5);
		Food food3 = new Food("name1",25, 5);
		Toy toy1 = new Toy("name", 25, 5);
		assertNotEquals(food1,food3);
		assertNotEquals(food1,null);
		assertNotEquals(food1,toy1);
		assertNotEquals(food2,toy1);
		assertEquals(food1,food2);
		assertTrue(food1.hashCode() == food2.hashCode());
		assertFalse(food1.hashCode() == food3.hashCode());
	
	}
	@Test
	public void testConstructor4Args(){
		testFood1 = new Food("name", 25, 5, "name.png");
		assertTrue(testFood1.getName() == "name");
		assertTrue(testFood1.getCost() == 25);
		assertTrue(testFood1.getFoodValue() == 5);
		assertTrue(testFood1.getImgString() == "name.png"); 
		
	}
	@Test
	public void testConstructor1Args(){
		testFood2 = new Food("name");
		assertTrue(testFood2.getName() == "name");
	}
	@Test
	public void testtoString(){
		testFood1 = new Food("name", 25, 5, "name.png");
		assertTrue(testFood1.shopString() instanceof String);	
		}
	
	@Test
	public void testConstructorClone(){
		food = new Food("name", 10,5);
		foodClone = new Food(food);
		assertTrue(food != foodClone);
		assertTrue(food.getName() == foodClone.getName());
		assertTrue(food.getCost() == foodClone.getCost());
		assertTrue(food.getFoodValue() == foodClone.getFoodValue());
		assertTrue(food.getImgString() == foodClone.getImgString());
		
	}
	

}