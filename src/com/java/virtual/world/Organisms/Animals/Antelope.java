package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Antelope extends Animal {
    public Antelope(int x, int y, World world) {
        super(4, 4, x, y, world, "A", "Antelope",new Color(94, 28, 28));
    }


    @Override
    public boolean Collision(Organism other) {
        if (this.isBlocked()) {
            world.AddLog(this,other,this.coordinates,"ESCAPE");
            if(this.Run())
            return true;
        }
        Organism[][] worldboard = world.getWorldBoard();

        if (this.getPower() == other.getPower()) {
            if (this.lifetime < other.getLifetime()) {
                this.setKilled();
                world.AddLog(other,this,this.coordinates,"KILL");
                worldboard[this.coordinates.GetY()][this.coordinates.GetX()] = null;
                return true;
            }
            else {
                world.AddLog(this,other,this.coordinates,"KILL");
                other.setKilled();
                worldboard[other.getCoordinates().GetY()][other.getCoordinates().GetX()] = null;
                return false;
            }
        } else if (this.getPower() < other.getPower()) {
            this.setKilled();
            world.AddLog(other,this,this.coordinates,"KILL");
            worldboard[this.coordinates.GetY()][this.coordinates.GetX()] = null;
            return true;
        } else {
            world.AddLog(this,other,this.coordinates,"KILL");
            other.setKilled();
            worldboard[other.getCoordinates().GetY()][other.getCoordinates().GetX()] = null;
            return false;
        }

    }


    boolean isBlocked() {
        Random r = new Random();
        int choice = r.nextInt(2);
        return choice == 1;

    }

    boolean Run() {
        Coordinates newCoords = RandomCoordinate(GenerateNewCoordinates(3));
        if(newCoords.GetY() == -1)
            return false;
        else {
            this.setCoordinates(newCoords);
            return true;
        }
    }

    @Override
    public Vector<Coordinates> GenerateNewCoordinates(int action) {
        Vector<Coordinates> coordinates = new Vector<>();
        int x1,x2,y1,y2;
        if(action!=3) {
            x1 = this.coordinates.GetX() - 2;
            x2 = this.coordinates.GetX() + 2;
            y1 = this.coordinates.GetY() - 2;
            y2 = this.coordinates.GetY() + 2;
        }
        else
        {
            x1 = this.coordinates.GetX() - 1;
            x2 = this.coordinates.GetX() + 1;
            y1 = this.coordinates.GetY() - 1;
            y2 = this.coordinates.GetY() + 1;
        }
        if (x1 < 0)
            x1 = 0;
        if (y1 < 0)
            y1 = 0;
        if (x2 >= this.world.getWorldX())
            x2 =this.world.getWorldX()-1;
        if (y2 >= this.world.getWorldY())
            y2 = this.world.getWorldY()-1;
        for (int i = y1; i <= y2; i++)
            for (int j = x1; j <= x2; j++) {
                if (this.coordinates.GetX() == j && this.coordinates.GetY() == i)
                    continue;
                else if (world.getWorldBoard()[this.coordinates.GetY()][this.coordinates.GetX()] == null) {
                    coordinates.addElement(new Coordinates(j, i));
                } else {
                    if (action != 1&&action!=3)
                        coordinates.addElement(new Coordinates(j, i));
                }
            }
        Random seed = new Random();

        return coordinates;
    }
}

