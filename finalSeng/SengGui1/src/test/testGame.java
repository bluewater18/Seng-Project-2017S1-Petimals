package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Game;
import main.Pet;
import main.Player;
import main.Specie;

public class testGame {
	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}
	
	
	@Test
	public void testAddPlayer() {
		game.addPlayer("player1", 2);
		assertFalse(game.getPlayersArray().isEmpty());
		
	}
	
	@Test
	public void testStart(){
		game.addPlayer("player1", 2);
		game.start();
		assertFalse(game.getPlayersArray().get(0).toys.isEmpty());
		assertFalse(game.getPlayersArray().get(0).food.isEmpty());
		assertTrue(game.getToysArray().size() == 6);
		assertTrue(game.getFoodsArray().size() == 6);
		
	}

	
	@Test
	/**
	 * 
	 */
	public void testdayScore(){
		game.addPlayer("player1", 1);
		game.getPlayersArray().get(0).addPet(1);
		assertTrue(game.getPlayersArray().get(0).getScore()==0);
		game.dayScore();
		int healthyScore = game.getPlayersArray().get(0).getScore();
		
		game.getPlayersArray().clear();
		game.addPlayer("player1", 1);
		game.getPlayersArray().get(0).addPet(1);
		game.getPlayersArray().get(0).pets.get(0).makeSick(true);
		game.dayScore();
		int unHealthyScore = game.getPlayersArray().get(0).getScore();
		assertTrue(healthyScore > unHealthyScore);
		
	}
	
	
	
	@Test
	/**
	 * Tests managepets in the case the where pet is alive when ran
	 */
	public void testManagePetsLiving(){
	Player p = new Player("John");
	p.addPet(0);
	Pet preChange = new Pet(Specie.getTypeFromInt(0));
	game.managePets(p);
	Pet postChange = p.pets.get(0);
	assertTrue(preChange.getHealthInt() > postChange.getHealthInt());
	assertTrue(preChange.getHungerInt() > postChange.getHungerInt());
	assertTrue(preChange.getEnergyInt() < postChange.getEnergyInt());
	assertTrue(preChange.getToiletInt() < postChange.getToiletInt());
	assertTrue(preChange.getMoodInt() > postChange.getMoodInt());
}
	
	
	@Test
	/**
	 * Tests managePets in the case where pet is dead when ran
	 */
	public void testManagePetsDead(){
	Player p = new Player("John");
	p.addPet(0);
	p.pets.get(0).death();
	Pet preChange = new Pet(Specie.getTypeFromInt(0));
	preChange.death();
	game.managePets(p);
	Pet postChange = p.pets.get(0);
	assertTrue(preChange.getHealthInt() == postChange.getHealthInt());
	assertTrue(preChange.getHungerInt() == postChange.getHungerInt());
	assertTrue(preChange.getEnergyInt() == postChange.getEnergyInt());
	assertTrue(preChange.getToiletInt() == postChange.getToiletInt());
	assertTrue(preChange.getMoodInt() == postChange.getMoodInt());
}
	
	
	
	
}
