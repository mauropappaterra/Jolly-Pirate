/* The Jolly Pirate
   Controller.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/
package controller;

import model.System_Management;
import view.View;
import java.io.*;

public class Controller {
    public void run (View view, System_Management model) {

        load_database(model);
        view.start_screen();

        boolean exit = false;

        while (!exit) {

            char choose = view.home_screen();
            view.gap();

            if (choose == 'q'){
                exit = true;
            } else if (choose == '1') {
                choose = view.explore_screen();
                view.gap();

                if (choose == 'q') {
                    exit = true;
                } else if (choose == '1'){
                    String id = view.display_id(model);
                    if (!id.equals("cancel")) {
                        view.printMember(model, id);
                    }
                    view.gap();
                } else if (choose == '2'){
                    view.printCompact(model);
                    if (view.navigate_screen() == 'q') {
                        exit = true;
                    }
                    view.gap();

                }else if (choose == '3'){
                    view.printVerbose(model);
                    if (view.navigate_screen() == 'q') { exit = true; }
                    view.gap();
                }
            }
            else if (choose == '2'){
                choose = view.member_screen();
                view.gap();
                if (choose == 'q') {
                    exit = true;
                }
                else if (choose == '1'){
                    view.add_member_screen(model);
                    view.gap();
                } else if (choose == '2'){
                    String id = view.display_id(model);
                    view.edit_member_screen(id, model);
                    view.gap();

                }
                else if (choose == '3'){
                    String id = view.display_id(model);

                    if (!id.equals("cancel")) {

                        view.display_member_delete (id, model.returnMember(id).get_first(), model.returnMember(id).get_last());

                        model.deleteMember(id);
                    }
                    view.gap();
                }
            } else if (choose == '3'){
                choose = view.boat_screen();
                view.gap();

                if (choose == 'q') {
                    exit = true;
                }
                else if (choose == '1'){
                    view.register_boat_screen(model);
                    view.gap();
                } else if (choose == '2'){
                    view.edit_boat_screen(model);
                    view.gap();
                }else if (choose == '3'){
                    view.delete_boat_screen(model);
                    view.gap();
                }
            }
        }

        if (view.save_screen() == 'y') {
            save_database(model);
        }

        view.exit_screen();
    }


    private void save_database(System_Management model) {
        try {
            String line = "";

            for (int i = 0; i < model.get_member_size(); i++) {
                line += model.get_member_by_index(i).get_first() + ","
                        + model.get_member_by_index(i).get_last() + ","
                        + model.get_member_by_index(i).get_id() + ","
                        + model.get_member_by_index(i).get_no_boats() + ",";

                int boats = model.get_member_by_index(i).get_no_boats();

                for(int j = 0; j < boats ; j++){
                    line += model.get_member_by_index(i).get_boat_by_index(j).getSize() + ","
                    + model.get_member_by_index(i).get_boat_by_index(j).getType() + ",";
                }
            }

            File file = new File("database.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter file_writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(file_writer);
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load_database (System_Management model) {
        File file = new File("database.csv");
        if (file.exists()) {
            try{
                BufferedReader reader = new BufferedReader(new FileReader("database.csv"));
                String line = reader.readLine();
                if (line != null){

                    String aux = "";
                    String first_name = "";
                    String last_name = "";
                    String id = "";
                    int counter = 0;

                    for(int i=0; i<line.length();i++){
                        if(line.charAt(i) == ','){
                            i++;
                            if(counter == 0){
                                first_name = aux;
                                aux = "";
                            }
                            if(counter == 1){
                                last_name = aux;
                                aux = "";
                            }
                            if(counter == 2){
                                id = aux;
                                model.loadMembers(first_name, last_name, id);
                                aux = "";
                                first_name = "";
                                last_name = "";

                                while(line.charAt(i) != ',' && i < line.length()-1){
                                    aux += line.charAt(i);
                                    i++;
                                }

                                int totalBoats = Integer.parseInt(aux);
                                int loadedBoats = 0;
                                aux = "";


                                while(loadedBoats < totalBoats)
                                {
                                    aux = "";
                                    int size = 0;
                                    String type = "";
                                    counter = 0;

                                    for(int j=i+1; j<line.length(); j++)
                                    {
                                        if(line.charAt(j) == ',')
                                        {
                                            if(counter == 0){
                                                size += Integer.parseInt(aux);
                                                aux = "";
                                            }
                                            else if(counter == 1)
                                            {
                                                type = aux;
                                                aux = "";

                                                model.loadBoats(id, size, type);
                                                aux = "";
                                                counter = -1;

                                                if(loadedBoats < totalBoats){
                                                    i = j;
                                                    j = line.length()-1;
                                                }
                                            }

                                            counter++;
                                        }
                                        if(line.charAt(j) != ',')
                                            aux += line.charAt(j);
                                    }
                                    loadedBoats++;
                                }
                                counter = -1;
                            }
                            counter++;
                        }
                        if(line.charAt(i) != ','){
                            aux += line.charAt(i);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}