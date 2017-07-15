package main;

public class Energy extends StatLevel{
	
	/**
	 * Calls parent constructor with doubles for starting level and multiplier
	 * @param level - double starting level for stat
	 * @param multiplier - double multiplier for stat
	 */
	public Energy(double level, double multiplier){
		super(level, multiplier);
	}
	/**
	 * constructor to make a clone of the passed in energy 
	 * @param energy - energy stat to be clones
	 */
	public Energy(Energy energy) {
		// TODO Auto-generated constructor stub
		super(energy.getLevel(), energy.getModifier());
	}
	@Override
	/**
	 * returns String of energy level
	 * @return String representation of energy level
	 */
	public String getLevelString() {
		
		return "energy level";
	}

}
