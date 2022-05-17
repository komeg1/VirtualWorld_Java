package com.java.virtual.world.SavesManagement;

import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Saving {
    private World world;

    public Saving(World world) {
        this.world = world;
    }

    public void CreateFile() {
    try {
        File save = new File("save.txt");
        if (save.createNewFile()) {
            System.out.println("File created: " + save.getName());
        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }


    }

    public void Save(){
        try {
            FileWriter output = new FileWriter("save.txt");
            output.write(world.getWorldX()+ world.getWorldY() + world.get);
            for(Organism organism: world.getOrganismsArray()) {
                output.write(organism.getSign() + " " +
                                 organism.getFullName() +" " +
                                organism.getCoordinates().GetX() +" " +
                                organism.getCoordinates().GetY() + " "+
                                organism.getPower();
            }
        }
        catch (IOException e){

        }
    }

}
