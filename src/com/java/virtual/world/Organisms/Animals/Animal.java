package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Animal extends Organism {
    public Animal(int power, int initiative, int x, int y, World world, String sign,String fullname,Color color) {
        super(world, initiative, power, new Coordinates(x, y), color, sign,fullname);

    }

    @Override
    public void Action() {
        Coordinates oldCoords = this.coordinates;
        Coordinates newCoords = RandomCoordinate(GenerateNewCoordinates(0));
        Organism[][] worldboard = world.getWorldBoard();


        if (worldboard[newCoords.GetY()][newCoords.GetX()] == null)
            this.setCoordinates(newCoords);
        else {

            if (worldboard[newCoords.GetY()][newCoords.GetX()] != null) {
                Organism other = worldboard[newCoords.GetY()][newCoords.GetX()];
                System.out.println(this.getSign()+other.getSign());
                if(this.getSign()==other.getSign())
                {
                    if(Breeding(other)) {
                        world.AddLog(this,other,this.coordinates,"BREED");

                    }
                    return;
                }
                if (other.Collision(this)) {

                    this.setCoordinates(newCoords);

                } else {
                    return;
                }
            } else {
                this.setCoordinates(newCoords);

            }
            world.setWorldBoard(worldboard);

        }
        this.setLifetime(this.getLifetime()+1);
        if(this.getBreedingTimeout()>0)
            this.setBreedingTimeout(this.getBreedingTimeout()-1);
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
                other.setKilled();
                worldboard[other.getCoordinates().GetY()][other.getCoordinates().GetX()] = null;
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
            other.setKilled();
            worldboard[other.getCoordinates().GetY()][other.getCoordinates().GetX()] = null;
            return false;
        }

    }

    boolean Breeding(Organism other)
    {
        if (this.getSign()=="H")
            return false;
        if (this.getBreedingTimeout() ==0 && other.getBreedingTimeout() == 0)
        {

            Coordinates newCreatureCoords =RandomCoordinate(PrepareArea(other));
            if (newCreatureCoords.GetX() != -1)
            {
                world.AddOrganism(newCreatureCoords,this.getFullName());
                this.setBreedingTimeout(15);
                other.setBreedingTimeout(15);
                Organism[][] worldboard = world.getWorldBoard();
                worldboard[newCreatureCoords.GetY()][newCreatureCoords.GetX()].setBreedingTimeout(15);
                world.setWorldBoard(worldboard);
                return true;
            }
        }
        return false;
    }
    public Vector<Coordinates> PrepareArea(Organism other) {
        Vector<Coordinates> thisArea = GenerateNewCoordinates(1),
                otherArea = other.GenerateNewCoordinates(1);


        thisArea.addAll(otherArea);

        return thisArea;

    }
}

