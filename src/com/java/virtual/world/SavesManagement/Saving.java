package com.java.virtual.world.SavesManagement;

import com.java.virtual.world.Organisms.Animals.Human;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class Saving{
    private final World world;
    private final JFrame saveNameInput= new JFrame();
    private final JButton saveButton = new JButton("Save");
    private final JTextField saveInput= new JTextField(10);
    private final JLabel windowInfo = new JLabel("SAVE NAME:");
    private File save;
    private final File workingDirectory;

    public Saving(World world) {
        this.world = world;
        workingDirectory = new File(System.getProperty("user.dir")+"/Saves");
        if(!workingDirectory.exists())
            workingDirectory.mkdir();
        saveButton.addActionListener(e-> {
            if (Objects.equals(saveInput.getText(), ""))
                showMessageDialog(null, "Please insert save filename.");
            else {
                CreateFile(saveInput.getText());
            }
        });

    }

    public void OpenWindow(){

        saveNameInput.setLayout(new BorderLayout());
        saveNameInput.add(windowInfo,BorderLayout.NORTH);
        saveNameInput.add(saveInput,BorderLayout.CENTER);
        saveNameInput.add(saveButton,BorderLayout.SOUTH);
        saveNameInput.setSize(100,100);
        saveNameInput.setVisible(true);

    }
    public void CreateFile(String name) {

        try {
            save = new File(workingDirectory+"/"+name+".txt");
            if (save.createNewFile()) {
                {
                    System.out.println("File created: " + save.getName());
                    Save();
                }
            } else {
                showMessageDialog(null, "File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
    }

    public void Save(){
        try {
            FileWriter output = new FileWriter(workingDirectory+"/"+save.getName());
            output.write(world.getWorldX()+" "+ world.getWorldY()+"\n");
            for(Organism organism: world.getOrganismsArray()) {
                output.write(organism.getSign() + " " +
                                organism.getCoordinates().GetX() +" " +
                                organism.getCoordinates().GetY() + " "+
                                organism.getPower() + " "+
                                organism.getInitiative() + " " +
                                organism.getLifetime() + " "+
                                organism.getBreedingTimeout() + " ");
                if(Objects.equals(organism.getSign(), "H"))
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
            showMessageDialog(null, "Game has been saved sucessfuly.");
            saveNameInput.setVisible(false);
        }
        catch (IOException e){
            showMessageDialog(null, "Can't save the game.");
            e.printStackTrace();
        }


}

}
