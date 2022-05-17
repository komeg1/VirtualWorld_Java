package com.java.virtual.world.WorldManager;

import com.java.virtual.world.Inteface.Colors;
import com.java.virtual.world.Organisms.Animals.*;
import com.java.virtual.world.Organisms.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.Organisms.Plants.*;
import com.java.virtual.world.SavesManagement.Saving;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class World implements KeyListener {
    private Vector<Organism> organisms = new Vector<>();
    private Organism[][] worldBoard;
    private final int worldX;
    private final int worldY;
    protected GameArea area;
    private int turn;
    private Human human;
    private final int KEY_NULL = 0;
    private final int KEY_UP = 1;
    private final int KEY_LEFT =2;
    private final int KEY_RIGHT = 3;
    private final int KEY_DOWN = 4;
    private final int KEY_F = 5;
    private int humanMove=KEY_NULL;
    private Vector<String> logs;
    private Colors colors = new Colors();

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

        human =new Human(0,0,this);
        StartRandomGame();
    }

    public Colors getColors(){
        return colors;
    }
    public int getWorldY(){
        return worldY;
    }
    public int getWorldX(){
        return worldX;
    }
    public int getHumanMove(){return humanMove;}
    public GameArea getArea(){return area;}
    public Organism[][] getWorldBoard(){
        return worldBoard;
    }
    public Vector<Organism> getOrganismsArray() {
        return organisms;
    }
    public Vector<String> getLogs(){
        return logs;

    }
    public Human getHuman(){
        return human;
    }
    public void setLogs(Vector<String> logs){
        this.logs=logs;
    }

    public void setHumanMove(int move){
        this.humanMove = move;
    }
    public void setOrganismsArray(Vector<Organism> arr){
        this.organisms = arr;
    }
    public void setWorldBoard(Organism[][] worldBoard){
        this.worldBoard=worldBoard;
    }



    public void NextTurn(){
        sortOrganisms();

    turn++;
        logs.addElement("TURA "+turn);
        for (int i=0;i<organisms.size(); i++) {
            if(organisms.get(i).getKilled()==0) {
                organisms.get(i).Action();

            }
        }
        sortOrganisms();
        ClearDeaths();

        area.updateArea();
    }
    public void ClearDeaths(){
        ArrayList<Integer> toRemove = new ArrayList<>();
        for(Organism organism : organisms){
            if(organism.getKilled()==1) {
                worldBoard[organism.getCoordinates().GetY()][organism.getCoordinates().GetX()]=null;
                toRemove.add(organism.GetIndex());
            }
        }
        toRemove.sort(Collections.reverseOrder());
        for(Integer x : toRemove) {
            int index = x;
            organisms.remove(index);
        }
        toRemove.clear();
    }






    public void AddLog(Organism a, Organism b, Coordinates coords, String type)
    {
        if(Objects.equals(type, "KILL"))
            logs.addElement("ZABOJSTWO: '"+ a.getSign() + "' zabija '"+b.getSign()+"' na pozycji x: "+coords.GetX()+ " y: "+coords.GetY());
        else if(Objects.equals(type,"ESCAPE"))
            logs.addElement("UCIECZKA: '"+a.getSign()+"' ucieka przed walką");
        else if(Objects.equals(type,"BLOCK"))
            logs.addElement("BLOK: '"+a.getSign()+"' blokuje atak '"+b.getSign()+"'");
        else if(Objects.equals(type,"BREED"))
            logs.addElement("ROZMNOZENIE: '"+a.getSign()+"' na polu x: "+coords.GetX()+" y: "+coords.GetY());
        else if(Objects.equals(type,"GUARANA"))
            logs.addElement("WZMOCNIENIE: '"+a.getSign() + "' zjadł guaranę. Jego siła wynosi: "+a.getPower());
        else if(Objects.equals(type,"BERRIES"))
            logs.addElement("SMIERC: '"+a.getSign() + "zjadł wilcze jagody i umiera.");



    }

    public void sortOrganisms(){
        organisms.sort(new OrganismComparator());
    }

    public void AddOrganism(Coordinates coords,String name){
        Organism toAdd;
        if(worldBoard[coords.GetY()][coords.GetX()]!=null)
            showMessageDialog(null, "Te pole nie jest wolne");
            else
        {
            switch (name) {
                case "Wolf" -> toAdd = new Wolf(coords.GetX(), coords.GetY(), this);
                case "Antelope" -> toAdd = new Antelope(coords.GetX(), coords.GetY(), this);
                case "Sheep" -> toAdd = new Sheep(coords.GetX(), coords.GetY(), this);
                case "Turtle" -> toAdd = new Turtle(coords.GetX(), coords.GetY(), this);
                case "Fox" -> toAdd = new Fox(coords.GetX(), coords.GetY(), this);
                case "Grass" -> toAdd = new Grass(coords.GetX(), coords.GetY(), this);
                case "Dandelion" -> toAdd = new Dandelion(coords.GetX(), coords.GetY(), this);
                case "Guarana" -> toAdd = new Guarana(coords.GetX(), coords.GetY(), this);
                case "Berries" -> toAdd = new WolfBerries(coords.GetX(), coords.GetY(), this);
                case "Sosnowski's Hogweed" -> toAdd = new SosnowskiHogweed(coords.GetX(), coords.GetY(), this);
            }

            area.updateArea();
        }


    }

    public void StartRandomGame(){
        int boardSize = worldY*worldX;
         int maxCreaturesAmount = (int)Math.round(0.03*boardSize);
        String[] organismsNames = {"Wolf","Antelope","Sheep","Turtle","Fox","Grass","Dandelion","Guarana","Sosnowski's Hogweed","Berries",};
        Random seed = new Random();
        int x,y;

        for(int i=0;i<maxCreaturesAmount;i++) {

            do {
                x = seed.nextInt(worldX);
                y = seed.nextInt(worldY);
            }
            while (worldBoard[y][x] != null);
            AddOrganism(new Coordinates(x,y),organismsNames[seed.nextInt(organismsNames.length)]);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
