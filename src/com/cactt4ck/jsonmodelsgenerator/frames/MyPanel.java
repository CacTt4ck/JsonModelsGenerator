package com.cactt4ck.jsonmodelsgenerator.frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {

    private JButton generateBtn;
    private JLabel title;
    private ImageIcon block, item, tool;
    private JComboBox<ImageIcon> choiceBox;

    public MyPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){
        this.title();
        this.choiceBox();
        this.generateButton();
    }

    private void title(){
        title = new JLabel("Json Generator");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Monaco",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        this.add(title, BorderLayout.NORTH);
    }

    private void generateButton(){
        generateBtn = new JButton("Generate File");
        this.add(generateBtn, BorderLayout.SOUTH);
    }

    private void choiceBox(){
        block = new ImageIcon(new ImageIcon("res/assets/pictures/block.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        item = new ImageIcon(new ImageIcon("res/assets/pictures/item.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        tool = new ImageIcon(new ImageIcon("res/assets/pictures/tool.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));

        ImageIcon[] imageList = {block, item, tool};
        choiceBox = new JComboBox<ImageIcon>(imageList);
        this.add(choiceBox, BorderLayout.CENTER);
    }

}
