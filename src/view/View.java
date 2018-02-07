/* The Jolly Pirate
   View.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/

package view;

import model.*;
import java.util.Scanner;

public class View {

    public void start_screen(){
        String text =
                 "\n ###############################################################" +
                 "\n #####       THE JOLLY PIRATE BOAT CLUB DATABASE         #######" +
                 "\n ###############################################################" +
                 "\n Coded by Mauro J. Pappaterra                               v1.0\n";
        System.out.println(text);
    }

    public char home_screen(){
        String message = "Select one of the available options and press <enter>" +
                "\n1 - EXPLORE => Look up information on the database " +
                "\n2 - MEMBERS => Add, update or delete a member from the database" +
                "\n3 - BOATS => Add, update or delete a boat from the database" +
                "\n\nq - Quit";
        char[] options = {'1', '2', '3', 'q'};
        return display_select(message, options);
    }

    public char explore_screen(){
        String message = "Look up information on the database"
                + "\n1 - Look up a member"
                + "\n2 - Show compact list of all members"
                + "\n3 - Show verbose list of all members"
                + "\n\nr - Return"
                + "\nq - Quit";
        char[] options = {'1', '2', '3', 'r', 'q'};
        return display_select(message, options);
    }

    public char member_screen(){
        String message = "Add, update or delete a member from the database"
                + "\n1 - Add new member"
                + "\n2 - Update member"
                + "\n3 - Delete member"
                + "\n\nr - Return"
                + "\nq - Quit";
        char[] options = {'1', '2', '3', 'q', 'r' };
        return display_select(message, options);
    }

    public char boat_screen(){
        String message = "Add, update or delete a boat from the database"
                + "\n1 - Register new boat"
                + "\n2 - Update boat"
                + "\n3 - Unregister boat"
                + "\n\nr - Return"
                + "\nq - Quit";
        char[] options = {'1', '2', '3', 'q', 'r' };
        return display_select(message, options);
    }

    public char save_screen(){
        String message = "Save changes before exit?"
                + "\ny - Yes"
                + "\nn - No";
        char[] options = {'y', 'n'};
        return display_select (message, options);
    }

    public char navigate_screen(){
        String message = "\nr - Return "
                        +"\nq - Quit";
        char[] options = {'q', 'r'};
        return display_select(message, options);
    }

    public void exit_screen(){
        System.out.println("\nExiting The Jolly Pirate Boat Club Database");
    }

    public void gap(){
        System.out.println("\n\n");
    }

    public void add_member_screen (System_Management model) {

        boolean flag = false;

        String message = "Add new member \n<Enter 'c' at anytime to Cancel>"
                + "\n\nMember's First Name: ";
        String first_name = display_string(message);

        if (!first_name.equals("cancel")) {

            message = "Member's Last Name: ";
            String last_name = display_string(message);

            if (!last_name.equals("cancel")) {

                message = "Member's Personal Id Number: ";
                String id = display_string(message);

                loop:
                while (!model.checkId(id)) {
                    message = "Not a valid id number!\nMembers Personal Id Number (format YYMMDD-NNNN): ";
                    id = display_string(message);

                    if (id.equals("cancel")) {
                        flag = true;
                        break loop;
                    }
                }

                if (!id.equals("cancel")) {
                    if (!model.find_id(id)){
                        model.addMember(first_name, last_name, id);
                        System.out.println ("\n" + first_name + " " + last_name + " ("+ id + ") was added to the system!");
                    } else {
                        System.out.println(id + " is already in the system!");
                    }
                } else {
                    flag = true;
                }
            } else {
                flag = true;
            }

            if (flag) {
                System.out.println("Add Member Cancelled");
            }
        }
    }

    public void edit_member_screen (String member_id, System_Management model) {
        Member member = model.returnMember(member_id);

        String input = "";
        boolean flag = false;

        String message = "Edit Member \n<Enter 'c' at anytime to Cancel>" +
                "\n 1 - Edit First Name" +
                "\n 2 - Edit Last Name" +
                "\n 3 - Edit Id Number";
        char[] options = {'1', '2', '3', 'c'};
        char choose = display_select(message, options);

        if (choose != 'c') {
            if (choose == '1') {
                System.out.println("Update First Name:");
                input = input_string();
                if (!input.equals("cancel")) {
                    member.set_first(input);
                    System.out.println("Member's first name updated to " + input);
                } else {
                    flag = true;
                }
            } else if (choose == '2') {
                System.out.println("Update Last Name:");
                input = input_string();
                if (!input.equals("cancel")) {
                    member.set_last(input);
                    System.out.println("Member's last name updated to " + input);
                } else {
                    flag = true;
                }
            } else {
                System.out.println("Update personal Id number:");
                loop:
                while (!model.checkId(input)) {
                    input = input_string();
                    if (input.equals("cancel")) {
                        break loop;
                    }
                    if (!model.checkId(input)) {
                        System.out.println("Not a valid id number!\nMembers Personal Id Number (format YYMMDD-NNNN): ");
                    }
                }
                if (!input.equals("cancel")) {
                    member.set_id(input);
                    System.out.println("Social security nr updated to " + input);
                } else {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }

        if (flag){
            System.out.println ("Member Edit Cancelled.");
        }
    }

    public void register_boat_screen(System_Management model){

        boolean flag = false;

        String message = "Register Boat \n<Enter 'c' at anytime to Cancel>"
                + "\nMembership Number: ";

        String member_id = display_string(message);

        loop:
        while (!model.isMember(member_id) && !member_id.equals("cancel")) {
            message = "Membership number not found in database. Try again: ";
            member_id = display_string(message);

            if (member_id.equals("cancel")) {
                flag = true;
                break loop;
            }
        }

        if (!member_id.equals("cancel")) {

         message = "\nSelect Type of Boat:"
                 +"\n1 - Sailboat"
                 +"\n2 - Motorsailer"
                 +"\n3 - Kayak/Canoe"
                 +"\n4 - Other";
        char[] options = {'1','2','3','4','c'};
        char aux = display_select(message, options);

        String type = "";

        switch (aux) {
            case '1':
                type = "Sailboat";
                break;
            case '2':
                type = "Motorsailer";
                break;
            case '3':
                type = "Kayak/Canoe";
                break;
            case '4':
                type = "Other";
                break;
            case 'c':
                type = "cancel";
                flag = true;
                break;
        }

            if (!type.equals("cancel")){

                message = "Length of boat in feet:";
                int size = display_int(message);

                if (size != -1){
                    model.registerBoat(member_id, size, type);
                    System.out.println ("A new " + type + " registered to "+ member_id + " in the system's database!");
                } else{
                    flag = true;
                }
            }
        }

        if (flag){
            System.out.println("Boat Registration Cancelled.");
        }
    }

    public void edit_boat_screen(System_Management model){
        boolean flag = false;
        String message = "Edit Boat \n<Enter 'c' at anytime to Cancel>"
                + "\nMember's Id Number: ";
        String member_id = display_string(message);

        loop:
        while (!model.isMember(member_id) && !member_id.equals("cancel")) {
            message = "Member Id number not found in database. Try again: ";
            member_id = display_string(message);

            if (member_id.equals("cancel")) {
                flag = true;
                break loop;
            }
        }

        if (!member_id.equals("cancel")){

            message = "Boat number: ";

            loop:
            while (true){
                String boat_no = display_string(message);

                if (!boat_no.equals("cancel")){

                    if (model.getBoat(member_id,boat_no) != null){

                        message = "Make a selection: "
                                + "\n1 - Update Type of Boat"
                                + "\n2 - Update Size of Boat";

                        char[] options = {'1','2','c'};
                        char choose = display_select(message, options);

                        if (choose != 'c'){

                            if (choose == '1'){

                                message = "\nSelect Type of Boat:"
                                        +"\n1 - Sailboat"
                                        +"\n2 - Motorsailer"
                                        +"\n3 - Kayak/Canoe"
                                        +"\n4 - Other";
                                char[] options_2 = {'1','2','3','4','c'};
                                char aux = display_select(message, options_2);

                                int size = model.getBoat(member_id,boat_no).getSize();
                                String type = "";

                                switch (aux) {
                                    case '1':
                                        type = "Sailboat";
                                        break;
                                    case '2':
                                        type = "Motorsailer";
                                        break;
                                    case '3':
                                        type = "Kayak/Canoe";
                                        break;
                                    case '4':
                                        type = "Other";
                                        break;
                                    case 'c':
                                        type = "cancel";
                                        flag = true;
                                        break loop;
                                }
                                model.editBoat(member_id,boat_no,size,type);
                                System.out.println ("Boat " + boat_no + " updated to type " + type + ".");
                            }

                            if (choose == '2'){

                                message = "Length of boat in feet:";
                                int size = display_int(message);

                                if (size != -1){
                                    String type = model.getBoat(member_id,boat_no).getType();
                                    model.editBoat(member_id, boat_no, size, type);
                                    System.out.println ("Boat " + boat_no + " updated to size " + size + " feet.");
                                } else{
                                    flag = true;
                                    break loop;
                                }
                            }

                        } else {
                            flag = true;
                        }

                        break loop;
                    } else {
                        message = "Boat number not found in the database. Try again:";
                    }
                } else {
                    flag = true;
                    break loop;
                }
            }

        } else {
            flag = true;
        }

        if (flag){
            System.out.println("Boat Edition Cancelled.");
        }


    }

    public void delete_boat_screen (System_Management model){
        boolean flag = false;
        String message = "Unregister Boat \n<Enter 'c' at anytime to Cancel>"
                + "\nMember's Id Number: ";
        String member_id = display_string(message);

        loop:
        while (!model.isMember(member_id) && !member_id.equals("cancel")) {
            message = "Member Id number not found in database. Try again: ";
            member_id = display_string(message);

            if (member_id.equals("cancel")) {
                flag = true;
                break loop;
            }
        }

        if (!member_id.equals("cancel")){

            message = "Boat number: ";

            loop:
            while (true){
                String boat_no = display_string(message);

                if (!boat_no.equals("cancel")){

                    if (model.getBoat(member_id,boat_no) != null){
                        model.deleteBoat(member_id, boat_no);
                        System.out.println("Boat " + boat_no + " belonging to member " + member_id + " is now unregistered from the system's database!");
                        break loop;
                    } else {
                        message = "Boat number not found in the database. Try again:";
                    }
                } else {
                    flag = true;
                    break loop;
                }
            }

        } else {
            flag = true;
        }

        if (flag){
            System.out.println("Boat Deletion Cancelled.");
        }
    }

    private char display_select(String message, char[] options){
        System.out.println(message);

        while (true){
            char input = input_char();

            for (int i = 0; i < options.length; i++) {
                if (input == options[i]) {
                    return input;
                }
            }
             System.err.println("You must enter a valid option!");
        }
    }

    private char input_char(){
        while (true){
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            input = fix_input(input);

            if (input.length() == 1) {
                return input.charAt(0);
                }
            else { System.err.println("You must enter a single character input!");
            }
        }
    }

    private int display_int(String message){

        int min = 6;
        int max = 100;

        System.out.println(message);

        while (true){
            String input = input_int();

            if (input.equals('c')) {
                return -1;
            }

            if (check_int(input)){
                int aux = Integer.parseInt(input);
                if (min <= aux && aux <= max){
                    return aux;
                }else{
                    System.err.println("Size must be between " + min + " and " + max + " feet");
                }
            } else {
                System.err.println("Enter a numeric value");
            }
        }
    }

    private boolean check_int(String input){
        boolean flag = true;
        if (!input.isEmpty()) {
            if (Character.isDigit(input.charAt(0)) || input.charAt(0) == '-') {

                for (int i = 1; i < input.length(); i++){
                    if (!Character.isDigit(input.charAt(i))){
                        flag = false;
                    }
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    private String fix_input(String input) {
        input = input.trim();
        input = input.toLowerCase();
        return input;
    }

    private String display_string(String message){
        System.out.println(message);
        return input_string();
    }

    public String display_id(System_Management model){
        String message = "Member's Personal Id Number: ";
        String id = display_string(message);

        loop:
        while (!model.checkId(id) || !model.isMember(id)) {
            message = "Id number not found in the database!\nEnter Members Personal Id Number (format YYMMDD-NNNN): ";
            id = display_string(message);

            if (id.equals("cancel")) {
                break loop;
            }
        }
        return id;
    }

    private String input_string() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            input = input.trim();
            if (input.equalsIgnoreCase("c")) {
                return "cancel";
            } else if (input.length() > 1) {
                return input;
            } else {
                System.err.println("You must enter a multiple character input!");
            }
        }
    }

    private String input_int(){
        while (true){
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            input = input.trim();
            if (input.equalsIgnoreCase("c")){
                return "-1";
            }
                return input;
        }
    }

    public void display_member_delete (String id, String first_name, String last_name){
        System.out.println ("Member " + id + " " + first_name + " " + last_name + " is now deleted from the system!");
    }

    public void printMember (System_Management model, String id) {

        String printout = "\nMembership no.: " + model.returnMember(id).get_member_Id() + "\nId Number: " + id + "\nFirst Name: "
                + model.returnMember(id).get_first() + "\tLast Name: " + model.returnMember(id).get_last() ;

        if (model.returnMember(id).get_no_boats() > 0){
            printout +="\nTotal registered boats for "+ model.returnMember(id).get_member_Id() +": "+ model.returnMember(id).get_no_boats();

            for (int j = 0; j < model.returnMember(id).get_no_boats(); j++){
                printout += "\n(" + (j + 1) + ") Boat no: " + model.returnMember(id).get_boat_by_index(j).getBoat_no() +
                        "\tType: " + model.returnMember(id).get_boat_by_index(j).getType()
                        + "\tLength: " + model.returnMember(id).get_boat_by_index(j).getSize() + " feet";
            }
        } else {
            printout += "\nThis club member has not yet registered any boats in the system's database!\n";
        }

        System.out.println(printout);
    }


    public void printCompact(System_Management model) {

        String printout = "";

        if (model.get_member_size() > 0){
            printout += "\n::COMPACT LIST OF CLUB MEMBERS:::";
            for (int i = 0; i < model.get_member_size(); i++){
                printout += "\nName: " + model.get_member_by_index(i).get_first() + " " + model.get_member_by_index(i).get_last()
                        + "\t\tMembership no.: " + model.get_member_by_index(i).get_member_Id() + "\t\tBoats registered: "
                        + model.get_member_by_index(i).get_no_boats();
            }
        } else {
            printout = "\nThere are no members in the system's database!";
        }

        System.out.println (printout);
    }

    public void printVerbose (System_Management model){
        if (model.get_member_size() > 0){
            System.out.println ("\n::VERBOSE LIST OF CLUB MEMBERS:::");

            for (int i = 0; i < model.get_member_size(); i++){
                printMember (model, model.get_member_by_index(i).get_id());
            }

        } else {
            System.out.println ("\nThere are no members in the system database!");
        }
    }
}