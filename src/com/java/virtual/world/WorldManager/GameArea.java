package com.java.virtual.world.WorldManager;

import com.java.virtual.world.Inteface.OrganismField;
import com.java.virtual.world.Organisms.Organism;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private final OrganismField[][] area;
    private final World world;
    private final int y;
    private final int x;
    GameArea(int x,int y,World world)
    {
        this.area= new OrganismField[x][y];
        this.x =x;
        this.y =y;

        this.world=world;
        CreateArea();

    }

    public void updateArea(){
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++) {
                area[i][j].SetField(new Color(0,153,0), new JLabel(""));
            }
        for(int i=0;i<world.getOrganismsArray().size();i++) {
            Organism organism = world.getOrganismsArray().get(i);
            if(organism.getKilled()==0)
            {

                int organism_x = organism.getCoordinates().GetX();
                int organism_y = organism.getCoordinates().GetY();
                Color organism_color = organism.getColor();
                String organism_sign = organism.getSign();
                area[organism_y][organism_x].SetField(organism_color, new JLabel(organism_sign));
            }
        }
    }

    public OrganismField[][] getArea() {
        return area;
    }

    public void CreateArea() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                area[i][j] = new OrganismField(new Color(0, 153, 0), "", j, i, world);
            }
        }
        for(int i=0;i<world.getOrganismsArray().size();i++) {
            Organism organism = world.getOrganismsArray().get(i);
            int organism_x = organism.getCoordinates().GetX();
            int organism_y = organism.getCoordinates().GetY();
            Color organism_color = organism.getColor();
            String organism_sign = organism.getSign();
            area[organism_y][organism_x] =
                    new OrganismField(organism_color, organism_sign,organism_x,organism_y,world);
        }
    }

}
