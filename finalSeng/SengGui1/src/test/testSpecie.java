package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Food;
import main.Specie;
import main.Toy;

public class testSpecie {
		Specie specie;
	@Before
	public void setUp() throws Exception {
		specie = Specie.ROCK;
		
	}

	@Test
	public void testGetTypeFromInt() {
		assertTrue(Specie.getTypeFromInt(1) == Specie.ROCK);
		assertTrue(Specie.getTypeFromInt(2) == Specie.PENGUIN);
		assertTrue(Specie.getTypeFromInt(3) == Specie.WOLF);
		assertTrue(Specie.getTypeFromInt(4) == Specie.CAT);
		assertTrue(Specie.getTypeFromInt(5) == Specie.DOG);
		assertTrue(Specie.getTypeFromInt(6) == Specie.RABBIT);
		assertTrue(Specie.getTypeFromInt(100) == Specie.RABBIT);	
	}
	
	@Test
	public void testGetters(){
		assertTrue(specie.getImage96() instanceof String);
		assertTrue(specie.getImage64() instanceof String);
		assertTrue(specie.getDeadImg() instanceof String);
		assertTrue(specie.getFavFood() instanceof Food);
		assertTrue(specie.getFavToy() instanceof Toy);
	}
	

}
