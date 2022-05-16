package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    public Fox(int x, int y, World world) {
        super(3, 7, x, y, world, "F", "Fox", Color.orange);
    }

    @Override
    public void Action() {
        Coordinates currentCoords = getCoordinates();
        Coordinates newCoords = RandomCoordinate(GenerateNewCoordinates(0));
        if (newCoords.GetY() != -1 && CheckIfOrganism(newCoords)) {
            Organism other = FindOrganism(newCoords);
            other.Collision(this);

        } else {
            if (newCoords.GetX() != -1)
                this.setCoordinates(newCoords);
            this.setLifetime(this.getLifetime()+1);
        }
        if (this.getBreedingTimeout() > 0)
        this.setBreedingTimeout(this.getBreedingTimeout()-1);
    }
}