package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.ArrayList;

public class Animal extends Organism {
    public Animal(int power, int initiative, int x, int y, World world, String sign) {
        super(world, initiative, power, new Coordinates(x, y), Color.red, sign);

    }

    @Override
    public void Action() {
        Coordinates oldCoords = this.coordinates;
        Coordinates newCoords = GenerateNewCoordinates();
        System.out.println(newCoords);
        Organism[][] worldboard = world.getWorldBoard();
        if (worldboard[newCoords.GetY()][newCoords.GetX()] == null) {
            this.setCoordinates(newCoords);

        }
        else {
            Organism other = worldboard[newCoords.GetY()][newCoords.GetX()];
            if (worldboard[newCoords.GetY()][newCoords.GetX()] != null) {
                if (other.Collision(this)) {
                    worldboard[newCoords.GetY()][newCoords.GetX()].setKilled();
                    worldboard[newCoords.GetY()][newCoords.GetX()] = null;
                    this.setCoordinates(newCoords);

                } else {
                    this.setKilled();
                    worldboard[oldCoords.GetY()][oldCoords.GetX()] = null;
                }
            } else {
                this.setCoordinates(newCoords);

            }
            world.setWorldBoard(worldboard);

        }
    }

    public boolean Collision(Organism other) {
        if(this.getPower()==other.getPower())
        {
            if(this.lifetime>other.getLifetime())
                return false;
            else
                return true;
        }
        if(this.getPower()>other.getPower())
            return false;
        else
            return true;

    }
}

