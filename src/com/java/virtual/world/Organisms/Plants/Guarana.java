package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

public class Guarana extends Plant {
    public Guarana(int x, int y, World world) {
        super(0, 0, x, y, world, "Gu", "Guarana", world.getColors().getColor("Guarana"));
    }
    public Guarana(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Guarana",world.getColors().getColor("Guarana"),lifetime,breedingtimeout);
    }
    @Override
    public boolean Collision(Organism other) {
        this.setKilled();
        other.AddGuaranaBoost();
        world.AddLog(other, this, this.coordinates, "GUARANA");
        return true;
    }
}


