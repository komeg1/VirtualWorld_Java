package com.java.virtual.world.SavesManagement;

import com.java.virtual.world.Organisms.Animals.Human;
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
            CreateFile();
            FileWriter output = new FileWriter("save.txt");
            output.write(world.getWorldX()+" "+ world.getWorldY()+"\n");
            for(Organism organism: world.getOrganismsArray()) {
                output.write(organism.getSign() + " " +
                                 organism.getFullName() +" " +
                                organism.getCoordinates().GetX() +" " +
                                organism.getCoordinates().GetY() + " "+
                                organism.getPower() + " "+
                                organism.getInitiative() + " " +
                                organism.getLifetime() + " "+
                                organism.getBreedingTimeout() + " ");
                if(organism.getSign() == "H")
                {
                    Human human = (Human)organism;
                    if(human.getSkillIsActive())
                        output.write(1+" ");
                    else
                        output.write(0+" ");
                    output.write(human.getSkillCooldown()+" ");
                    output.write(human.getSkillTurnLeft()+" ");
                }
                output.write("\n");

            }
            output.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
