package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.WorldManager.World;

public class Dandelion extends Plant{
    public Dandelion(int x, int y, World world) {
        super(0, 0, x, y, world, "D","Dandelion", world.getColors().getColor("Dandelion"));
    }
    public Dandelion(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Dandelion",world.getColors().getColor("Dandelion"),lifetime,breedingtimeout);
    }

    @Override
    public void Action() {
        boolean done = false;
        for (int i = 0; i < 3; i++) {
            if (SpreadProbability() && this.getBreedingTimeout() == 0) {
                this.Spread();
                return;
            }
            }
            if (this.getBreedingTimeout() > 0)
                this.setBreedingTimeout(this.getBreedingTimeout() - 1);

    }
}
