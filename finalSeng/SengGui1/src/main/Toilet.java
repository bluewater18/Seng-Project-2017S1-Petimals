package main;

public class Toilet extends StatLevel{
	
	
	/**
	 * constructor that takes a double as a parameter and calls super constructor with this double. setting the level to that passed in and defaulting modifier to 1
	 * @param level - level to initialise stat
	 */
	public Toilet(double level){
		super(level);
	}
	/**
	 * constructor to clone a passed toilet object. by running super constructor with passed toilet level
	 * @param toilet - toilet object to clone
	 */
	public Toilet(Toilet toilet) {
		super(toilet.getLevel());
	}
	@Override
	/**
	 * returns string of toilet level
	 */
	public String getLevelString() {
		// TODO Auto-generated method stub
		return "toilet level";
	}

}
