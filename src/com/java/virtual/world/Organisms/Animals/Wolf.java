package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.WorldManager.World;

public class Wolf extends Animal{

    public Wolf(int x, int y, World world) {
        super(9, 5, x, y, world, "W","Wolf", world.getColors().getColor("Wolf"));
    }
    public Wolf(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Wolf",world.getColors().getColor("Wolf"),lifetime,breedingtimeout);
    }


}
