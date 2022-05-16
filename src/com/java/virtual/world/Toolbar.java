package com.java.virtual.world;

import javax.swing.*;

public class Toolbar extends JPopupMenu {
    public int menuLength;

    JMenuItem[] menuItems;

    public Toolbar(int x, int y, World world)
    {
        this.menuLength = 5;

        menuItems = new JMenuItem[menuLength];
        menuItems[0] = new JMenuItem("Antelope");
        menuItems[1] = new JMenuItem("Fox");
        menuItems[2] = new JMenuItem("Sheep");
        menuItems[3] = new JMenuItem("Turtle");
        menuItems[4] = new JMenuItem("Wolf");
        //menuItems[5] = new JMenuItem("Grass");
        //menuItems[6] = new JMenuItem("Sow Thistle");
        //menuItems[7] = new JMenuItem("Belladonna");
        //menuItems[8] = new JMenuItem("Guarana");
        for(int i=0;i<menuLength;i++)
        {
            add(menuItems[i]);
            menuItems[i].addActionListener(new OrganismListener(x,y,world,menuItems[i].getText()));
        }
    }
}
