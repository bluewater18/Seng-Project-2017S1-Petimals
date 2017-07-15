package main;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	/**
	 * number of days the game will be played for
	 * starts at 0 to avoid trying to run a game with no players made
	 */
	public int numDays=0;
	/**
	 * ArrayList of all player names in use
	 * stored as lowercase
	 * empty string is always in the array
	 */
	static ArrayList<String> names = new ArrayList<String>();
	/**
	 * ArrayList of all pet names in use
	 * stored as lowercase
	 * empty string is always in the array
	 */
	static ArrayList<String> petNames = new ArrayList<String>();
	/**
	 * Waiter object to use thread operations on. so that the program can wait for certain gui's to finish etc
	 */
	protected static Object waiter = new Object();
	
	/**
	 * Start of the program. runs the WelcomeOption object GUI
	 * @param args - cmd arguments
	 */
	public static void main(String[] args) {
		//to disallow an empty string as a name
		names.add("");
		petNames.add("");
		
		
		Game game = new Game();
		@SuppressWarnings("unused")
		WelcomeOption test = new WelcomeOption(game);
		playerGetter(game);
		
	}
	
	/**
	 * Gets player name and number of pets for the number of players selected from the Welcome screen
	 * @param game - current game object
	 */
	public static void playerGetter(Game game){
		
		
		for(int i=1; i<= game.getPlayers(); i++){
			String msg = "";
			do{
			
			@SuppressWarnings("unused")
			PlayerName getPlayer = new PlayerName(game,i,msg);
			msg = "Please insert an unused name";
			}
			while (Main.names.contains(game.tempString.toLowerCase()));
			
			game.addPlayer(game.tempString,game.tempInt);
			
			Main.names.add(game.tempString.toLowerCase());
		}
		petGetter(game);
	}
	
	/**
	 * Gets the pets for each player using PetChooser object GUI
	 * @param game - current game object
	 */
	public static void petGetter(Game game){
		
		for(Player p:game.players){
			
			
			
			
			game.currPlayer = p;
			PetChooser petSelect = new PetChooser(game);
			petSelect.runWindow(game);
			try {
				synchronized(waiter){
				waiter.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		startGame(game);
	}
	
	
	/**
	 * Sets number of days for game
	 * @param d - number of days the game will run for
	 */
	public void setDays(int d){
		numDays = d;
	}
	
	
	/**
	 * Starts the game and loops through the the players every day
	 * @param game - current game object
	 */
	public static void startGame(Game game){
		game.start();
		
		//Where the game goes
		for(int i = 1; i<=game.getNumDays(); i++){
			
			game.setDayNum(i);
			
		for(Player p:game.players){	
			p.giveMoney(10);
			game.managePets(p);
			
			game.currPlayer = p;
			GameGui gui = new GameGui(game);
			gui.runWindow(game);
			try {
				
				synchronized(waiter){
				waiter.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			game.dayScore();
			
		}
		}
		
		for(Player p: game.players){
			JOptionPane.showMessageDialog(null, "<HTML>Game Over.<br> <HTML>"+p.getName()+" scored "+p.getScore()+" Well Done!");
		}
		
	}

}
