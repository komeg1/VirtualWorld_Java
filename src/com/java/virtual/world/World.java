package com.java.virtual.world;

import com.java.virtual.world.Organisms.Animals.Wolf;
import com.java.virtual.world.Organisms.Organism;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Array;
import java.util.ArrayList;

public class World {
    private ArrayList<Organism> organisms = new ArrayList<>();
    private Organism[][] worldBoard;
    private final int worldX;
    private final int worldY;

    public World(int x,int y){
        this.worldX=x;
        this.worldY=y;
        worldBoard = new Organism[y][x];
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++)
                worldBoard[i][j]=null;
        new Wolf(0,0,this);
        new Wolf(1,1,this);

    }

    public int getWorldY(){
        return worldY;
    }
    public int getWorldX(){
        return worldX;
    }
    public Organism[][] getWorldBoard(){
        return worldBoard;
    }
    public void setOrganismsArray(ArrayList<Organism> arr){
        this.organisms = arr;
    }
    public void setWorldBoard(Organism[][] worldBoard){
        this.worldBoard=worldBoard;
    }
    public ArrayList<Organism> getOrganismsArray() {
        return organisms;
    }
    public void NextTurn(){
        System.out.println(organisms.size());
        for (Organism organism : organisms) {
            if(organism.getKilled()==0)
                organism.Action();
        }
        for(int i=0;i<worldY;i++) {
            for (int j = 0; j < worldX; j++) {
                if(worldBoard[i][j]!=null)
                    System.out.print(worldBoard[i][j].getSign());
                else
                    System.out.print("G");
            }
        System.out.println();
        }
        ClearDeaths();
    }
    public void ClearDeaths(){
        ArrayList<Integer> toRemove = new ArrayList<>();
        for(Organism organism : organisms){
            if(organism.getKilled()==1)
                toRemove.add(organism.GetIndex());
        }
        for(Integer x : toRemove) {
            int index = x;
            organisms.remove(index);
        }
        toRemove.clear();
    }
}
