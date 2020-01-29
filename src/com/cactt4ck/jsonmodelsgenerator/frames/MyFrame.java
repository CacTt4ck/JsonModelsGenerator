package com.cactt4ck.jsonmodelsgenerator.frames;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public static final long VERSION = 25092019001L;
    /* version composition : xx-day, xx-month, xxxx-year, xxx-update in same journey */
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public MyFrame(String title, boolean undecorated){
        super(title);
        this.setUndecorated(undecorated);
        this.init();
    }

    private void init() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation((int) SCREEN_SIZE.getWidth()/3, (int) SCREEN_SIZE.getHeight()/3);
        this.setResizable(false);
        this.setFocusable(true);
        this.setContentPane(new MyPanel());
        this.pack();
        this.setVisible(true);
    }
}
