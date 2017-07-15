package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import main.RandomEvents;

public class testRandomEvents {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetRandomPercent() {
		assertTrue(RandomEvents.getRandomPercent(100));
		assertFalse(RandomEvents.getRandomPercent(0));
		ArrayList<Boolean> outcomes = new ArrayList<Boolean>();
		for(int i= 0; i <100; i++){
			outcomes.add(RandomEvents.getRandomPercent(50));
		}
		assertTrue(outcomes.contains(true));
		assertTrue(outcomes.contains(false));
	}
	
	
	@Test
	public void testGetRandomInt(){
		ArrayList<Integer> outcomes = new ArrayList<Integer>();
		for(int i= 0; i <500; i++){
			outcomes.add(RandomEvents.getRandomInt(5));
		}
		assertTrue(outcomes.contains(0));
		assertTrue(outcomes.contains(1));
		assertTrue(outcomes.contains(2));
		assertTrue(outcomes.contains(3));
		assertTrue(outcomes.contains(4));
		assertFalse(outcomes.contains(5));
		assertFalse(outcomes.contains(-1));
		
	}

}
