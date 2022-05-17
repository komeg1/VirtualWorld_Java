package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.Organisms.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public abstract class Plant extends Organism {
    public Plant(int power, int initiative, int x, int y, World world, String sign,String fullname,Color color) {
        super(world, initiative, power, new Coordinates(x, y), color, sign,fullname);

    }

    public Plant(int power, int initiative, int x, int y, World world, String sign, String fullname, Color color, int lifetime, int breedingtimeout) {
        super(world,initiative,power,new Coordinates(x,y),color,sign,fullname,lifetime,breedingtimeout);
    }

    public void Spread(){
        Coordinates currentCoords = this.coordinates;
        Vector<Coordinates> surrounding = GenerateNewCoordinates(1);
        if (surrounding.size() > 0)
        {
            //world.AddLog(this,this,this.coordinates,"BREED");
            Coordinates newCoords = RandomCoordinate(surrounding);
            world.AddOrganism(newCoords,this.getFullName());
            world.getWorldBoard()[newCoords.GetY()][newCoords.GetX()].setBreedingTimeout(15);
            this.setBreedingTimeout(15);
        }
    }

    @Override
    public void Action() {
        if (SpreadProbability() && this.getBreedingTimeout() == 0)
        {
            this.Spread();
            return;
        }
        if (this.getBreedingTimeout() > 0)
        this.setBreedingTimeout(this.getBreedingTimeout()-1);
    }

    @Override
    public boolean Collision(Organism other) {
        Organism[][] worldboard = world.getWorldBoard();
        if (this.getPower() > other.getPower()) {
            world.AddLog(this, other, this.coordinates, "KILL");
            other.setKilled();
            worldboard[other.getCoordinates().GetY()][other.getCoordinates().GetX()] = null;
            return false;
        }
        else {
            world.AddLog(other, this, this.coordinates, "EAT");
            this.setKilled();
            worldboard[this.coordinates.GetY()][this.coordinates.GetX()] = null;
            return true;
            }

        }

    boolean SpreadProbability()
    {
        Random seed = new Random();
        int choice = seed.nextInt(100);
        return choice<50;
    }

}



