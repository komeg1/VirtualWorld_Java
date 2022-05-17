package com.java.virtual.world.Inteface;

import com.java.virtual.world.WorldManager.OrganismListener;
import com.java.virtual.world.WorldManager.World;

import javax.swing.*;

public class Toolbar extends JPopupMenu {
    public int menuLength;

    JMenuItem[] menuItems;

    public Toolbar(int x, int y, World world)
    {
        this.menuLength = 10;

        menuItems = new JMenuItem[menuLength];
        menuItems[0] = new JMenuItem("Antelope");
        menuItems[1] = new JMenuItem("Fox");
        menuItems[2] = new JMenuItem("Sheep");
        menuItems[3] = new JMenuItem("Turtle");
        menuItems[4] = new JMenuItem("Wolf");
        menuItems[5] = new JMenuItem("Grass");
        menuItems[6] = new JMenuItem("Dandelion");
        menuItems[7] = new JMenuItem("Guarana");
        menuItems[8] = new JMenuItem("Berries");
        menuItems[9] = new JMenuItem("Sosnowski'sHogweed");
        for(int i=0;i<menuLength;i++)
        {
            add(menuItems[i]);
            menuItems[i].addActionListener(new OrganismListener(x,y,world,menuItems[i].getText()));
        }
    }
}
