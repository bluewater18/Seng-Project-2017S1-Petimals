package main;

/**
 * 
 * @author MorganEnglish
 * Enumerated types for specie. That is species that pets are capable of being in the game
 *
 */
public enum Specie {
	
	//Creation of all species. Listing their stating stats
	//Description, Specie String, Weight, BaseHelath, Health Modifier, Base Exhaustion, Exhaustion Modifier, Base Hunger, Hunger Modifier,Toilet Level, toy damage, img_64, img_96, imgDead_96
	ROCK("Rock Solid", "Rock", new Weight(50.00),new Health(90,1.00), new Energy(50.00, 1.00), new Hunger( 100.00, 1.00),new Toilet(50.00),new Mood(80.00), 1, "/rock_64.gif", "/rock_96.gif", "/rock_dead.png", new Toy("Pebble"), new Food("Algae")),		
	PENGUIN("Pengu", "Penguin", new Weight(35.00),new Health(95,0.75), new Energy(80.00, 1.00), new Hunger( 100.00, 1.00),new Toilet(50.00),new Mood(65.00), 1, "/penguin_64.gif", "/penguin_96.gif", "/penguin_dead.png", new Toy("Snow"), new Food("Fish")),				
	WOLF("Lone Wanderer", "Wolf", new Weight(40.00),new Health(100,1.00), new Energy(80.00, 1.25), new Hunger( 100.00, 1.25),new Toilet(50.00),new Mood(60.00), 1, "/wolf_64.gif", "/wolf_96.gif", "/wolf_dead.png", new Toy("Bone"), new Food("Meat")),
	CAT("Internet God", "Cat", new Weight(30.00),new Health(100,0.75), new Energy(60.00, 0.75), new Hunger( 100.00, 1.00),new Toilet(50.00),new Mood(70.00), 1, "/cat_64.gif", "/cat_96.gif", "/cat_dead.png", new Toy("Laser"), new Food("Catnip")),
	DOG("Man's best friend", "Dog", new Weight(35.00),new Health(100,1.25), new Energy(80.00, 1.50), new Hunger( 100.00, 1.50),new Toilet(50.00),new Mood(80.00), 1, "/dog_64.gif", "/dog_96.gif", "/dog_dead.png", new Toy("Chewtoy"), new Food("Kibble")),
	RABBIT("Furry Critter", "Rabbit", new Weight(25.00),new Health(80,1.25), new Energy(90.00, 1.75), new Hunger( 80.00, 1.50),new Toilet(50.00),new Mood(70.00), 1, "/rabbit_64.gif", "/rabbit_96.gif", "/rabbit_dead.png", new Toy("Wheel"), new Food("Grass"));
	
	//public static String specieChoiceString = String.format("%s",Specie.values())
	
	
	/**
	 * Description of pet
	 */
	private String desc;
	/**
	 * String of Specie
	 */
	private String specieStr;
	/**
	 * starting weight of specie
	 */
	private Weight weight;
	/**
	 * starting health for specie
	 */
	private Health healthStat;
	/**
	 * starting energy stat for specie
	 */
	private Energy energyStat;
	/**
	 * starting hunger stat for specie
	 */
	private Hunger hungerStat;
	/**
	 * starting mood stat for specie
	 */
	private Mood moodStat;
	/**
	 * Starting toiletStat for specie
	 */
	private Toilet toiletStat;
	/**
	 * damage specie type will do to toys
	 */
	private int toyDamage;
	
	
	/**
	 * String of 96*96 image location for specie
	 */
	private String img96Str;
	/**
	 * String of 96*96 image location for specie
	 */
	private String img64Str;
	/**
	 * String of 96*96 DEAD image location for specie
	 */
	private String imgDeadStr;
	
	/**
	 * Species favourite toy
	 */
	private Toy favToy;
	/**
	 * Species favourite food
	 */
	private Food favFood;
	
	
	/**
	 * Static method to return a specific Specie type from an integer passed in. Defaults to rabbit if the integer is not within the bounds
	 * @param num - number to get specie from 1 = Rock, 2 = Penguin, 3 = Wolf, 4 = Cat, 5 = Dog, 6 = Rabbit
	 * @return - A Specie type
	 */
	public static Specie getTypeFromInt(int num){
		switch(num){
		case 1: return Specie.ROCK;
		case 2: return Specie.PENGUIN;
		case 3: return Specie.WOLF;
		case 4: return Specie.CAT;
		case 5: return Specie.DOG;
		default: return Specie.RABBIT;
		
		}
	}
	
	
	/**
	 * Constructor for specie requires description, specie name , all stats, toy damage, and image locations
	 * @param descC
	 * @param specieStrC
	 * @param weightC
	 * @param healthStatC
	 * @param energyStatC
	 * @param hungerStatC
	 * @param toiletStatC
	 * @param moodStatC
	 * @param toyDamageC
	 * @param img64
	 * @param img96
	 * @param imgDead
	 * @param favorToy
	 * @param favorFood
	 */
	Specie(String descC, String specieStrC, Weight weightC, Health healthStatC, Energy energyStatC, Hunger hungerStatC, Toilet toiletStatC, Mood moodStatC, int toyDamageC, String img64, String img96, String imgDead, Toy favorToy, Food favorFood){
		desc = descC;
		specieStr = specieStrC;
		weight = weightC;
		healthStat = healthStatC;
		energyStat = energyStatC;
		toiletStat = toiletStatC;
		hungerStat = hungerStatC;
		moodStat = moodStatC;
		toyDamage = toyDamageC;
		img96Str = img96;
		img64Str = img64;
		imgDeadStr = imgDead;
		favToy = favorToy;
		favFood = favorFood;
	}
	
	/**
	 * returns the favorite toy for the species
	 * @return - species fav toy
	 */
	public Toy getFavToy(){
		return favToy;
	}
	
	/**
	 * returns the favorite food for the specie
	 * @return - species fav food
	 */
	public Food getFavFood(){
		return favFood;
	}
	
	/**
	 * 
	 * @return returns the specie Description
	 */
	public String getDesc(){
		return desc;
	}
	
	/**
	 * 
	 * @return returns the specie name
	 */
	public String getSpecieStr(){
		return specieStr;
	}
	
	/**
	 * returns specie starting weight
	 * @return specie starting weight
	 */
	public Weight getWeight(){
		return weight;
	}
	
	/**
	 * returns specie starting health
	 * @return specie starting health
	 */
	public Health getHealth(){
		return healthStat;
	}
	
	/**
	 * returns specie starting hunger
	 * @return specie starting hunger
	 */
	public Hunger getHunger(){
		return hungerStat;
	}
	
	/**
	 * returns specie statrting energy
	 * @return specie statrting energy
	 */
	public Energy getEnergy(){
		return energyStat;
	}
	
	/**
	 * returns specie starting toilet level
	 * @return specie starting toilet level
	 */
	public Toilet getToilet(){
		return toiletStat;
	}
	
	/**
	 * returns specie starting mood
	 * @return specie starting mood
	 */
	public Mood getMood(){
		return moodStat;
	}
	
	/**
	 * returns damage done by pet when playing with a toy
	 * @return specie toy damage
	 */
	public int getToyDamage(){
		return toyDamage;
	}

	
	/**
	 * returns string containing the location of the 96*96 img of the specie
	 * @return string location of 96*96 img
	 */
	public String getImage96() {
		return img96Str;
		
	}

	/**
	 * returns string containing the location of the 64*64 img of the specie
	 * @return string location of 64*64 img
	 */
	public String getImage64(){
		return img64Str;
	}
	
	/**
	 * returns the dead image of the specie. 96*96 size
	 * @return string location of dead 96*96 img
	 */
	public String getDeadImg() {
		return imgDeadStr;
	}
	
	
}
