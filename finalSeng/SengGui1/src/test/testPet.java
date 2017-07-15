package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Food;
import main.Pet;
import main.Player;
import main.Specie;
import main.Toy;

public class testPet {
	Pet pet;
	@Before
	public void setUp() throws Exception {
		//Rock used as it has only 1.00 multipliers for all stats
		pet = new Pet(Specie.ROCK);
	}

	@Test
	public void testDead() {
		assertFalse(pet.getDead());
		assertTrue(pet.isReviveable());
		assertTrue(pet.getHealthInt()>0);
		pet.death();
		assertTrue(pet.getDead());
		assertTrue(pet.isReviveable());
		assertTrue(pet.getHealthInt()==0);
		pet.revive();
		assertFalse(pet.getDead());
		assertFalse(pet.isReviveable());
		assertTrue(pet.getHealthInt()>0);
		
	}
	
	@Test
	public void testToilet(){
		Pet petBefore = new Pet(Specie.ROCK);
		pet.goToilet();
		assertTrue((petBefore.getActions() - 1) == pet.getActions());
		assertTrue((petBefore.getToiletInt()-20)==pet.getToiletInt());
		assertTrue((petBefore.getWeightInt()-2)==pet.getWeightInt());
	}
	
	@Test
	public void testSleep(){
		Pet petBefore = new Pet(Specie.ROCK);
		pet.sleep();
		assertTrue((petBefore.getActions() - 1) == pet.getActions());
		assertTrue((petBefore.getEnergyInt()+20)==pet.getEnergyInt());
		assertTrue((petBefore.getHealthInt()+5)==pet.getHealthInt());
		assertTrue((petBefore.getHungerInt()-5)==pet.getHungerInt());
		
	}
	
	@Test
	public void testPlay(){
		Pet petBefore = new Pet(Specie.ROCK);
		Toy toy = new Toy("toy", 10, 5);
		Toy toyClone = new Toy("toy",10,5);
		Toy rockFavToy = new Toy("Pebble", 10,5);
		
		pet.play(toy);
		assertTrue((petBefore.getActions() - 1) == pet.getActions());
		assertTrue((petBefore.getEnergyInt()-15)==pet.getEnergyInt());
		assertTrue((petBefore.getMoodInt()+10)==pet.getHealthInt());
		assertTrue(toy.getDurability() == (toyClone.getDurability()-pet.getSpecie().getToyDamage()));
		
		pet = new Pet(Specie.ROCK);
		pet.play(rockFavToy);
		assertTrue((petBefore.getActions() - 1) == pet.getActions());
		assertTrue((petBefore.getEnergyInt()-10)==pet.getEnergyInt());
		assertTrue((petBefore.getMoodInt()+20)==pet.getMoodInt());
		assertTrue((petBefore.getHealthInt()+5)==pet.getHealthInt());
		
		
	}
	
	@Test
	public void testRemovalToy(){
		Toy weakToy = new Toy("toy",10,1);
		Player p = new Player("John");
		p.addToy(weakToy);
		p.addPetWithName(Specie.ROCK, "Johns Rock");
		assertTrue(p.toys.size() == 1);
		p.pets.get(0).play(p.toys.get(0));
		assertTrue(p.toys.size() == 0);
		
	}
	
	
	@Test
	public void testFeed(){
		Player p = new Player("John");
		p.addPetWithName(Specie.ROCK, "Rocky");
		Pet petBefore = new Pet(Specie.ROCK);
		Food food = new Food("food", 10,1);
		Food foodClone = new Food("food", 10,1);
		Food rockFavFood = new Food("Algae",10,1);
		Food rockFavFoodClone = new Food("Algae",10,1);
		p.addFood(food);
		
		assertTrue(p.food.size()==1);
		p.pets.get(0).dailyChangeStats();
		petBefore.dailyChangeStats();
		
		p.pets.get(0).feed(p.food.get(0));
		
		assertTrue(p.food.size() == 0);
		assertTrue((petBefore.getActions() - 1) == p.pets.get(0).getActions());
		assertTrue((petBefore.getHungerInt() + (foodClone.getFoodValue()*3))==p.pets.get(0).getHungerInt());
		assertTrue((petBefore.getWeightInt()+foodClone.getFoodValue())==p.pets.get(0).getWeightInt());
		assertTrue((petBefore.getToiletInt()+(foodClone.getFoodValue()*2))==p.pets.get(0).getToiletInt());
		
		p.pets.clear();
		p.addPetWithName(Specie.ROCK, "Rocky");
		p.addFood(rockFavFood);
		
		p.pets.get(0).dailyChangeStats();
		
		p.pets.get(0).feed(p.food.get(0));
		
		assertTrue(p.food.size() == 0);
		assertTrue((petBefore.getActions() - 1) == p.pets.get(0).getActions());
		assertTrue((petBefore.getHungerInt() + (rockFavFoodClone.getFoodValue()*4))==p.pets.get(0).getHungerInt());
		assertTrue((petBefore.getWeightInt()+rockFavFoodClone.getFoodValue()+2)==p.pets.get(0).getWeightInt());
		assertTrue((petBefore.getToiletInt()+(rockFavFoodClone.getFoodValue()*2))==p.pets.get(0).getToiletInt());
		assertTrue((petBefore.getMoodInt()+10) == p.pets.get(0).getMoodInt());
		
		
		
	}
	@Test
	public void testDailyChangeStats(){
		Pet petBefore = new Pet(Specie.ROCK,"test Name");
		pet.dailyChangeStats();
		assertTrue((petBefore.getHealthInt() - 15)==pet.getHealthInt());
		assertTrue((petBefore.getHungerInt() - 15)==pet.getHungerInt());
		assertTrue((petBefore.getEnergyInt() + 10)==pet.getEnergyInt());
		assertTrue((petBefore.getToiletInt() + 10) == pet.getToiletInt());
		assertTrue((petBefore.getMoodInt() - 12)==pet.getMoodInt());
	}
	
	@Test
	public void testDiscipline(){
		Pet petBefore = new Pet(Specie.ROCK);
		assertFalse(pet.getMisbehaving());
		pet.makeMisbehave(true);
		assertTrue(pet.getMisbehaving());
		pet.discipline();
		assertFalse(pet.getMisbehaving());
		assertTrue((petBefore.getActions() - 1) == pet.getActions());
		assertTrue((petBefore.getMoodInt() - 20)==pet.getMoodInt());
	}
	
	@Test
	public void testVet(){
		Player p = new Player("John");
		Pet petBefore = new Pet(Specie.ROCK);
		p.addPetWithName(Specie.ROCK, "Johns Rock");
		assertFalse(p.pets.get(0).getSickness());
		p.pets.get(0).makeSick(true);
		assertTrue(p.pets.get(0).getSickness());
		assertTrue((petBefore.getHealthInt()-10) == p.pets.get(0).getHealthInt());
		assertTrue(p.getBalance() == 100);
		p.pets.get(0).vet();
		assertTrue(p.getBalance() == 100-25);
		
		assertTrue((petBefore.getActions() - 1) == p.pets.get(0).getActions());
		assertTrue((petBefore.getMoodInt() + 20) == p.pets.get(0).getMoodInt());
		assertTrue((petBefore.getHealthInt() + 20 - 10) == p.pets.get(0).getHealthInt());
		
	}
	@Test
	public void testUpdateLiving(){
		assertFalse(pet.getDead());
		pet.updateLiving();
		assertFalse(pet.getDead());
		//No way to change stats from outside of pet class as the stats are private to remove possibility of stats being changed inadvertantly
		//so call dailyChangestats which lowers the stats multiple times such that one reaches 0
		pet.dailyChangeStats();
		pet.dailyChangeStats();
		pet.dailyChangeStats();
		pet.dailyChangeStats();
		pet.dailyChangeStats();
		pet.dailyChangeStats();
		pet.updateLiving();
		assertTrue(pet.getDead());
		
		
	}
	
	@Test
	public void testActions(){
		pet.setActions(-1);
		assertTrue(pet.getActions() == 0);
		pet.setActions(0);
		assertTrue(pet.getActions() == 0);
		pet.setActions(1);
		assertTrue(pet.getActions() == 1);
		pet.setActions(2);
		assertTrue(pet.getActions() == 2);
		pet.setActions(3);
		assertTrue(pet.getActions() == 2);
		
		
		pet.setActions(0);
		assertFalse(pet.canAct());
		pet.setActions(2);
		assertTrue(pet.canAct());
		pet.death();
		assertFalse(pet.canAct());
		
	}
	
	@Test
	public void testToString(){
		assertTrue(pet.toString() instanceof String);
	}

}
