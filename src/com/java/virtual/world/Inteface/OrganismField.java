package com.java.virtual.world.Inteface;

import com.java.virtual.world.WorldManager.World;

import javax.swing.*;
import java.awt.*;

public class OrganismField extends JPanel {
    private Color color;
    private JLabel label;
    private int x;
    private int y;
    private World world;

    public OrganismField(Color color, String sign,int x,int y,World world){
        this.world=world;
        this.x =x;
        this.y = y;
        RightClickMenu rightCLickMenu = new  RightClickMenu(x,y,world);
        addMouseListener(rightCLickMenu);
        this.color = color;
        if(world.getWorldX()<=20||world.getWorldY()<=20) {
            this.label = new JLabel(sign);
            label.setFont(new Font("Arial", Font.PLAIN, 25));
            add(label);
        }

        setBackground(color);


    }

    public void SetField(Color color, JLabel label)
    {
        this.color = color;
        if(world.getWorldX()<=20||world.getWorldY()<=20)
            this.label.setText(label.getText());
        setBackground(color);
    }
    public void SetLabel(String label)
    {
        if(world.getWorldX()<=20||world.getWorldY()<=20)
            this.label.setText(label);
    }
    public void SetColor(Color color)
    {
        this.color = color;
        setBackground(color);
    }
    public Color GetColor(){
        return color;
    }
    public JLabel GetSign(){
        return label;
    }


    public void ClearField(){
        this.color = Color.GREEN;
        if(world.getWorldX()<=20||world.getWorldY()<=20)
            this.label.setText("");
    }
}
