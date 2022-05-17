package com.java.virtual.world.Organisms.Animals;

import com.java.virtual.world.Organisms.Coordinates;
import com.java.virtual.world.Organisms.Organism;
import com.java.virtual.world.WorldManager.World;

import java.awt.*;
import java.util.Random;


public class Human extends Animal{

    private final int KEY_NULL = 0;
    private final int KEY_UP = 1;
    private final int KEY_LEFT =2;
    private final int KEY_RIGHT = 3;
    private final int KEY_DOWN = 4;
    private final int KEY_F = 5;
    private final int SKILL_TURN_AMNT = 5;
    private int keyPressed=KEY_NULL;
    private boolean skillIsActive=false;
    private int skillTurnLeft=0;
    private int skillCooldown=0;


    public Human(int x,int y, World world) {
        super(5, 4, x, y, world, "H", "Human", world.getColors().getColor("Human"));


    }
    public Human(int x,int y, World world, int power,int initiative, int lifetime, int breedingtimeout,String sign,boolean skillIsActive,int skillCooldown, int skillTurnLeft) {
        super(power, initiative, x, y, world, "H", "Human", world.getColors().getColor("Human"),lifetime,breedingtimeout);

        this.skillIsActive=skillIsActive;
        this.skillCooldown=skillCooldown;
        this.skillTurnLeft = skillTurnLeft;


    }

    public Human(World world){
        super(4,4, -1, -1,world, "H", "Human", world.getColors().getColor("Human"),1);
    }


    @Override
    public void Action(){

        world.getArea().updateArea();
        Random seed = new Random();
        boolean choice = seed.nextInt(100)<50;
        Coordinates newCoordinates;
        if((this.skillIsActive &&this.skillTurnLeft>2)||(this.skillIsActive && choice)) {
            newCoordinates = UpdateCoordinates(2);
        }
        else {
            newCoordinates = UpdateCoordinates(1);
        }
        Organism[][] worldboard = world.getWorldBoard();
        if (worldboard[newCoordinates.GetY()][newCoordinates.GetX()] == null)
            this.setCoordinates(newCoordinates);
        else {
            if (worldboard[newCoordinates.GetY()][newCoordinates.GetX()] != null) {
                Organism other = worldboard[newCoordinates.GetY()][newCoordinates.GetX()];
                if (other.Collision(this)) {
                    this.setCoordinates(newCoordinates);
                } else {
                    return;
                }
            } else {
                this.setCoordinates(newCoordinates);
            }
            world.setWorldBoard(worldboard);

        }
        this.setLifetime(this.getLifetime()+1);
        UpdateSkillStatus();
    }


    public Coordinates UpdateCoordinates(int skill){
        Coordinates newCoordinates;
        keyPressed = world.getHumanMove();
        if(keyPressed == KEY_UP)
            newCoordinates = new Coordinates(this.coordinates.GetX(),this.coordinates.GetY()-skill);
        else if(keyPressed == KEY_DOWN)
            newCoordinates = new Coordinates(this.coordinates.GetX(),this.coordinates.GetY()+skill);
        else if(keyPressed == KEY_LEFT)
            newCoordinates = new Coordinates(this.coordinates.GetX()-skill,this.coordinates.GetY());
        else if(keyPressed == KEY_RIGHT)
            newCoordinates = new Coordinates(this.coordinates.GetX()+skill,this.coordinates.GetY());
        else
            newCoordinates = this.coordinates;

        int x = newCoordinates.GetX();
        int y = newCoordinates.GetY();

        if(x<0)
            x=0;
        if(y<0)
            y=0;
        if(x>=world.getWorldX())
            x=world.getWorldX()-1;
        if(y>=world.getWorldY())
            y=world.getWorldY()-1;

        return new Coordinates(x,y);


    }

    public void CheckSkill(){
        keyPressed = world.getHumanMove();
        if(keyPressed == KEY_F)
        {
            if (getSkillIsActive())
            {
                if(this.getSkillTurnLeft()>0)
                    return;
            }
            if (!this.getSkillIsActive())
            {
                if (this.getSkillCooldown() > 0)
                    return;
		else
                {
                    this.setSkillIsActive();
                    this.setSkillCooldown(SKILL_TURN_AMNT);
                    this.setSkillTurnLeft(SKILL_TURN_AMNT);
                }
            }
        }
    }

    public boolean getSkillIsActive(){
        return skillIsActive;
    }
    public int getSkillCooldown(){
        return skillCooldown;
    }
    public int getSkillTurnLeft(){
        return skillTurnLeft;
    }

    public void setSkillIsActive(){
        skillIsActive = !skillIsActive;
    }

    public void setSkillTurnLeft(int amnt){
        skillTurnLeft = amnt;
    }
    public void setSkillCooldown(int amnt){
        skillCooldown=amnt;
    }

    public void UpdateSkillStatus() {
        if (this.skillIsActive)
        {
            this.skillTurnLeft--;
            if (this.skillTurnLeft == 0)
            {
                this.skillIsActive = false;
                this.skillCooldown = SKILL_TURN_AMNT;
            }
        }
	else if (!this.skillIsActive)
        if (this.skillCooldown > 0)
        this.skillCooldown--;
    }
}
