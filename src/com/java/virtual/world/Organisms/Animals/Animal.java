package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.ArrayList;

public class Animal extends Organism {
    public Animal(int power, int initiative, int x, int y, World world, String sign,Color color) {
        super(world, initiative, power, new Coordinates(x, y), color, sign);

    }

    @Override
    public void Action() {
        Coordinates oldCoords = this.coordinates;
        Coordinates newCoords = GenerateNewCoordinates(0);
        Organism[][] worldboard = world.getWorldBoard();

        if (worldboard[newCoords.GetY()][newCoords.GetX()] == null)
            this.setCoordinates(newCoords);
        else {
            Organism other = worldboard[newCoords.GetY()][newCoords.GetX()];
            if (worldboard[newCoords.GetY()][newCoords.GetX()] != null) {
                if (other.Collision(this)) {

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
        this.setLifetime(this.getLifetime()+1);
    }

    public boolean Collision(Organism other) {
        Organism[][] worldboard = world.getWorldBoard();
        if(this.getPower()==other.getPower())
        {
            if(this.lifetime<other.getLifetime())
            {
                world.AddLog(other,this,this.coordinates,"KILL");
                this.setKilled();
                worldboard[this.coordinates.GetY()][this.coordinates.GetX()] = null;
                return true;
            }
            else {
                world.AddLog(this,other,this.coordinates,"KILL");
                return false;
            }
        }
        else if(this.getPower()<other.getPower())
        {
            world.AddLog(other,this,this.coordinates,"KILL");
            this.setKilled();
            worldboard[this.coordinates.GetY()][this.coordinates.GetX()] = null;
            return true;
        }
        else {
            world.AddLog(this,other,this.coordinates,"KILL");
            return false;
        }

    }
}

