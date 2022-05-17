package com.java.virtual.world.Inteface;

import javax.swing.*;

public class Menu extends JFrame {
    private JButton newGame;
    private JButton loadGame;
    private JButton exit;

    public Menu() {
        JButton newGame = new JButton("New game");
        newGame.setBounds(150, 100, 100, 50);
        JButton loadGame = new JButton("Load game");
        loadGame.setBounds(150, 200, 100, 50);
        JButton exit = new JButton("Exit");
        exit.setBounds(150, 300, 100, 50);
        exit.addActionListener(e -> {
            System.exit(0);
        });
        newGame.addActionListener(e -> {
            setVisible(false);
            new NewGameMenu().setVisible(true);
        });
        add(newGame);
        add(loadGame);
        add(exit);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 500);
        setVisible(true);

    }
}
