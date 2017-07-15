package main;
public class Mood extends StatLevel{
	
	
	/**
	 * Calls parent constructor with doubles for starting level and multiplier
	 * @param level - level to initialise the stat to
	 * @param multiplier - multiplier to initialise the stat to
	 */
	public Mood(double level, double multiplier){
		super(level, multiplier);
	}
	/**
	 * calls parent constructor with only double for starting level. modifier will be set to 1
	 * @param level - level to initialise the stat to
	 */
	public Mood(double level){
		super(level);
	}
	/**
	 * constructor to create a clone of the passed in mood object
	 * @param mood -  mood object to be cloned
	 */
	public Mood(Mood mood) {
		// TODO Auto-generated constructor stub
		super(mood.getLevel());
	}

	@Override
	/**
	 * returns a string of mood level
	 * @return string representation of mood Level
	 */
	public String getLevelString() {
		
		return "Mood Level";
	}

}