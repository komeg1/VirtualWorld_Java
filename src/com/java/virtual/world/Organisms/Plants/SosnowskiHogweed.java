package com.java.virtual.world.Organisms.Plants;

import com.java.virtual.world.Organisms.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

import java.util.Objects;
import java.util.Vector;

public class SosnowskiHogweed extends Plant{
    public SosnowskiHogweed(int x, int y, World world) {
        super(10, 0, x, y, world, "Ho","Sosnowski'sHogweed", world.getColors().getColor("Sosnowski'sHogweed"));
    }
    public SosnowskiHogweed(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign){
        super(power,initiative,x,y,world,sign,"Sosnowski'sHogweed",world.getColors().getColor("Sosnowski'sHogweed"),lifetime,breedingtimeout);
    }

    @Override
   public void Action(){
        Vector<Coordinates> area = GenerateNewCoordinates(0);
        KillAnimalsAround(area);
        if (SpreadProbability() && this.getBreedingTimeout() == 0)
        {
            this.Spread();
            return;
        }
        if (this.getBreedingTimeout() > 0)
        this.setBreedingTimeout(this.getBreedingTimeout()-1);
    }

    void KillAnimalsAround(Vector<Coordinates> area) {
        Vector<Organism> organisms = world.getOrganismsArray();
        for (int i = 0; i < area.size(); i++)
        {
            Organism organism= FindOrganism(area.get(i));
            if (organism!=null&&!Objects.equals(organism.getSign(), this.getSign()))
            {
                organism.setKilled();
            }

        }



    }
}
