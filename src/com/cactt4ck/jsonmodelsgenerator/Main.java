package com.cactt4ck.jsonmodelsgenerator;

import com.cactt4ck.jsonmodelsgenerator.frames.MyFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(()-> new MyFrame("Json Model Generator", false));
    }

}
