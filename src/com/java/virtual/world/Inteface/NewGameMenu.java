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
    private final JSpinner humanXSpinner = new JSpinner();
    private final JSpinner humanYSpinner = new JSpinner();
    private int x;
    private int y;
    private int humanX;
    private int humanY;
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

        JPanel humanCoordinates = new JPanel();
        humanCoordinates.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel humanInfoX = new JLabel("Human position X: ");
        JLabel humanInfoY = new JLabel(" Y: ");
        Dimension size = new Dimension(60,30);
        humanXSpinner.setPreferredSize(size);
        humanXSpinner.setMinimumSize(size);
        humanYSpinner.setPreferredSize(size);
        humanYSpinner.setMinimumSize(size);
        humanCoordinates.add(humanInfoX);
        humanCoordinates.add(humanXSpinner);
        humanCoordinates.add(humanInfoY);
       humanCoordinates.add(humanYSpinner);

        options.add(humanCoordinates);
        add(topPanel,BorderLayout.NORTH);
        add(options,BorderLayout.CENTER);

        AuthorPanel authorPanel = new AuthorPanel();
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
                    if(humanX>=x||humanY>=y)
                    {
                        showMessageDialog(null,("Invalid player positon. Player maximum position is X: "+(x-1)+" Y: "+(y-1)));
                    }
                else
                {
                    setVisible(false);
                    new GameHUD(x,y,fulfill,humanX,humanY);
                }
                    }
        );

        fulfillSlider.addChangeListener(
                e->{
                    this.fulfill = (0.01*fulfillSlider.getValue());
                    fulfillDesc.setText("Percentage fulfill of organisms on map: "+ this.fulfillSlider.getValue()+"%");
                }
        );

        humanXSpinner.addChangeListener(
                e->{
                    humanX = (int)humanXSpinner.getValue();

                }
        );
        humanYSpinner.addChangeListener(
                e->{
                    humanY = (int)humanYSpinner.getValue();
                }
        );


    }



}
