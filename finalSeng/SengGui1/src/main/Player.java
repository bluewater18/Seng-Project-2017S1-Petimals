package main;
import java.util.ArrayList;

public class Player {
	/**
	 * name of the player
	 */
	private String name;
	/**
	 * ArrayList of type Pet containing all the pets the player has
	 */
	public ArrayList<Pet> pets = new ArrayList<Pet>();
	/**
	 * ArrayList of type Toy containing all the toys the player has
	 */
	public ArrayList<Toy> toys = new ArrayList<Toy>();
	/**
	 * ArrayList of type Food containing all the food the player has
	 */
	public ArrayList<Food> food = new ArrayList<Food>();
	/**
	 * Integer starting balance of the player. set to 100
	 */
	private int balance = 100;
	private int score = 0;
	private int numPets = 0;
	
	
	/**
	 * Player constructor requiring a name only. Number of pets will start at 0
	 * @param nameC - name for player
	 */
	public Player(String nameC){
		this.name = nameC;
	}
	
	/**
	 * Player constructor requiring a name and int number of pets
	 * Allows looping through the number to add pets
	 * @param nameC - name for player
	 * @param petNum - number of pets for player
	 */
	public Player(String nameC, int petNum){
		this.name = nameC;
		this.numPets = petNum;
	}
	
	/**
	 * Gets player name
	 * @return String playername
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * returns the number of pets the player has
	 * @return - number of pets the player has
	 */
	public int getPetNum(){
		return numPets;
	}
	
	/**
	 * Sets the number of pets the player should have. However will not remove pets.
	 * implemented to allow for adding of specific numbers of pets
	 * @param num - number of pets player will have
	 */
	public void setPetNum(int num){
		numPets = num;
	}
	
	/**
	 * Adds a toy of the type passed in. Uses Toy(Toy) constructor to make a clone of the toy object
	 * so that the player has their own instance of the object
	 * @param toy - toy to be added to the player
	 */
	public void addToy(Toy toy){
		toys.add(new Toy(toy));
	}
	
	
	/**
	 * Adds a food of the type passed in. Uses Food(Food) constructor to make a clone of the toy object
	 * so that the player has their own instance of the object
	 * @param foo - food to be added to the player
	 */
	public void addFood(Food foo){
		food.add(new Food(foo));
	}
	/**
	 * Prints the player and a list of their pets with pet.toString()
	 */
	public String toString(){
		String returnStr = "Player Name:" + name + "\nPets:";
		for (Pet p : pets){
			
			returnStr += "\n"+ p.toString() + " ";
		}
		return returnStr;
	}
	
	/**
	 * To get players current balance
	 * @return player balance
	 */
	public int getBalance(){
		return balance;
	}
	
	/**
	 * To set players balance
	 * @param balSet players new balance
	 */
	public void setBalance(int balSet){
		if (balSet<=0){
			balance = 0;
		}
		else{
		balance = balSet;
		}
	}
	/**
	 * Used to remove a set int amount from players balance. If the balance somehow goes below zero balance will be set to 0
	 * @param amount - money to remove from players balance
	 */
	public void spend(int amount){
		if(balance - amount > 0){
		balance -= amount;
		}
		else{balance = 0;}
	}
	
	/**
	 * gives the player a passed integer amount of money. No upper cap
	 * @param amount - money to add to players balance
	 */
	public void giveMoney(int amount){
		balance += amount;
	}
	
	/**
	 * To get player score as Int
	 * @return players Int of current score
	 */
	public int getScore(){
		return score;
	}
	/**
	 * To set players score to a specific Int
	 * @param scoreAmount - Score to set the current player's to
	 */
	public void setScore(int scoreAmount){
		score = scoreAmount;
	}
	
	/**
	 * Add a double value to the score, will be rounded by Math.round
	 * @param scoreAdd - score to add to the players score total
	 */
	public void addScore(double scoreAdd){
		score += (int) Math.round(scoreAdd);
	}
	/**
	 * Add an int value to the score
	 * @param scoreAdd - score to add to the players total
	 */
	public void addScore(int scoreAdd){
		score+= scoreAdd;
	}
	
	
	/**
	 * add a new pet, takes an int and then uses specie enum to set values
	 * @param num - specie type for pet
	 */
	public void addPet(int num){
		pets.add(new Pet(Specie.getTypeFromInt(num)));
	}
	/**
	 * Adds a new pet to the players array list. requires a specie type and name
	 * @param specie - Specie type of pet to add
	 * @param petName - name for new pet
	 */
	public void addPetWithName(Specie specie, String petName){
		pets.add(new Pet(specie, petName, this));
	}
	/**
	 * used to remove all pets a player has
	 * Used if the addition of pets fail so that it can be retried without duplicating some pet objects
	 */
	public void removePets() {
		pets.clear();
		
	}


	

	 
}
