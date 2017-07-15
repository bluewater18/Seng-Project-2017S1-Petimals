package main;

public class Toy {
	/**
	 * name of the toy
	 */
	private String name;
	/**
	 * int cost of the toy
	 */
	private int cost;
	/**
	 * int durabilituy the toy has
	 */
	private int durability;
	/**
	 * String representation of the image location for the toy
	 */
	private String img;
	
	/**
	 * Constructor taking only the name of a toy
	 * Used to implement favorite toy as the name is the comparitive factor
	 * @param nameC - name for new toy
	 */
	public Toy(String nameC){
		name = nameC;
	}
	
	/**
	 * Contructor for toy object. Requires a name cost and durability. Image will be set to default 64*64 placeholder
	 * @param nameC name of toy
	 * @param costC cost for toy
	 * @param durabilityC durability of toy
	 */
	public Toy(String nameC, int costC, int durabilityC){
		name  = nameC;
		cost = costC;
		durability = durabilityC;
		img = "/64.png";
	}
	
	/**
	 * Constructor for toy object with specified image
	 * @param nameC toy name
	 * @param costC toy cost
	 * @param durabilityC toy durability
	 * @param imgC string location of image
	 */
	public Toy(String nameC, int costC, int durabilityC, String imgC){
		name  = nameC;
		cost = costC;
		durability = durabilityC;
		img = imgC;
	}
	
	/**
	 * Constructor to clone a Toy object
	 * @param toy clones the Toy
	 */
	public Toy(Toy toy){
		name = toy.name;
		cost = toy.cost;
		durability = toy.durability;
		img = toy.img;
	}
	
	@Override
	/**
	 * Compares this Toy object with the passed in object
	 * Toy is determined to be equal if they have the same name
	 */
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(!Toy.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		final Toy other = (Toy) obj;
		if(this.name == other.name){
			return true;
		}
		else{return false;}
	}
	
	@Override
	/**
	 * Createss hash code for the toy
	 * implemented as equals has been overwritten
	 * New hash code is determined by name only
	 */
	public int hashCode(){
		return 53 * 3 + this.name.hashCode();
	}
	
	/**
	 * returns a string of the toy and its durability in brackets used to show toy as an inventory item
	 * @return name(durability)
	 */
	public String toString(){
		return name+"("+durability+")";
	}
	/**
	 * returns just the name of the toy
	 * @return toyName - name of the toy
	 */
	public String getName(){
		return name;
		
	}
	
	/**
	 * returns a string used in the shop displaying the price. Should the durability be included?
	 * @return name(durability)[$cost]
	 */
	public String shopString(){
		return name +"("+durability+")"+ "[$"+cost+ "]";
	}
	
	/**
	 * returns the cost of they toy
	 * @return cost - cost of the toy
	 */
	public int getCost(){
		return cost;
	}
	/**
	 * returns the current durability of the toy
	 * @return durability - durability of the toy
	 */
	public int getDurability(){
		return durability;
	}
	/**
	 * Sets the toys durability to that passed in, for toy use the calculation must be handled outside this method.
	 * Either in pet or game loop class
	 * @param durabilityM - durability for new toy
	 */
	public void setDurability(int durabilityM){
		durability = durabilityM;
	}
	
	
	/**
	 * returns string of img location
	 * @return string location of image
	 */
	public String getImgString(){
		return img;
	}
	
	
}
