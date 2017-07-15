package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Player;
import main.Specie;

public class testPlayer {
	Player player;
	@Before
	public void setUp() throws Exception {
		player = new Player("john");
	}

	@Test
	public void testMoney() {
		assertTrue(player.getBalance()==100);
		player.spend(50);
		assertTrue(player.getBalance()==50);
		player.setBalance(90);
		assertTrue(player.getBalance()==90);
		player.setBalance(-100);
		assertTrue(player.getBalance()==0);
		player.setBalance(0);
		assertTrue(player.getBalance()==0);
		
		player.giveMoney(20);
		assertTrue(player.getBalance() == 0 + 20);
		
		player.spend(40);
		assertTrue(player.getBalance()==0);
	}
	
	@Test
	public void testScore(){
		assertTrue(player.getScore() == 0);
		player.addScore(52.43);
		assertTrue(player.getScore()==52);
		player.setScore(0);
		assertTrue(player.getScore()==0);
		player.addScore(5);
		assertTrue(player.getScore()==5);
	}
	
	@Test
	public void testBasics(){
		player.setPetNum(1);
		assertTrue(player.getPetNum() == 1);
		assertTrue(player.getName() instanceof String);
		player.addPetWithName(Specie.ROCK, "rocky");
		assertTrue(player.toString() instanceof String);
		assertTrue(player.pets.size() == 1);
		player.removePets();
		assertTrue(player.pets.size() == 0);
		
		
	}
	

}
