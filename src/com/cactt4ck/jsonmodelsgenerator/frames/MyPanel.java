package com.cactt4ck.jsonmodelsgenerator.frames;

import com.cactt4ck.jsonmodelsgenerator.types.Block;
import com.cactt4ck.jsonmodelsgenerator.types.Items;
import com.cactt4ck.jsonmodelsgenerator.types.Tool;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {

    private JButton generateBtn, buttonPathChooser;
    private JTextField name;
    private File modPath;
    private JLabel title, pathFound;
    private ImageIcon block, item, tool, stair;
    private JComboBox<ImageIcon> choiceBox;
    private JPanel boxPanel, buttonPanel;
    private JFileChooser filechooser;
    private boolean pathSelected;

    public MyPanel(){
        super();
        this.pathSelected = false;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        this.title();
        this.pathChooser();
        this.pathFound();
        this.choiceBox();
        this.generateButton();
        this.nameField();
        this.add(boxPanel, BorderLayout.CENTER);
    }

    private void nameField(){
        name = new JTextField();
        name.setToolTipText("Enter name here");
        name.setFont(new Font("Consolas", Font.PLAIN, 15));
        name.addActionListener(e -> this.actionListener());
        boxPanel.add(name);
    }

    private void pathFound(){
        pathFound = new JLabel();
        pathFound.setFont(new Font("Monaco",Font.PLAIN,12));
        pathFound.setForeground(Color.green);
        boxPanel.add(pathFound);
    }

    private void pathChooser(){
        buttonPathChooser = new JButton();
        buttonPathChooser.setText("Choose path");
        buttonPathChooser.addActionListener(e -> {
            modPath = paths();
            this.pathSelected = false;
            File[] directories = modPath.listFiles(File::isDirectory);
            for (File file :
                    directories) {
                System.out.println(file.getAbsolutePath());
            }
            if(this.pathSelected){
                buttonPathChooser.setVisible(false);
                pathFound.setText("Path found !");
            } else
                this.error();
        });
        boxPanel.add(buttonPathChooser);
    }

    private void error(){
        JOptionPane.showMessageDialog(this, "Error path incorrect", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private File paths(){
        filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.showOpenDialog(this);
        return filechooser.getSelectedFile();
    }

    private void actionListener(){
        if(pathSelected){
            ImageIcon choice = (ImageIcon) choiceBox.getSelectedItem();
            if(choice == block){
                Block block = new Block(name.getText());
                block.generateFiles();
            }else if(choice == item){
                Items item = new Items(name.getText());
                item.generateFiles();
            }else if(choice == tool){
                Tool tool = new Tool(name.getText());
                tool.generateFiles();
            }else if(choice == stair){
                System.out.println("not ready yet !");
            }
        } else
            JOptionPane.showMessageDialog(this, "You have to select a correct path to your mod project!", "Incorrect Path",JOptionPane.ERROR_MESSAGE);
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
        choiceBox = new JComboBox<>(imageList);
        boxPanel.add(choiceBox);
        boxPanel.setBorder(BorderFactory.createEmptyBorder(75,150,75,150));
    }

}
