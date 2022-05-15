package com.java.virtual.world;

import com.java.virtual.world.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameHUD extends JFrame {
    private GameArea gameArea;
    private World world;

    public GameHUD(int x,int y)
    {
        this.world = new World(x,y);
        this.gameArea = new GameArea(x,y,world);
        setLayout(new GridBagLayout());
        JPanel grid = new JPanel(new GridLayout(x,y,1,1));
        for(int i=0;i<y;i++)
            for(int j=0;j<x;j++) {
                grid.add(gameArea.getArea()[i][j]);
                System.out.println(gameArea.getArea()[i][j].GetColor());
            }
        add(grid);


        setSize(1920,1080);
        JButton clear = new JButton("Clear");
        JButton nextTurn = new JButton("Next Turn");
        nextTurn.setBounds(500,1000,100,100);
        nextTurn.addActionListener(e->{
            world.NextTurn();
            gameArea.updateArea();

        });
        clear.setBounds(500,900,100,100);
        clear.addActionListener(e->{this.gameArea.updateArea();});
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(clear);
        add(nextTurn);
        setVisible(true);
    }



}
