package com.java.virtual.world;

import javax.swing.*;

import com.java.virtual.world.GameHUD;
import com.java.virtual.world.Menu;
import static javax.swing.JOptionPane.showMessageDialog;
public class NewGameMenu extends JFrame {
    private final JLabel worldX = new JLabel("Set world X size");
    private final JLabel worldY = new JLabel("Set world Y size");
    private final JButton backToMainMenu= new JButton("<-Back");
    private final JButton nextButton = new JButton("Start");
    private final JSpinner worldXSpinner = new JSpinner();
    private final JSpinner worldYSpinner = new JSpinner();
    private int x;
    private int y;

    NewGameMenu(){


        nextButton.setBounds(290,420,100,50);
        worldX.setBounds(150,50,150,50);
        worldY.setBounds(150,90,150,50);
        backToMainMenu.setBounds(0,0,120,50);
        worldXSpinner.setBounds(150,80,100,20);
        worldYSpinner.setBounds(150,130,100,20);

        add(backToMainMenu);
        add(worldX);
        add(worldY);
        add(nextButton);
        add(worldXSpinner);
        add(worldYSpinner);
        setButtonActions();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400,500);
        setVisible(false);
    }

    public void setButtonActions(){
        worldXSpinner.addChangeListener(
                e->{this.x = (Integer)worldXSpinner.getValue();}
        );
        worldYSpinner.addChangeListener(
                e->{this.y = (Integer)worldYSpinner.getValue();}
        );
        backToMainMenu.addActionListener(
                e->{setVisible(false);
                    new Menu().setVisible(true);}
        );
        nextButton.addActionListener(
                e->{if(this.x<=0||this.y<=0)
                    showMessageDialog(null, "Zly rozmiar swiata");
                else
                {
                    setVisible(false);
                    new GameHUD(x,y);
                }
                    }
        );
    }



}
