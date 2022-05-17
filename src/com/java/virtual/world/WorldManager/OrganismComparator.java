package com.java.virtual.world.WorldManager;

import com.java.virtual.world.Organisms.Organism;

import java.util.Comparator;

public class OrganismComparator implements Comparator<Organism> {

    // override the compare() method
    public int compare(Organism a, Organism b)
    {
        if(a.getInitiative()==b.getInitiative())
            return 0;
        if(a.getInitiative()>b.getInitiative())
            return -1;
        else
            return 1;
    }
}

