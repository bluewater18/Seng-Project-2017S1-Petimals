package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Food;
import main.Toy;

public class testToy {
	Toy toy;
	Toy toyClone;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testConstructors() {
		toy = new Toy("name", 10,5);
		toyClone = new Toy("name", 10, 5, "/img location");
		assertTrue(toy.getImgString() != toyClone.getImgString());
		
	}
	
	@Test
	public void testConstructorClone(){
		toy = new Toy("name", 10,5);
		toyClone = new Toy(toy);
		assertTrue(toy != toyClone);
		assertTrue(toy.getName() == toyClone.getName());
		assertTrue(toy.getCost() == toyClone.getCost());
		assertTrue(toy.getDurability() == toyClone.getDurability());
		assertTrue(toy.getImgString() == toyClone.getImgString());
	}
	
	@Test
	public void testEquals(){
		toy = new Toy("name", 10,5);
		toyClone = new Toy("name", 15, 2);
		Food food = new Food("name", 10, 5);
		
		assertNotEquals(toy, null);
		assertNotEquals(toy, food);
		assertEquals(toy, toyClone);
		
		assertTrue(toy.hashCode() == toyClone.hashCode());
		assertFalse(toy.hashCode() == food.hashCode());
		
	}
	
	@Test
	public void testSetDurability(){
		toy = new Toy("name",10,5);
		assertTrue(toy.getDurability()==5);
		toy.setDurability(0);
		assertTrue(toy.getDurability()==0);
	}

}
