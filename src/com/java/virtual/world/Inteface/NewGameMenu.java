package com.java.virtual.world.Inteface;

import javax.swing.*;

import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class NewGameMenu extends JFrame {
    private final JButton backToMainMenu= new JButton("<-Back");
    private final JButton nextButton = new JButton("Start");
    private final JSlider worldXSlider = new JSlider(0,200,20);
    private final JSlider worldYSlider = new JSlider(0,200,20);
    private final JSlider fulfillSlider = new JSlider(JSlider.HORIZONTAL,0,50,10);
    private final JLabel fulfillDesc = new JLabel("Percentage fulfill of organisms on map: 25%");
    private final JLabel worldX = new JLabel("Set world size: 20x20");
    private final AuthorPanel authorPanel = new AuthorPanel();
    private int x;
    private int y;
    private double fulfill=0.25;

    NewGameMenu(){


        nextButton.setSize(100,50);

        worldX.setSize(150,50);
        setTitle("Virtual World Simulator");


        JPanel options = new JPanel();
        options.setLayout(new GridLayout(8,1,1,1));

        worldXSlider.setValue(20);
        x=20;
        y=20;
        setLayout(new BorderLayout(8,6));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backToMainMenu.setSize(100,50);
        nextButton.setSize(100,50);
        topPanel.add(backToMainMenu);
        topPanel.add(nextButton);
        options.add(worldX);
        options.add(worldXSlider);
        options.add(fulfillDesc);
        options.add(fulfillSlider);

        add(topPanel,BorderLayout.NORTH);
        add(options,BorderLayout.CENTER);


        add(authorPanel,BorderLayout.SOUTH);


        setButtonActions();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(400,500);
        setLocation(700,250);
        setVisible(false);
    }

    public void setButtonActions(){
        worldXSlider.addChangeListener(
                e->{this.x = worldXSlider.getValue();
                    this.y = worldXSlider.getValue();
                    worldX.setText("Set world size: "+x+"x"+x);}
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
                    new GameHUD(x,y,fulfill,0,0);
                }
                    }
        );

        fulfillSlider.addChangeListener(
                e->{
                    this.fulfill = (0.01*fulfillSlider.getValue());
                    fulfillDesc.setText("Percentage fulfill of organisms on map: "+ this.fulfillSlider.getValue()+"%");
                }
        );


    }



}
