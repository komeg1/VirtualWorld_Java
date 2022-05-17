package com.java.virtual.world.WorldManager;

import com.java.virtual.world.Organisms.Coordinates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganismListener implements ActionListener {
    Coordinates coords;
    String name;
    World world;

    public OrganismListener(int x, int y, World world, String name) {
        coords = new Coordinates(x, y);
        this.name = name;
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.AddOrganism(coords, name);
    }
}
