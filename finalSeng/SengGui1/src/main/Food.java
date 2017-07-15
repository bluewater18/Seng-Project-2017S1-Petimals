package main;

public class Food {
	/**
	 * Name of food. foods with the same name will be considered the same by equals
	 */
	private String name;
	/**
	 * cost of food
	 */
	private int cost;
	/**
	 * food value of food
	 */
	private int foodValue;
	/**
	 * String location for the image of food
	 */
	private String img;
	
	/**
	 * Constructor using default image location
	 * @param nameC - name for food
	 * @param costC - cost of food
	 * @param foodValueC - foodValue of food
	 */
	public Food(String nameC, int costC, int foodValueC){
		name = nameC;
		cost = costC;
		foodValue = foodValueC;
		img = "/64.png";
	}
	/**
	 * Constructor with specific img location
	 * @param nameC - name of food
	 * @param costC -  cost of food
	 * @param foodValueC - food value of food
	 * @param imgC - string of image location of food
	 */
	public Food(String nameC, int costC, int foodValueC, String imgC){
		name = nameC;
		cost = costC;
		foodValue = foodValueC;
		img = imgC;
	}
	
	/**
	 * Constructor taking only the name of a food
	 * Used to implement favorite food as the name is the comparitive factor
	 * @param nameC - name of food
	 */
	public Food(String nameC){
		name = nameC;
	}
	/**
	 * Constructor for cloning food object
	 * @param food clones food object
	 */
	public Food( Food food){
		name = food.name;
		cost = food.cost;
		foodValue = food.foodValue;
		img = food.img;
	}
	
	
	
	
	@Override
	/**
	 * Compares this food object with the object passed in
	 * Food is determined to be equal if they have the same name
	 * @return - boolean if the Foods have the same name. CASE SENSITIVE
	 */
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(!Food.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		final Food other = (Food) obj;
		if(this.name == other.name){
			return true;
		}
		else{return false;}	
		
	}
	
	@Override
	/**
	 * Creates hash code for Food objects
	 * implemented as equals has been overwitten
	 * New hash code determined by name only
	 * @return hashcode for the Food
	 */
	public int hashCode(){
		return 57 * 3 + this.name.hashCode();
	}
	/**
	 * 
	 * @return Returns the name of the food
	 */
	public  String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return returns the cost for the food
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * returns the name of the food and its food value
	 * @return name(foodValue)
	 */
	public String toString(){
		return name + "("+foodValue+")";
	}
	
	/**
	 * String for use in shop menus. Should food value be included
	 * @return name(foodValue)[$cost]
	 */
	public String shopString(){
		return name +"(" + foodValue+")"+"[$" + cost +"]";
	}
	/**
	 * Returns food value of the food
	 * @return returns the foodValue
	 */
	public int getFoodValue(){
		return foodValue;
	}
	/**
	 * returns string of img location
	 * @return string location of image
	 */
	public String getImgString(){
		return img;
	}
	
}
