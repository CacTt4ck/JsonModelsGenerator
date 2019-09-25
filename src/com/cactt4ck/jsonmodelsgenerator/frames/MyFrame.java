package com.cactt4ck.jsonmodelsgenerator.frames;

import javax.swing.*;

public class MyFrame extends JFrame {

    public static final long VERSION = 25092019001L;
    /* version composition : xx-day, xx-month, xxxx-year, xxx-update in same journey */

    public MyFrame(boolean undecorated){
        super();
        this.setUndecorated(undecorated);
        this.init();
    }

    private void init() {
        //this.setSize(640,480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setContentPane(new MyPanel());
        this.pack();
        this.setVisible(true);
    }
}
