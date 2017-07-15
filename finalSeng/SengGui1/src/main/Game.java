package main;
import java.util.ArrayList;


public class Game {
	/**
	 * ArrayList holding all the players in the game
	 * Used to complete turn based behaviour for each player
	 */
	ArrayList<Player> players = new ArrayList<Player>();
	/**
	 * ArrayList holding all the possible toys in the game.
	 * clones of these are created to give toys to players
	 * properties of toys in this list are used in shop class to display the images and shopStrings
	 */
	ArrayList<Toy> toys = new ArrayList<Toy>();
	
	/**
	 * ArrayList holding all possible Foods in the game
	 * Clones are created from these and given to players when required
	 */
	ArrayList<Food> foods = new ArrayList<Food>();
	
	/**
	 * number of days
	 */
	private int numDays;
	/**
	 * number of players
	 */
	private int numPlayers;
	/**
	 * temporary string to get strings from popup boxes
	 */
	public String tempString;
	/**
	 * temporary int to get numbers from combo boxes
	 */
	public int tempInt;
	/**
	 * player who is currently using the gui
	 */
	public Player currPlayer;
	/**
	 * current day number
	 */
	private int dayNum;
	
	
	/**
	 * Constructor for game
	 * No arguments as all properties are changed after the instancing of the object
	 */
	public Game(){
		
	}
	/**
	 * Returns the players ArrayList
	 * @return - players ArrayList
	 */
	public ArrayList<Player> getPlayersArray(){
		return players;
	}
	
	/**
	 * Returns the toys ArrayList
	 * @return - toy ArrayList
	 */
	public ArrayList<Toy> getToysArray(){
		return toys;
	}
	
	/**
	 * Returns the foods ArrayList
	 * @return - Food ArrayList
	 */
	public ArrayList<Food> getFoodsArray(){
		return foods;
	}
	
	/**
	 * Sets the number of the day. For display in the GUI
	 * @param d - total number of days
	 */
	public void setDayNum(int d){
		dayNum = d;
	}
	/**
	 * returns the number of the current day
	 * @return - current day number
	 */
	public int getdayNum(){
		return dayNum;
	}
	
	/**
	 * Sets the number of days the game will be played for
	 * @param d - total number of days
	 */
	public void setDays(int d){
		numDays = d;
	}
	/**
	 * returns the number of days for the game
	 * @return - total number of days
	 */
	public int getNumDays(){
		return numDays;
	}
	
	/**
	 * Sets the number of players there will be in the game
	 * @param p total number of players in the game
	 */
	public void setPlayers(int p){
		numPlayers = p;
	}
	/**
	 * Returns the number of players there is in the game
	 * @return total number of players in the game
	 */
	public int getPlayers(){
		return numPlayers;
	}
	/**
	 * adds a player to the game object by calling
	 * player constructor(String, int)
	 * @param name name for the player
	 * @param petNum number of pets the player will have
	 */
	public void addPlayer(String name, int petNum){
		players.add(new Player(name, petNum));
	}
	
	/**
	 * Code that is run before the first day of the game is started
	 * calls createItems to initialise all Toy/Food items for the game
	 * then gives each player a few starting items
	 */
	public void start() {
		createItems();
		for(Player p: players){
			p.toys.add(toys.get(RandomEvents.getRandomInt(6)));
			p.food.add(foods.get(RandomEvents.getRandomInt(6)));
		}

	}
	/**
	 * Creates all the different Food and Toys for the game and adds them to an array to be accessed by other classes
	 */
	private void createItems(){
		Toy chewtoy = new Toy("Chewtoy",15, 3, "/chewtoy.png");
		Toy pebble = new Toy("Pebble",10, 3, "/pebble.png");
		Toy bone = new Toy("Bone",20, 4, "/bone.png");
		Toy snow = new Toy("Snow",5, 2,"/snow.png");
		Toy laser = new Toy("Laser",40, 10,"/laser.png");
		Toy playWheel = new Toy("Wheel",30,8,"/playWheel.png");
		
		toys.add(chewtoy);
		toys.add(pebble);
		toys.add(bone);
		toys.add(snow);
		toys.add(laser);
		toys.add(playWheel);
		
		
		Food kibble = new Food("Kibble",10,5,"/kibble.png");
		Food rawMeat = new Food("Meat",15,10,"/rawmeat.png");
		Food fish = new Food("Fish",15,10,"/fish.png");
		Food catNip = new Food("Catnip", 15,12,"/catnip.png");
		Food grass = new Food("Grass",5,2,"/grass.png");
		Food algae = new Food("Algae", 2,2,"/algae.png");
		
		foods.add(kibble);
		foods.add(rawMeat);
		foods.add(fish);
		foods.add(grass);
		foods.add(catNip);;
		foods.add(algae);
		
	}
	
	

	
	/**
	 * Manages the pets of a specific player at the start of each turn
	 * Used to change stats and add actions
	 * @param p player whose pets need to be managed
	 */
	public void managePets(Player p) {		
			
			for(Pet pet: p.pets){
				//Does nothing if the pet is dead
				if(!pet.getDead()){
					
					pet.dailyChangeStats();

					//If the pet dies after the stats have been reduced it should not get any actions
				}
				pet.updateLiving();
				
				if(!pet.getDead()){
					
					pet.setActions(2);
					pet.makeSick(RandomEvents.getRandomPercent(10));
					pet.makeMisbehave(RandomEvents.getRandomPercent(10));
					
				}
							
}	
	}
	
	/**
	 * Calculates the day score for all players. The scored depends on the pets health hunger and mood, higher levels give more score
	 * Score is greatly affected if any pets are dead at the end of the round
	 */
	public void dayScore(){
		for(Player p:players){
			int score = 0;
			for(Pet pet:p.pets){if(pet.getSickness() || pet.getMisbehaving()){
				score = pet.getHealthInt()+pet.getHungerInt()+pet.getMoodInt();
				score = (int)(score/2);
				
			}
			else{
				score = pet.getHealthInt()+pet.getHungerInt()+pet.getMoodInt();
			}
					
			}
			p.addScore(score);
		}
	}

	
}
