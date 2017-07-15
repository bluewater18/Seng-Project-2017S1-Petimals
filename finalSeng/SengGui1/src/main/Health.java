package main;

public class Health extends StatLevel{
	
	/**
	 * Calls parent constructor with doubles for starting level and multiplier
	 * @param level - level to initialise stat to
	 * @param multiplier - level to initialise modifier to
	 */
	public Health(double level, double multiplier){
		super(level, multiplier);
	}
	/**
	 * constructor to make a clone of passed in health stat
	 * Calls parent constructor with doubles for starting level and multiplier. gathered from the properties of the passed in Health object
	 * @param h - health object to be cloned
	 */
	public Health(Health h){
		super(h.getLevel(), h.getModifier());
	}
	

	@Override
	/**
	 * returns a string representation of the level of the health stat
	 * @return String representation of health value
	 */
	public String getLevelString() {
		
		double levelTemp = this.getLevel();
		if (levelTemp <= 100 && levelTemp >80)
			return "Very Healthy";
		else if (levelTemp <= 80 && levelTemp >50)
			return "Healthy";
		else if (levelTemp <= 50 && levelTemp >20)
			return "Unhealthy";
		else
			return "Dying";
						
	}
	

}
