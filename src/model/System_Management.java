/* The Jolly Pirate
   System_Management.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/
package model;
import java.util.ArrayList;

public class System_Management {
	
	private static ArrayList <Member> all_members = new ArrayList<Member>();

	public System_Management(){
		// Constructor
	}

	public Member get_member_by_index(int i) {
		return all_members.get(i);
	}

	public int get_member_size() {
		return all_members.size();
	}

	public void addMember (String first_name, String last_name, String idNumber) {
		all_members.add(new Member(first_name, last_name, idNumber));
	}

	public boolean checkId (String id){
		if (id.length() == 11) {
			if (id.substring(6, 7).equals("-")) {
				return true;
			}
		}
		return false;
	}

	public Boolean find_id(String id) {
		for (int i = 0; i < all_members.size(); i++){
			if (all_members.get(i).get_id().equals(id)){
				return true;
			}
		}
		return false;
	}

	public Boolean isMember(String member_id) { // check either member id or id number, return true if member is found
		for (int i = 0; i < all_members.size(); i++){
			if (all_members.get(i).get_member_Id().equals(member_id) || all_members.get(i).get_id().equals(member_id)){
				return true;
			}
		}
		return false;
	}

	public Member returnMember(String id){

		if (checkId(id)){ // Return by id number
			for (int i = 0; i < all_members.size(); i++){
				if (all_members.get(i).get_id().equals(id)){
					return all_members.get(i);
				}
			}
		} else { // Return by member number
			for (int j = 0; j < all_members.size(); j++){
				if (all_members.get(j).get_member_Id().equals(id)){
					return all_members.get(j);
				}
			}
		}
		return null;
	}

	public void editMember (String member_id, String first, String last, String id){
		loop:
		for (int i = 0; i < all_members.size(); i++){

			if (all_members.get(i).get_member_Id().equals(member_id)){
				all_members.get(i).set_first(first);
				all_members.get(i).set_last(last);
				all_members.get(i).set_id(id);
				break loop;
			}
		}
	}

	public void deleteMember (String member_id){

		loop:
		for (int i = 0; i < all_members.size(); i++){
			if (all_members.get(i).get_id().equals(member_id)){
				all_members.remove(i);
				break loop;
			}
		}
	}

	public void registerBoat (String member_id, int size, String type){
		loop:
		for (int i = 0; i < System_Management.all_members.size(); i++){
			if (System_Management.all_members.get(i).get_member_Id().equals(member_id)){
				System_Management.all_members.get(i).add_boat(new Boat(size, type));
				break loop;
			}
		}
	}

	public void deleteBoat (String member_id, String boat_no) {

		loop:
		for (int i = 0; i < System_Management.all_members.size(); i++) {
			if (System_Management.all_members.get(i).get_member_Id().equals(member_id)) {

				inner_loop:
				for (int j = 0; j < System_Management.all_members.get(i).get_no_boats(); j++) {
					if (System_Management.all_members.get(i).get_boat_by_index(j).getBoat_no().equals(boat_no)) {
						System_Management.all_members.get(i).remove_boat(j);
						break inner_loop;
					}
				}
				break loop;
			}
		}
	}

	public Boat getBoat (String member_id, String boat_no) {

		for (int i = 0; i < System_Management.all_members.size(); i++) {
			if (System_Management.all_members.get(i).get_member_Id().equals(member_id)) {
				for (int j = 0; j < System_Management.all_members.get(i).get_no_boats(); j++) {
					if (System_Management.all_members.get(i).get_boat_by_index(j).getBoat_no().equals(boat_no)) {
						return System_Management.all_members.get(i).get_boat_by_index(j);
					}
				}
			}
		}
		return null;
	}

	public void editBoat (String member_id, String boat_no, int size, String type) {

		loop:
		for (int i = 0; i < System_Management.all_members.size(); i++) {
			if (System_Management.all_members.get(i).get_member_Id().equals(member_id)) {

				inner_loop:
				for (int j = 0; j < System_Management.all_members.get(i).get_no_boats(); j++) {
					if (System_Management.all_members.get(i).get_boat_by_index(j).getBoat_no().equals(boat_no)) {
						System_Management.all_members.get(i).get_boat_by_index(j).setSize(size);
						System_Management.all_members.get(i).get_boat_by_index(j).setType(type);
						break loop;
					}
				}
			}
		}
	}

	public void loadMembers (String first_name, String last_name, String idNumber) {
		if (checkId(idNumber)){
			if (!find_id(idNumber)){
				all_members.add(new Member(first_name, last_name, idNumber));
			}
		}
	}

	public void loadBoats (String member_id, int size, String type){

		if (isMember(member_id)) {
			loop:
			for (int i = 0; i < System_Management.all_members.size(); i++) {
				if (System_Management.all_members.get(i).get_member_Id().equals(member_id) || System_Management.all_members.get(i).get_id().equals(member_id)) {
					System_Management.all_members.get(i).add_boat(new Boat(size, type));
					break loop;
				}
			}
		}
	}
}