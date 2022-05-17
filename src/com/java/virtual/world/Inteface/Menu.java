package com.java.virtual.world.Inteface;

import com.java.virtual.world.SavesManagement.Loading;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JButton newGame;
    private JButton loadGame;
    private JButton exit;
    private Loading load;
    private final AuthorPanel author = new AuthorPanel();
    public Menu() {
        setTitle("Virtual World Simulator");
        setLayout(new BorderLayout(8,6));
        JLabel center = new JLabel();
        JButton newGame = new JButton("New game");
        newGame.setSize(100, 50);
        JButton loadGame = new JButton("Load game");
        loadGame.setSize(100, 100);
        JButton exit = new JButton("Exit");
        exit.setSize(100, 50);
        exit.addActionListener(e -> {
            System.exit(0);
        });
        newGame.addActionListener(e -> {
            setVisible(false);
            new NewGameMenu().setVisible(true);
        });
        loadGame.addActionListener(e->{
            load = new Loading();
        });
        JPanel buttons = new JPanel();
        JPanel startPanel= new JPanel();
        JLabel startText = new JLabel("VIRTUAL WORLD SIMULATOR");
        startText.setFont(new Font("Arial",Font.BOLD,25));
        startPanel.add(startText);
        add(startPanel,BorderLayout.NORTH);
        buttons.setLayout(new GridLayout(5,1,10,10));
        buttons.add(center);
        buttons.add(newGame);
        buttons.add(loadGame);
        buttons.add(exit);
        add(buttons,BorderLayout.CENTER);
        add(author,BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(400, 500);
        setLocation(700,250);
        setVisible(true);

    }
}
