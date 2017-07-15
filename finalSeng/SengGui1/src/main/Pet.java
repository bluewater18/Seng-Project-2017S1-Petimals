package main;
import javax.swing.JOptionPane;

public class Pet {
	/**
	 * pets name
	 */
	private String name = "Marcus";
	/**
	 * description of the pet
	 */
	private String desc;
	/**
	 * string of specie
	 */
	private String specieStr;
	/**
	 * pets current health
	 */
	private Health healthStat;
	/**
	 * pets current energy
	 */
	private Energy energyStat;
	/**
	 * pets current hunger
	 */
	private Hunger hungerStat;
	/**
	 * pets current weight
	 */
	private Weight weight;
	/**
	 * pets current mood
	 */
	private Mood moodStat;
	/**
	 * pets current toilet stat
	 */
	private Toilet toilet;
	/**
	 * Specie of the pet
	 */
	private Specie specie;
	private int actions = 0;
	/**
	 * maximum number of actions per turn
	 */
	private final int MAX_ACTIONS = 2;
	/**
	 * damage the pet does when it plays with an object
	 */
	private final int TOY_DAMAGE;
	/**
	 * player who owns the pet
	 */
	private Player owner;
	/**
	 * Living status of the pet true = dead, false = living
	 */
	private boolean isDead = false;
	/**
	 * revivability status of the pet true = can revive, false = cannot revive
	 */
	private boolean reviveable = true;
	/**
	 * Sickness status of the pet
	 * true = sick
	 * false = not sick
	 */
	private boolean isSick = false;
	/**
	 * misbehaving status of the pet
	 * true = misbehaving
	 * false = not misbehaving
	 */
	private boolean isMisbehaving = false;
	
	
	
	/**
	 * Pet constructor requiring only a Specie enum type
	 * pets created this way will not have an owner property, however tey may exist in a player pet arrayList
	 * @param specieC - Specie to make new pet
	 */
	public Pet(Specie specieC){
		this.specie = specieC;
		desc = specie.getDesc();
		specieStr = specie.getSpecieStr();
		
		TOY_DAMAGE= specie.getToyDamage();
		initialiseStats(specieC);
		
		
		
	}
	/**
	 * Pet contructor for a Specie and name
	 * @param specieC - Specie for the new pet
	 * @param petName - name for the new pet
	 */
	public Pet(Specie specieC, String petName) {
		// TODO Auto-generated constructor stub
		this.specie = specieC;
		this.name = petName;
		desc = specie.getDesc();
		specieStr = specie.getSpecieStr();
		
		TOY_DAMAGE= specie.getToyDamage();
		initialiseStats(specieC);
	}
	
	/**
	 * Pet constructor for a Specie, name and specific owner
	 * @param specieC - specie for the new pet
	 * @param petName - name for the new pet
	 * @param player - owner of the new pet
	 */
	public Pet(Specie specieC, String petName, Player player) {
		// TODO Auto-generated constructor stub
		this.specie = specieC;
		this.name = petName;
		desc = specie.getDesc();
		specieStr = specie.getSpecieStr();
		owner = player;
		TOY_DAMAGE= specie.getToyDamage();
		initialiseStats(specieC);
	}
	/**
	 * Initialises all of the stats for the pet. Called from every constructor.
	 * Extrapolated out to here so that it can also be used to re-set a pets stats on revive
	 * @param specie - specie that pet should inherit its stats values from
	 */
	private void initialiseStats(Specie specie){
		healthStat = new Health(specie.getHealth());
		energyStat = new Energy(specie.getEnergy());
		hungerStat = new Hunger(specie.getHunger());
		weight = new Weight(specie.getWeight());
		toilet = new Toilet(specie.getToilet());
		moodStat = new Mood(specie.getMood());
	}
	
	/**
	 * returns and integer form of the pets health stat for use in gui bar
	 * @return - health stat of pet as int
	 */
	public int getHealthInt(){
		return (int)healthStat.getLevel();
	}
	
	/**
	 * returns the hunger of the pet as an integer
	 * @return - hunger stat of pet as int
	 */
	public int getHungerInt(){
		return(int)hungerStat.getLevel();
	}
	
	/**
	 * returns the energy of the pet as an integer
	 * @return - energy stat of pet as int
	 */
	public int getEnergyInt(){
		return (int)energyStat.getLevel();
	}
	
	/**
	 * returns the mood of the pet as an integer
	 * @return - mood stat of pet as int
	 */
	public int getMoodInt(){
		return (int)moodStat.getLevel();
	}
	
	/**
	 * returns the toilet level of the pet as an integer
	 * @return - toilet stat of pet as int
	 */
	public int getToiletInt(){
		return (int)toilet.getLevel();
	}
	
	/**
	 * returns the weight of the pet as an integer
	 * @return - weight stat of pet as int
	 */
	public int getWeightInt(){
		return (int)weight.getLevel();
	}
	/**
	 * 
	 * @return name of the pet
	 */
	public String getName(){
		return name;
	}
	/**
	 * sets the pets name
	 * @param newName name for the pet
	 */
	public void setName(String newName){
		this.name = newName;
	}

	/**
	 * @return returns a line broken string of pet stats with num to 2 dp and string representations of all stats
	 */
	public String toString(){
		String returnString;
		if (isDead)
			return name + " is dead";
		else
			returnString = String.format("Name: %s \nDesc: %s\nSpecie: %s \nHealth: %s (%.2f) \nHunger: %s (%.2f) \nHunger: %s (%.2f) \nEnergy: %s (%.2f) \nToilet: %s (%.2f) \nWeight: %.2f", name ,desc,specieStr,healthStat.getLevelString(), healthStat.getLevel(), moodStat.getLevelString(), moodStat.getLevel(), hungerStat.getLevelString(), hungerStat.getLevel(), energyStat.getLevelString(), energyStat.getLevel(), toilet.getLevelString(), toilet.getLevel(), weight.getLevel());
			if (isMisbehaving){ returnString += "/nMisbehaving";}
			if (isSick){returnString += "/nSick";}
			returnString += "\n";
		return returnString;
	}
	/**
	 * Used to find if a pet is dead or not
	 * @return true if pet is dead
	 */
	public boolean getDead(){
		return isDead;
		
	}
	
	/**
	 * Used to find if the pet can be revived
	 * @return boolean true if can be revived
	 */
	public boolean isReviveable(){
		return reviveable;
	}
	/**
	 * Sets all Stats except weight to 0, sets isDead to true
	 * 
	 */
	public void death(){
		this.isDead = true;
		healthStat.dead();
		energyStat.dead();
		hungerStat.dead();
		moodStat.dead();
		toilet.dead();		
	}
	
	
	/**
	 * Revives dead pets. If the pet is not dead or has already been revived does not complete
	 * reinitialises all stats except weight, sets isdead to false, sets reviveable to false
	 * 
	 * 
	 */
	public void revive(){
		if (this.isDead && this.reviveable){
			this.reviveable = false;
			this.isDead = false;
			initialiseStats(specie);	
		}
		
	}
	/**
	 * 
	 * Removes toy from owner toy list if it is destroyed
	 * less energy used, larger mood increase and small health increase if its the pets favourite toy
	 * @param toy Toy that will be played with
	 * 
	 */
	public void play(Toy toy){
		actions -= 1;
		if(toy.equals(this.specie.getFavToy())){
			energyStat.changeLevel(-10);
			moodStat.changeLevel(20);
			healthStat.changeLevel(5);
		}
		else{
		energyStat.changeLevel(-15);
		moodStat.changeLevel(10);
		}
		
		
		int durability = toy.getDurability();
		int remainingDurability = durability - TOY_DAMAGE;
		if (remainingDurability>0){
		
		toy.setDurability(remainingDurability);
		}
		else{
			JOptionPane.showMessageDialog(null, this.getName()+" has broken " + toy.getName());
			
			owner.toys.remove(toy);
		}		
			
		
		
		
	}
	/**
	 * increases hungerstat, weight and toilet
	 * if is favfood then increase is larger and mood is also increased
	 * should always remove food
	 * 
	 * @param food food type that is being used
	 */
	public void feed(Food food){
		//implement this method privately and add a favourite food
		/*if isFavFood(food){
			int foodVal = food.getFoodValue()+5;
			//Increase Mood
		}*/
		actions -=1;
		if(food.equals(this.specie.getFavFood())){
			hungerStat.changeLevel(food.getFoodValue()*4);
			weight.changeLevel(food.getFoodValue()+2);
			toilet.changeLevel(food.getFoodValue()*2);
			moodStat.changeLevel(10);
		}
		else{
		hungerStat.changeLevel(food.getFoodValue()*3);
		weight.changeLevel(food.getFoodValue());
		toilet.changeLevel(food.getFoodValue()*2);
		}
		owner.food.remove(food);
		
	}
	
	/**
	 * Takes 25 points of the pets need to go to the toilet
	 */
	public void goToilet(){
		actions -= 1;
		toilet.changeLevel(-20);
		weight.changeLevel(-2);
	}
	
	
	/**
	 * Adds 20 points to the pets energy levels
	 * adds 5 points to health
	 * remove 5 points from hunger
	 */
	public void sleep(){
		actions -= 1;
		energyStat.changeLevel(20);
		healthStat.changeLevel(5);
		hungerStat.changeLevel(-5);
	}
	
	/**
	 * 
	 * @return number of actions pet has remaining
	 */
	public int getActions(){
		return actions;
	}
	
	/**
	 * sets the number of pet actions to the value passed in
	 * @param actionsM number of pet actions to set
	 */
	public void setActions(int actionsM){
		if (actionsM <0){
			actions = 0;
		}
		else if (actionsM>MAX_ACTIONS){
			actions = MAX_ACTIONS;
		}
		else{
			
		actions = actionsM;
		}
	}
	
	/**
	 * Used to find if a pet can complete an action
	 * @return true if pet has actions left
	 */
	public boolean canAct(){
		if (isDead || actions <= 0)
			return false;
		else return true;
	}
	
	/**
	 * Used to find if a pet is sick or not
	 * @return true if pet is sick
	 */
	public boolean getSickness(){
		return isSick;
	}
	/**
	 * sets whether a pet is sick or not
	 * @param sick boolean true for sick
	 */
	public void setSickness(boolean sick){
		isSick = sick;
		
	}
	/**
	 * sets a pet to be sick cannot be used to make the pet healthy
	 * a pet that becomes sick will lose 15 health
	 * @param sick will make pet sick if true, no change if false
	 */
	public void makeSick(boolean sick){
		if(sick){
			isSick = sick;
			healthStat.changeLevel(-10);
		}
	}
	
	/**
	 * heals a pet by taking it to the vet
	 * uses an action point
	 * increases mood by 20
	 * increase health by 20
	 * costs $25
	 * 
	 */
	public void vet(){
		
		actions -= 1;
		isSick = false;
		moodStat.changeLevel(20);
		healthStat.changeLevel(20);
		owner.spend(25);
		
	}
	
	/**
	 * used to find if a pet is misbehaving
	 * @return true if misbehaving
	 */
	public boolean getMisbehaving(){
		return isMisbehaving;
	}
	
	/**
	 * sets boolean misbehaving to that passed in
	 * @param misbehave boolean sets misbehaving
	 */
	public void setMisbehaving(boolean misbehave){
		isMisbehaving = misbehave;
	}
	
	
	/**
	 * Sets pet to misbehaving state however canot be used for the converse
	 * @param misbehave will misbehave if true, no change if false
	 */
	public void makeMisbehave(boolean misbehave){
		if(misbehave){
			isMisbehaving = misbehave;
		}
	}
	
	
	/**
	 * disciplines a pet
	 * requires an action
	 * removing misbehaviour debuff
	 * changes moodStat by -20
	 */
	public void discipline(){
		actions -= 1;
		isMisbehaving = false;
		moodStat.changeLevel(-20);
		
	}
	
	/**
	 * Returns the specie of the pet
	 * @return - Specie of the current pet
	 */
	public Specie getSpecie(){
		return specie;
	}
	/**
	 * Changes the pets stats at the start of each day
	 * health - 15
	 * hunger -15
	 * energy +10
	 * toilet 10
	 * mood -12
	 */
	public void dailyChangeStats(){
		healthStat.changeLevel(-15);
		hungerStat.changeLevel(-15);
		energyStat.changeLevel(10);
		toilet.changeLevel(10);
		moodStat.changeLevel(-12);
		
	}
	/**
	 * checks to see if the pet has the required stats to be 'alive'
	 * if not sets isDead to true
	 */
	public void updateLiving() {
		
		if (!(healthStat.getLevel() > 0)||!(hungerStat.getLevel()>0)||!(energyStat.getLevel()>0)||!(moodStat.getLevel()>0))
				{
			isDead =true;
			death();
		}
		
		
	}
}
