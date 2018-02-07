/* The Jolly Pirate
   Member.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/
package model;
import java.util.ArrayList;


public class Member {

	private String first_name;
	private String last_name;
	private String idNumber;
	private String memberId;

	private ArrayList<Boat> boats = new ArrayList<Boat>();

	static int counter = 1;

	public Member(String first_name, String last_name, String idNumber) {
		set_first(first_name);
		set_last(last_name);
		set_id(idNumber);
		create_member_Id();
	}

	public void set_first(String new_name) {
		first_name = new_name;
	}

	public String get_first() {
		return first_name;
	}

	public void set_last(String new_name) {
		last_name = new_name;
	}

	public String get_last() {
		return last_name;
	}

	public void set_id (String id) {
		idNumber = id;
	}

	public String get_id() {
		return idNumber;
	}

	public void create_member_Id() {
		memberId = 'M' + Integer.toString(1000000 + counter).substring(1,7);
		counter++;
	}

	public String get_member_Id() {
		return memberId;
	}

	public int get_no_boats(){
		return boats.size();
	}

	public Boat get_boat_by_index(int i){
		return boats.get(i);
	}

	public Boolean has_boats(){
		return boats.isEmpty();
	}

	public void add_boat(Boat new_boat){
		boats.add(new_boat);
	}

	public void remove_boat(int index){
		boats.remove(index);
	}

	public String[] printInfo(){
		String info [] = {memberId, idNumber + "", first_name, last_name, boats.size() + ""};
		return info;
	}
}