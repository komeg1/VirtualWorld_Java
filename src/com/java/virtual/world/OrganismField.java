package com.java.virtual.world;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class OrganismField extends JPanel {
    private Color color;
    private JLabel label;
    private int x;
    private int y;
    private World world;
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 40);
    }
    public OrganismField(Color color, String sign,int x,int y,World world){
        this.world=world;
        this.x =x;
        this.y = y;
        RightClickMenu rightCLickMenu = new  RightClickMenu(x,y,world);
        addMouseListener(rightCLickMenu);
        this.color = color;
        this.label = new JLabel(sign);
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        setBackground(color);
        add(label);
    }

    public void SetField(Color color, JLabel label)
    {
        this.color = color;
        this.label.setText(label.getText());
        setBackground(color);
    }
    public void SetLabel(String label)
    {
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
        this.label.setText("");
    }
}
