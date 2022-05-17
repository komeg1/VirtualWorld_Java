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
    private final Colors colors = new Colors();

    public World(int x,int y,double fulfillPercentage, int humanX,int humanY){
        this.worldX=x;
        this.worldY=y;
        this.turn=0;
        area = new GameArea(worldX,worldY,this);
        worldBoard = new Organism[y][x];
        logs = new Vector<>();
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++)
                worldBoard[i][j]=null;


        StartRandomGame(fulfillPercentage,humanX,humanY);
    }
    public World(int x,int y,Scanner input){
        this.worldX=x;
        this.worldY=y;
        this.turn=0;
        area = new GameArea(worldX,worldY,this);
        worldBoard = new Organism[y][x];
        logs = new Vector<>();
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++)
                worldBoard[i][j]=null;


        LoadState(input);
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
                if(organism.getCoordinates().GetX()!=-1)
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
            logs.addElement("SMIERC: '"+a.getSign() + "' zjadł wilcze jagody i umiera.");



    }

    public void sortOrganisms(){
        organisms.sort(new OrganismComparator());
    }

    public void AddOrganism(Coordinates coords,String name){
        if(worldBoard[coords.GetY()][coords.GetX()]!=null)
            showMessageDialog(null, "Te pole jest zajęte");
            else
        {
            switch (name) {
                case "Wolf" ->  new Wolf(coords.GetX(), coords.GetY(), this);
                case "Antelope" -> new Antelope(coords.GetX(), coords.GetY(), this);
                case "Sheep" -> new Sheep(coords.GetX(), coords.GetY(), this);
                case "Turtle" -> new Turtle(coords.GetX(), coords.GetY(), this);
                case "Fox" -> new Fox(coords.GetX(), coords.GetY(), this);
                case "Grass" -> new Grass(coords.GetX(), coords.GetY(), this);
                case "Dandelion" -> new Dandelion(coords.GetX(), coords.GetY(), this);
                case "Guarana" ->  new Guarana(coords.GetX(), coords.GetY(), this);
                case "Berries" ->  new WolfBerries(coords.GetX(), coords.GetY(), this);
                case "Sosnowski'sHogweed" -> new SosnowskiHogweed(coords.GetX(), coords.GetY(), this);
            }

            area.updateArea();
        }


    }

    public void StartRandomGame(double fulfillPercentage,int humanX,int humanY){
        int boardSize = worldY*worldX;
        human =new Human(humanX,humanY,this);
         int maxCreaturesAmount = (int)Math.round(fulfillPercentage*boardSize);
        String[] organismsNames = {"Wolf","Antelope","Sheep","Turtle","Fox","Grass","Dandelion","Guarana","Sosnowski'sHogweed","Berries",};
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

    public void LoadState(Scanner input){
        String data;
        String sign;
        int x,y, power,initiative,lifetime,breedingtimeout,skillcooldown=0,skillturnleft=0;
        boolean skillisactive=false;

        while(input.hasNextLine())
        {
            data = input.nextLine();
            String[] split = data.split(" ");
            sign = split[0];
            x=Integer.parseInt(split[1]);
            y=Integer.parseInt(split[2]);
            power=Integer.parseInt(split[3]);
            initiative=Integer.parseInt(split[4]);
            lifetime=Integer.parseInt(split[5]);
            breedingtimeout=Integer.parseInt(split[6]);
            if(Objects.equals(sign, "H")) {
                if(Integer.parseInt(split[7])==1)
                    skillisactive=true;
                else
                    skillisactive=false;


                skillcooldown = Integer.parseInt(split[8]);
                skillturnleft = Integer.parseInt(split[9]);


            }
            switch (sign) {
                case "W" -> new Wolf(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "A" -> new Antelope(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "S" -> new Sheep(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "T" -> new Turtle(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "F" -> new Fox(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "G" -> new Grass(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "D" -> new Dandelion(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "Gu" -> new Guarana(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "B" -> new WolfBerries(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "Ho" -> new SosnowskiHogweed(x,y,this,power,initiative,lifetime,breedingtimeout,sign);
                case "H" -> this.human = new Human(x,y,this,power,initiative,lifetime,breedingtimeout,sign,skillisactive,skillcooldown,skillturnleft);
            }

            if(this.human==null)
            {
                this.human = new Human(this);

            }

        }
        input.close();
    }
}
