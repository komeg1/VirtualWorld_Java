package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.WorldManager.World;

public class Grass extends Plant{
    public Grass(int x, int y, World world) {
        super(0, 0, x, y, world, "G","Grass", world.getColors().getColor("Grass"));
    }
}
