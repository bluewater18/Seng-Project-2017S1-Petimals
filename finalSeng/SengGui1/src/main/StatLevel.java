package main;

abstract public class StatLevel {
	/**
	 * double representation of stat level restricted between 0 and 100 by behaviour
	 */
	private double level;
	/**
	 * double representation of multiplier. No restrictions
	 */
	private double modifier;
	
	/**
	 * Default constructor, no args
	 * Will automatically set level to 100.00 and modifier to 1.00
	 */
	public StatLevel(){
		level = 100;
		modifier = 1.00;
	}
	
	/**
	 * Will automatically set modifier to 1
	 * @param baseLevel BaseLevel for stat
	 */
	public StatLevel(double baseLevel){
		level = baseLevel;
		modifier = 1.00;
	}
	
	
	/**
	 * 
	 * @param baseLevel Starting level for the stat
	 * @param baseModifier modifier for the stat
	 */
	public StatLevel(double baseLevel, double baseModifier){
		level = baseLevel;
		modifier = baseModifier;
		
	}
	
	
	/**
	 * 
	 * @return returns the current stat level
	 */
	public double getLevel(){
		return level;
	}
	
	public double getModifier(){
		return modifier;
	}
	/**
	 * must be overridden
	 * @return Returns a string representation of current stat level
	 */
	abstract public String getLevelString();
	/**
	 * Sets the stat level to that of the double passed in.
	 * retricts change to between 0 and 100
	 * @param levelIn Level that will be set should be between 0 and 100
	 */
	public void setLevel(double levelIn){
		if (levelIn<0)
			level = 0;
		else if (levelIn>100)
			level = 100;
		else
			level = levelIn;
	}
	/**
	 * 
	 * @param levelChange Change to level as int, can be positive or negative
	 * if level drops to 0 pet will die, level maxes at 100
	 * 
	 */
	public void changeLevel(int levelChange){
		level += levelChange*modifier;
		if (level<0){
			level = 0;
			
		}
		if (level > 100)
			level = 100;
	}
	
	/**
	 * Method for when pet dies sets the stat to 0.00
	 */
	public void dead(){
		level = 0.00;
	}
	
	
	

}
