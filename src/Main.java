/* The Jolly Pirate
   Main.java
   Created by Mauro J. Pappaterra on 12 of March 2016.*/

import controller.Controller;
import model.System_Management;
import view.View;

public class Main
{
    public static void main(String[] args){
        View v = new View(); // VIEW
        System_Management m = new System_Management(); // MODEL
        Controller c = new Controller(); // CONTROLLER

        c.run(v, m);
    }
}
