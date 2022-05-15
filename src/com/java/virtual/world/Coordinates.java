package com.java.virtual.world;

public class Coordinates {
    private int x;
    private int y;
   public Coordinates(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x)
    {
        this.x =x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int GetX()
    {
        return x;
    }
    public int GetY(){
       return y;
    }
    public void setCoordinates(Coordinates newCoords)
    {
        this.x = newCoords.GetX();
        this.y = newCoords.GetY();
    }


}
