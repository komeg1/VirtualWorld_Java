package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

public class Guarana extends Plant {
    public Guarana(int x, int y, World world) {
        super(0, 0, x, y, world, "G", "Guarana", world.getColors().getColor("Guarana"));
    }

    @Override
    public boolean Collision(Organism other) {
        this.setKilled();
        other.AddGuaranaBoost();
        world.AddLog(other, this, this.coordinates, "GUARANA");
        return true;
    }
}


