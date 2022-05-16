package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal{

    public Turtle(int x,int y, World world) {
        super(2, 1, x, y, world, "T","Turtle", Color.cyan);
    }

    @Override
    public void Action(){
        if(canMove()) {
            Coordinates oldCoords = this.coordinates;
            Coordinates newCoords = RandomCoordinate(GenerateNewCoordinates(0));
            Organism[][] worldboard = world.getWorldBoard();

            if (worldboard[newCoords.GetY()][newCoords.GetX()] == null)
                this.setCoordinates(newCoords);
            else {
                Organism other = worldboard[newCoords.GetY()][newCoords.GetX()];
                if (worldboard[newCoords.GetY()][newCoords.GetX()] != null) {
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
            this.setLifetime(this.getLifetime() + 1);
        }
        
    }

    private boolean canMove() {
        Random r = new Random();
        int choice = r.nextInt(5);
        return choice == 1;
    }

    @Override
    public boolean Collision(Organism other) {
        if (this.isBlocked(other)) {
            world.AddLog(this,other,this.coordinates,"BLOCK");
            return false;
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

    boolean isBlocked(Organism other){
        return other.getPower() < 5;
    }
}
