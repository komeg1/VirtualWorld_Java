package com.java.virtual.world.Inteface;

import javax.swing.*;
import java.awt.*;

public class AuthorPanel extends JPanel {
        private JLabel author = new JLabel("Tomasz Krezymon, ETI, 189642 - May 2022");
        public AuthorPanel() {
            author.setFont(new Font("Arial",Font.BOLD,15));
            author.setForeground(Color.white);
            setBackground(Color.darkGray);
            add(author);
            setLayout(new FlowLayout(FlowLayout.CENTER));
        }
}
