package com.java.virtual.world.Inteface;

import com.java.virtual.world.Organisms.Animals.*;
import com.java.virtual.world.Organisms.Plants.*;

import java.awt.*;

public class Colors {
    public Colors(){}

    public Color getColor(String name){
        Color color= new Color(0,0,0);
        switch (name) {
            case "Wolf" -> color =new Color(86, 31, 5);
            case "Antelope" -> color =new Color(131, 93, 16);
            case "Sheep" -> color =Color.yellow;
            case "Turtle" -> color =Color.cyan;
            case "Fox" -> color = Color.orange;
            case "Grass" -> color =Color.green;
            case "Dandelion" -> color = new Color(231, 220, 89);
            case "Guarana" -> color = new Color(0, 20, 96);
            case "Berries" -> color =new Color(253, 108, 245);
            case "Sosnowski'sHogweed" -> color =new Color(255, 0, 0);
            case "Human" -> color =new Color(56, 138, 255);
        }
        return color;
    }
}
