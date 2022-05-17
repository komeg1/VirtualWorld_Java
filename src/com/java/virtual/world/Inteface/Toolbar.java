package com.java.virtual.world.Inteface;

import com.java.virtual.world.WorldManager.OrganismListener;
import com.java.virtual.world.WorldManager.World;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPopupMenu {
    public final int menuLength=11;


    JMenuItem[] menu;

    public Toolbar(int x, int y, World world)
    {

        JMenuItem fieldCoordinates = new JMenuItem("X: "+x+" Y: "+y);
        fieldCoordinates.setBackground(Color.black);
        fieldCoordinates.setForeground(Color.white);
        menu= new JMenuItem[]{
                    fieldCoordinates,
                    new JMenuItem("Wolf"),
                    new JMenuItem("Antelope"),
                    new JMenuItem("Fox"),
                    new JMenuItem("Sheep"),
                    new JMenuItem("Turtle"),
                    new JMenuItem("Grass"),
                    new JMenuItem("Dandelion"),
                    new JMenuItem("Guarana"),
                    new JMenuItem("Berries"),
                    new JMenuItem("Sosnowski'sHogweed")};
        for(int i=0;i<menuLength;i++)
        {
            add(menu[i]);
            menu[i].addActionListener(new OrganismListener(x,y,world,menu[i].getText()));
        }

    }
}
