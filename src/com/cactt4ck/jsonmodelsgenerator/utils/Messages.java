package com.cactt4ck.jsonmodelsgenerator.utils;

import javax.swing.*;
import java.awt.*;

public class Messages {

    public static final int PATH_INVALID = 1;
    public static final int PATH_VALID = 2;
    public static final int PATH_NOT_SELECTED = 3;

    public static void showMessage(int type, Component parent){
        switch (type){
            case PATH_VALID:
                JOptionPane.showMessageDialog(parent, "Path successfully found", "Information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case PATH_INVALID:
                JOptionPane.showMessageDialog(parent, "Error path incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case PATH_NOT_SELECTED:
                JOptionPane.showMessageDialog(parent, "You have to select a correct path to your mod project!", "Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                break;
        }
    }

}
