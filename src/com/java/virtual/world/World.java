package com.java.virtual.world;

import com.java.virtual.world.Organisms.Animals.Antelope;
import com.java.virtual.world.Organisms.Animals.Sheep;
import com.java.virtual.world.Organisms.Animals.Wolf;
import com.java.virtual.world.Organisms.Organism;

import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Array;
import java.util.*;

public class World {
    private ArrayList<Organism> organisms = new ArrayList<>();
    private Organism[][] worldBoard;
    private final int worldX;
    private final int worldY;
    protected GameArea area;
    private int turn;
    private Vector<String> logs;
    public World(int x,int y){
        this.worldX=x;
        this.worldY=y;
        this.turn=0;
        area = new GameArea(worldX,worldY,this);
        worldBoard = new Organism[y][x];
        logs = new Vector<>();
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++)
                worldBoard[i][j]=null;


        new Wolf(0,0,this);
        new Sheep(3,3,this);
        new Antelope(5,5,this);

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
    public ArrayList<Organism> getOrganismsArray() {
        return organisms;
    }
    public Vector<String> getLogs(){
        return logs;

    }
    public void setLogs(Vector<String> logs){
        this.logs=logs;
    }

    public void setOrganismsArray(ArrayList<Organism> arr){
        this.organisms = arr;
    }
    public void setWorldBoard(Organism[][] worldBoard){
        this.worldBoard=worldBoard;
    }



    public void NextTurn(){
        sortOrganisms();
    turn++;
        logs.addElement("TURA "+turn);
        for (Organism organism : organisms) {
            if(organism.getKilled()==0)
                organism.Action();
        }
        ClearDeaths();
        PrintLogs();
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
    public void PrintLogs(){
        if (logs.size() < 10) {
            for (String log : logs)
                System.out.println(log);

        }
        else{
            for (int i = logs.size() - 10; i < logs.size(); i++)
                System.out.println(logs.get(i));
        }
    }




    public void AddLog(Organism a, Organism b,Coordinates coords, String type)
    {
        if(Objects.equals(type, "KILL"))
            logs.addElement("ZABOJSTWO: '"+ a.getSign() + "' zabija '"+b.getSign()+"' na pozycji x: "+coords.GetX()+ " y: "+coords.GetY());
        else if(Objects.equals(type,"ESCAPE"))
            logs.addElement("UCIECZKA: '"+a.getSign()+"' ucieka przed walką");


    }

    public void sortOrganisms(){
        organisms.sort(new OrganismComparator());
    }

    public void AddOrganism(Coordinates coords,String name){
        Organism toAdd;
        switch(name)
        {
            case "Wolf":
                toAdd = new Wolf(coords.GetX(),coords.GetY(),this);
                break;
        }
        sortOrganisms();
        area.updateArea();




    }
}
