package com.cactt4ck.jsonmodelsgenerator.frames;

import com.cactt4ck.jsonmodelsgenerator.types.Block;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private JButton generateBtn;
    private JTextField name;
    private JLabel title;
    private ImageIcon block, item, tool, stair;
    private JComboBox<ImageIcon> choiceBox;
    private JPanel boxPanel, buttonPanel;

    public MyPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        this.title();
        this.choiceBox();
        this.generateButton();
        this.nameField();
        this.add(boxPanel, BorderLayout.CENTER);
    }

    private void nameField(){
        name = new JTextField();
        name.setToolTipText("enter name here");
        name.addActionListener(e -> this.actionListener());
        boxPanel.add(name);
    }

    private void actionListener(){
        ImageIcon choice = (ImageIcon) choiceBox.getSelectedItem();
        if(choice == block){
            Block block = new Block(name.getText());
            block.generateFiles();
        }else if(choice == item){
            System.out.println("not ready yet !");
        }else if(choice == tool){
            System.out.println("not ready yet !");
        }else if(choice == stair){
            System.out.println("not ready yet !");
        }
    }

    private void title(){
        title = new JLabel("Json Generator");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Monaco",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        this.add(title, BorderLayout.NORTH);
    }

    private void generateButton(){
        generateBtn = new JButton("Generate File");
        generateBtn.addActionListener(e -> this.actionListener());
        buttonPanel.add(generateBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void choiceBox(){
        block = new ImageIcon(new ImageIcon("res/assets/pictures/block.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        item = new ImageIcon(new ImageIcon("res/assets/pictures/item.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        tool = new ImageIcon(new ImageIcon("res/assets/pictures/tool.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        stair = new ImageIcon(new ImageIcon("res/assets/pictures/stairs.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));

        ImageIcon[] imageList = {block, item, tool, stair};
        choiceBox = new JComboBox<ImageIcon>(imageList);
        boxPanel.add(choiceBox);
        boxPanel.setBorder(BorderFactory.createEmptyBorder(75,150,75,150));
    }

}
