package com.java.virtual.world.Inteface;

import com.java.virtual.world.SavesManagement.Saving;
import com.java.virtual.world.WorldManager.GameArea;
import com.java.virtual.world.WorldManager.World;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class GameHUD extends JFrame implements KeyListener{
    private GameArea gameArea;
    private World world;
    private final int KEY_NULL = 0;
    private final int KEY_UP = 1;
    private final int KEY_LEFT =2;
    private final int KEY_RIGHT = 3;
    private final int KEY_DOWN = 4;
    private final int KEY_F = 5;
    private JPanel playerInfo;
    private JLabel playerHead;
    private JLabel playerPosition;
    private JLabel playerPower;
    private JLabel playerSkillTurnsCounter;
    private JLabel isPlayerSkillActive;
    private JLabel playerSkillCooldown;
    private JPanel gameDescription;
    private JPanel grid;
    private int x;
    private int y;
    private Saving save;
    private final JTextArea logs;
    public GameHUD(int x,int y)
    {
        this.x =x;
        this.y=y;
        this.world = new World(x,y);
        this.save = new Saving(world);
        gameArea = this.world.getArea();
        setSize(1300,1000);
        setLocation(200,50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,6));
        setBackground(Color.darkGray);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.darkGray));

        JPanel topPanel = new JPanel();

        topPanel.setBackground(Color.darkGray);
        topPanel.setLayout(new FlowLayout(5));

        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new LineBorder(Color.black,3));
        middlePanel.setLayout(new FlowLayout(1,4,4));
        middlePanel.setBackground(Color.darkGray);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,1,0,0));
        gridPanel.setBackground(Color.darkGray);


        SetPlayerInformation();
        CreateGrid();
        CreateGameDescription();
        JButton saveButton = new JButton("Save");
        JButton nextTurn = new JButton("Next Turn");

        nextTurn.setBounds(500,1000,100,100);
        nextTurn.addActionListener(e->{
            world.setHumanMove(KEY_NULL);
            world.NextTurn();
            gameArea.updateArea();
            UpdateLogs();
            UpdatePlayerInformation();
            requestFocus();

        });
        saveButton.setBounds(500,900,100,100);
        saveButton.addActionListener(e->{save.Save();});

        logs = new JTextArea(20,20);
        JScrollPane logsScroll = new JScrollPane(logs);




        addKeyListener(this);
        setFocusable(true);
        topPanel.add(nextTurn);
        topPanel.add(saveButton);
        gridPanel.add(logsScroll);
        gridPanel.add(playerInfo);
        gridPanel.add(gameDescription);
        middlePanel.add(gridPanel);

        add(topPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.WEST);
        add(grid,BorderLayout.CENTER);
        getContentPane().setBackground(Color.white);
        gameArea.updateArea();
        setResizable(false);
        setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> world.setHumanMove(KEY_UP);
            case KeyEvent.VK_DOWN -> world.setHumanMove(KEY_DOWN);
            case KeyEvent.VK_LEFT -> world.setHumanMove(KEY_LEFT);
            case KeyEvent.VK_RIGHT -> world.setHumanMove(KEY_RIGHT);
            case KeyEvent.VK_F -> world.setHumanMove(KEY_F);
        }

        if(keyCode==KeyEvent.VK_F)
        {
            world.getHuman().CheckSkill();
            UpdatePlayerInformation();
        }
        else
        {
            world.NextTurn();
            gameArea.updateArea();
            UpdateLogs();
            UpdatePlayerInformation();
        }
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void UpdateLogs(){
        Vector<String> logsV = world.getLogs();
        if(logsV.size()>0&&logsV.size()<=19){
            logs.setText(logsV.get(0)+"\n");
            for(int i=1;i<logsV.size();i++)
                logs.append(logsV.get(i)+"\n");
        }
        else
        {
            logs.setText(logsV.get(logsV.size()-19)+"\n");
            for(int i=logsV.size()-18;i<logsV.size();i++)
                logs.append(logsV.get(i)+"\n");
        }
    }

    public void SetPlayerInformation(){
        this.playerHead = new JLabel("GRACZ");
        this.playerPosition=new JLabel( "Pozycja: X: "+world.getHuman().getCoordinates().GetX()+" Y: "+world.getHuman().getCoordinates().GetY());
        this.playerPower=new JLabel("Siła: "+world.getHuman().getPower());
        this.isPlayerSkillActive = new JLabel("UMIEJETNOSC WLACZONA: " + isHumanSkillActive());
        this.playerSkillCooldown = new JLabel("UMIEJETNOSC COOLDOWN: "+world.getHuman().getSkillCooldown());
        this.playerSkillTurnsCounter = new JLabel("UMIEJETNOSC AKTYWNA PRZEZ: "+ world.getHuman().getSkillTurnLeft() + " TURY");
        playerInfo = new JPanel(new GridLayout(10,1,0,0));
        playerInfo.add(playerHead);
        playerInfo.add(playerPosition);
        playerInfo.add(playerPower);
        playerInfo.add(isPlayerSkillActive);
        playerInfo.add(playerSkillTurnsCounter);
        playerInfo.add(playerSkillCooldown);
    }
    public void UpdatePlayerInformation(){
        if(world.getHuman().getKilled()==0) {
            playerPosition.setText("Pozycja: X: " + world.getHuman().getCoordinates().GetX() + " Y: " + world.getHuman().getCoordinates().GetY());
            playerPower.setText("Siła: " + world.getHuman().getPower());
            isPlayerSkillActive.setText("UMIEJETNOSC WLACZONA: " + isHumanSkillActive());
            playerSkillCooldown.setText("UMIEJETNOSC COOLDOWN: "+world.getHuman().getSkillCooldown());
            playerSkillTurnsCounter.setText("UMIEJETNOSC AKTYWNA PRZEZ: "+ world.getHuman().getSkillTurnLeft() + " TURY");
        }
        else
        {

            playerHead.setText("GRACZ NIE ŻYJE");
            playerPosition.setText("");
            playerPower.setText("");
            isPlayerSkillActive.setText("");
            playerSkillCooldown.setText("");
            playerSkillTurnsCounter.setText("");


        }
    }
    public void CreateGrid() {
        grid = new JPanel(new GridLayout(x, y));
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {
                grid.add(gameArea.getArea()[i][j]);

            }
        grid.setOpaque(true);
        grid.setBorder(new LineBorder(Color.black, 3));
}

    public void CreateGameDescription() {
        gameDescription = new JPanel(new GridLayout(11, 1, 0, 0));
        String[] organismsNames = {"Human","Wolf","Antelope","Sheep","Turtle","Fox","Grass","Dandelion","Guarana","Berries","Sosnowski's Hogweed"};
        for(int i=0;i<organismsNames.length;i+=2){
            JPanel organismDesc = new JPanel(new FlowLayout(FlowLayout.LEFT,1,1));
            JPanel color1 = new JPanel(new FlowLayout());
            color1.setBackground(world.getColors().getColor(organismsNames[i]));
            JLabel organismName1 = new JLabel(organismsNames[i]+"           ");
            organismDesc.add(color1);
            organismDesc.add(organismName1);
            JPanel color2 = new JPanel();
            if(i<organismsNames.length-1)
            {
                color2.setBackground(world.getColors().getColor(organismsNames[i + 1]));
                JLabel organismName2 = new JLabel(organismsNames[i + 1]);
                organismDesc.add(color2);
                organismDesc.add(organismName2);
            }

            gameDescription.add(organismDesc);

        }
    }



    public String isHumanSkillActive(){
        if(world.getHuman().getSkillIsActive())
            return "TAK";
        return "NIE";
    }



}
