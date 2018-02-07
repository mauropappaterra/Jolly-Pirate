/* The Jolly Pirate
   Boat.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/
package model;

public class Boat {

	private String boat_no;
	private String type;
	private int size;

	static int counter = 1;

	public Boat (int size, String type){
		setSize(size);
		setType(type);
		createId();
	}

	public String getBoat_no(){
		return boat_no;
	}
	
	public void createId () {
		boat_no = 'B' + Integer.toString(1000000 + counter).substring(1,7);
		counter++;
	}

	public void setSize (int new_Size){
		size = new_Size;
	}

	public int getSize (){
		return size;
	}
	
	public void setType (String new_type){
		type = new_type;
	}

	public String getType (){
		return type;
	}

	public String[] printInfo (){
		String info [] = {boat_no, type, size + ""};
		return info;
	}

}
