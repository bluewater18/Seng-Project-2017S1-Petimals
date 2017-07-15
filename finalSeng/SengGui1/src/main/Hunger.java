package main;

public class Hunger extends StatLevel{
	
	
	/**
	 * Calls parent constructor with doubles for starting level and multiplier
	 * @param level - level to initialise stat to
	 * @param multiplier - level to initialise modifer to
	 */
	public Hunger(double level, double multiplier){
		super(level, multiplier);
	}
	/**
	 * constructor to make a clone of passed in hunger stat
	 * Calls parent constructor with doubles for starting level and multiplier. gathered from the properties of the passed in Hunger object
	 * @param hunger - hunger object to clone
	 */
	public Hunger(Hunger hunger) {
		super(hunger.getLevel(), hunger.getModifier());
	}
	@Override
	/**
	 * returns string of hunger level
	 * @return string representation of hunger level
	 */
	public String getLevelString() {
		
		return "hunger level";
	}

}
