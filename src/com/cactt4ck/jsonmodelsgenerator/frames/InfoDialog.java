package com.cactt4ck.jsonmodelsgenerator.frames;

import javax.swing.*;
import java.awt.*;

import static com.cactt4ck.jsonmodelsgenerator.frames.MyFrame.SCREEN_SIZE;

public class InfoDialog extends JDialog {

    private JLabel title;

    public InfoDialog(boolean isModal) {
        this.setLayout(new BorderLayout());
        this.setModal(isModal);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation((int) SCREEN_SIZE.getWidth()/3, (int) SCREEN_SIZE.getHeight()/3);
        this.setSize(400,400);
        this.init();
        this.setVisible(true);
    }

    private void init(){
        this.title();
    }

    private void title() {
        title = new JLabel("Informations");
        title.setFont(new Font("Monaco",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);
    }

}
