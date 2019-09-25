package com.cactt4ck.jsonmodelsgenerator;

import com.cactt4ck.jsonmodelsgenerator.frames.MyFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new MyFrame(false));
    }

}
