package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

public class WolfBerries extends Plant{
    public WolfBerries(int x, int y, World world) {
        super(99, 0, x, y, world, "B","Berries", world.getColors().getColor("Berries"));
    }
    public WolfBerries(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Berries",world.getColors().getColor("Berries"),lifetime,breedingtimeout);
    }
    @Override
    public boolean Collision(Organism other){
        this.setKilled();
        other.setKilled();
        world.AddLog(other,this,this.coordinates,"BERRIES");
        return false;
    }
}
