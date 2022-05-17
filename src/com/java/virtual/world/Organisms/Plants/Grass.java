package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.WorldManager.World;

public class Grass extends Plant{
    public Grass(int x, int y, World world) {
        super(0, 0, x, y, world, "G","Grass", world.getColors().getColor("Grass"));
    }
    public Grass(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Grass",world.getColors().getColor("Grass"),lifetime,breedingtimeout);
    }
}
