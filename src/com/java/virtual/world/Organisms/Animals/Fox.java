package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Organisms.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

import java.util.Vector;

public class Fox extends Animal {
    public Fox(int x, int y, World world) {
        super(3, 7, x, y, world, "F", "Fox",world.getColors().getColor("Fox"));
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

    @Override
    public Vector<Coordinates> GenerateNewCoordinates(int action){
        Vector<Coordinates> coordinates = new Vector<>();
        int x1 = this.coordinates.GetX() - 1,
                x2 = this.coordinates.GetX() + 1,
                y1 = this.coordinates.GetY() - 1,
                y2 = this.coordinates.GetY() + 1;

        if (x1 < 0)
            x1 = 0;
        if (y1 < 0)
            y1 = 0;
        if (x2 == world.getWorldX())
            x2--;
        if (y2 == world.getWorldY())
            y2--;
        if(action==1)
        {
            for (int i = y1; i <= y2; i++)
                for (int j = x1; j <= x2; j++) {
                    Coordinates newCoordinates = new Coordinates(j,i);
                    if (world.getWorldBoard()[i][j] == null)
                        coordinates.add(newCoordinates);
                }
        }
        else {
            for (int i = y1; i <= y2; i++)
                for (int j = x1; j <= x2; j++) {
                    Coordinates newCoordinates = new Coordinates(j, i);
                    if (world.getWorldBoard()[i][j] == this)
                        continue;
                    else
                    {
                        if(world.getWorldBoard()[i][j]!=null)
                        {
                            Organism other = world.getWorldBoard()[i][j];
                            if(IsStronger(other))
                                coordinates.add(newCoordinates);
                        }
                        else if(world.getWorldBoard()[i][j]==null)
                            coordinates.add(newCoordinates);
                    }
                }
        }

        return coordinates;
    }

    boolean IsStronger(Organism other)
    {

        return this.getPower() > other.getPower();
    }
}

