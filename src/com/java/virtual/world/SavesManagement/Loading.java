package com.java.virtual.world.SavesManagement;

import com.java.virtual.world.Inteface.GameHUD;
import com.java.virtual.world.WorldManager.World;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loading {


    public Loading(){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                final String name = f.getName();
                return name.endsWith(".txt");
            }
            @Override
            public String getDescription() {
                return "*.txt";
            }
        });
        File workingDirectory = new File(System.getProperty("user.dir")+"/Saves");
        if(!workingDirectory.exists())
            workingDirectory.mkdir();
        filechooser.setCurrentDirectory(workingDirectory);
        filechooser.showOpenDialog(null);

            Load(filechooser.getSelectedFile());

    }

    public void Load(File file){
        try {
            Scanner input = new Scanner(file);
            String data = input.nextLine();
            String[] splitValues = data.split(" ");
            new GameHUD(Integer.parseInt(splitValues[0]),Integer.parseInt(splitValues[1]),input);

            input.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
