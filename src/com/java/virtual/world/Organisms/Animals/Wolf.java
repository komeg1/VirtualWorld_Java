package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.WorldManager.World;

public class Wolf extends Animal{

    public Wolf(int x, int y, World world) {
        super(9, 5, x, y, world, "W","Wolf", world.getColors().getColor("Wolf"));
    }


}
