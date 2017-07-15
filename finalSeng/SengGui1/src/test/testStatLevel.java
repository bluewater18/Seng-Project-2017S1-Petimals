package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Energy;
import main.Health;
import main.Hunger;
import main.Mood;
import main.StatLevel;
import main.Toilet;
import main.Weight;

public class testStatLevel {

	StatLevel testStat;
	@Before
	public void setUp() throws Exception {
		testStat = new Health(100.00,1.00);
	}

	@Test
	public void testSetLevel() {
		testStat.setLevel(0);
		assertTrue(testStat.getLevel() == 0);
		testStat.setLevel(-12.55);
		assertTrue(testStat.getLevel() == 0);
		testStat.setLevel(510.6);
		assertTrue(testStat.getLevel() == 100);
		testStat.setLevel(99);
		assertTrue(testStat.getLevel() == 99);
	}
	
	
	@Test
	public void testExntendedClasses(){
		Health health = new Health(100.00,1.00);
		assertTrue(health.getLevel() == 100);
		assertTrue(health.getModifier() == 1.00);
		Mood mood = new Mood(90.00, 1.25);
		assertTrue(mood.getLevel() == 90);
		assertTrue(mood.getModifier() == 1.25);
		Hunger hunger = new Hunger(100.00,0.75);
		assertTrue(hunger.getLevel() == 100);
		assertTrue(hunger.getModifier() == 0.75);
		Weight weight =  new Weight(50.00);
		assertTrue(weight.getLevel() == 50);
		assertTrue(weight.getModifier() == 1.00);
		Energy energy = new Energy(100.00,1.00);
		assertTrue(energy.getLevel() == 100);
		assertTrue(energy.getModifier() == 1.00);
		Toilet toilet = new Toilet(100.00);
		assertTrue(toilet.getLevel() == 100);
		assertTrue(toilet.getModifier() == 1.00);
		
	}
	
	@Test
	public void testLevelChange(){
		testStat.setLevel(0);
		testStat.changeLevel(104);
		assertTrue(testStat.getLevel() == 100);
		
		testStat.setLevel(0);
		testStat.changeLevel(32);
		assertTrue(testStat.getLevel() == 32);
		
		testStat.setLevel(0);
		testStat.changeLevel(-5);
		assertTrue(testStat.getLevel() == 0);
		
		testStat.setLevel(0);
		testStat.changeLevel(0);
		assertTrue(testStat.getLevel() == 0);
	}

}
