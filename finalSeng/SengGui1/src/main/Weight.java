package main;

public class Weight extends StatLevel{
	
	/**
	 * single argument constructor that calls super constructor with the double passed in.
	 * This will set the level to that of the double passed in and will default the modifier to 1
	 * @param level - level to initiailise stat to
	 */
	public Weight(double level){
		super(level);
	}
	/**
	 * Constructor to clone a Weight Object. by calling super constructor with passed object level
	 * @param weight - weight object to clone
	 */
	public Weight(Weight weight) {
		// TODO Auto-generated constructor stub
		super(weight.getLevel());
	}
	@Override
	/**
	 * returns string of weight level
	 */
	public String getLevelString() {
		// TODO Auto-generated method stub
		return "weight level";
	}

}
