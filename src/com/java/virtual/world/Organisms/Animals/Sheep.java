package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.WorldManager.World;

public class Sheep extends Animal{
    public Sheep(int x, int y, World world) {
        super(4, 4, x, y, world, "S","Sheep", world.getColors().getColor("Sheep"));
    }
    public Sheep(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Sheep",world.getColors().getColor("Sheep"),lifetime,breedingtimeout);
    }



    }


