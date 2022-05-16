package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;

public class Wolf extends Animal{

    public Wolf(int x, int y, World world) {
        super(9, 5, x, y, world, "W","Wolf", Color.red);
    }


}
