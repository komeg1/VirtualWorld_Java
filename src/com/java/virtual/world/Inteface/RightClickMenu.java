package com.java.virtual.world.Inteface;

import com.java.virtual.world.WorldManager.World;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickMenu extends MouseAdapter {
    private int x;
    private int y;

    private World world;

    public RightClickMenu(int x, int y, World world)
    {
        this.world = world;
        this.x=x;
        this.y=y;
    }

    @Override
    public void mouseClicked(MouseEvent event)
    {
        if(event.getButton()==MouseEvent.BUTTON3)
        {
            Toolbar menu=new Toolbar(x,y,world);
            menu.show(event.getComponent(),event.getX(),event.getY());
        }
    }

}

