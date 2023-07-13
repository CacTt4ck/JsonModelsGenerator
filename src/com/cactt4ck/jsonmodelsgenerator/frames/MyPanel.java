package com.cactt4ck.jsonmodelsgenerator.frames;

import com.cactt4ck.jsonmodelsgenerator.utils.LabelledImage;
import com.cactt4ck.jsonmodelsgenerator.utils.Messages;
import com.cactt4ck.jsonmodelsgenerator.types.Block;
import com.cactt4ck.jsonmodelsgenerator.types.Items;
import com.cactt4ck.jsonmodelsgenerator.types.Tool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Image.SCALE_SMOOTH;

public class MyPanel extends JPanel {

    private JButton generateBtn, buttonPathChooser, invisiblebutton, infoButton;
    private JTextField name;
    private File modPath;
    private JLabel title, pathFound;
    private Image block, item, tool, stair;
    private JComboBox<LabelledImage> choiceBox;
    private JPanel boxPanel, northPanel, southPanel;
    private JFileChooser filechooser;
    private boolean pathSelected;
    private ImageIcon foldericon, processicon, infoicon;

    public MyPanel() {
        super();
        this.pathSelected = false;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        this.add(boxPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.title();
        this.invisiblebutton();
        this.choiceBox();
        this.generateButton();
        this.nameField();
        this.pathChooser();
        this.infoButton();
    }

    private void invisiblebutton() {
        invisiblebutton = new JButton();
        invisiblebutton.setEnabled(false);
        invisiblebutton.setOpaque(false);
        invisiblebutton.setBorderPainted(false);
        invisiblebutton.setContentAreaFilled(false);
        invisiblebutton.setFocusPainted(false);
        southPanel.add(invisiblebutton, BorderLayout.CENTER);
    }

    private void nameField() {
        name = new JTextField();
        name.setToolTipText("Enter name here");
        name.setFont(new Font("Consolas", Font.PLAIN, 15));
        name.addActionListener(e -> this.actionListener());
        boxPanel.add(name);
    }

    private void pathChooser() {
        try {
            foldericon = new ImageIcon(ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/folder.png")).getScaledInstance(48,48, SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonPathChooser = new JButton(foldericon);
        buttonPathChooser.setOpaque(false);
        buttonPathChooser.setBorderPainted(false);
        buttonPathChooser.setContentAreaFilled(false);
        buttonPathChooser.setFocusPainted(false);
        buttonPathChooser.addActionListener(e -> {
            modPath = paths();
            File[] directories = modPath.listFiles(File::isDirectory);
            if (isPathCorrect(directories)) {
                buttonPathChooser.setVisible(false);
                this.pathSelected = true;
                Messages.showMessage(Messages.PATH_VALID, this);
            } else
                Messages.showMessage(Messages.PATH_INVALID, this);
        });

        southPanel.add(buttonPathChooser, BorderLayout.EAST);
    }

    private boolean isPathCorrect(File[] files) {
        ArrayList<String> directories = new ArrayList<String>();
        for (File file : files)
            directories.add(file.getAbsolutePath());
        for (String directory :
                directories) {
            if(directory.contains(".gradle"))
                return true;
        }
        return false;
    }

    private File paths() {
        filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.showOpenDialog(this);
        return filechooser.getSelectedFile();
    }

    private void actionListener() {
        if(pathSelected){
            Image choice = ((LabelledImage) choiceBox.getSelectedItem()).image();
            if(choice.equals(block)){
                Block block = new Block(name.getText());
                block.generateFiles();
            }else if(choice.equals(item)){
                Items item = new Items(name.getText());
                item.generateFiles();
            }else if(choice.equals(tool)){
                Tool tool = new Tool(name.getText());
                tool.generateFiles();
            }else if(choice.equals(stair)){
                System.out.println("not ready yet !");
            }
        } else
            Messages.showMessage(Messages.PATH_NOT_SELECTED, this);
    }

    private void infoButton(){
        try {
            infoicon = new ImageIcon(ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/question.png")).getScaledInstance(32,32, SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        infoButton = new JButton(infoicon);
        infoButton.setOpaque(false);
        infoButton.setBorderPainted(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setFocusPainted(false);
        infoButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new InfoDialog(true)));

        northPanel.add(infoButton, BorderLayout.WEST);
        northPanel.add(invisiblebutton, BorderLayout.EAST);
    }

    private void title() {
        title = new JLabel("Json Generator");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Monaco",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        northPanel.add(title, BorderLayout.CENTER);
    }

    private void generateButton() {
        try {
            processicon = new ImageIcon(ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/settings.png")).getScaledInstance(48,48, SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateBtn = new JButton(processicon);
        generateBtn.addActionListener(e -> this.actionListener());
        generateBtn.setOpaque(false);
        generateBtn.setBorderPainted(false);
        generateBtn.setContentAreaFilled(false);
        generateBtn.setFocusPainted(false);

        southPanel.add(generateBtn, BorderLayout.WEST);
    }

    private void choiceBox(){
        try {
            block = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/block.png")).getScaledInstance(64,64, SCALE_SMOOTH);
            item = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/item.png")).getScaledInstance(64,64, SCALE_SMOOTH);
            tool = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/tool.png")).getScaledInstance(64,64, SCALE_SMOOTH);
            stair = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/stairs.png")).getScaledInstance(64,64, SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LabelledImage[] labelledImages = {new LabelledImage("Classic block", block),
                new LabelledImage("Basic item", item),
                new LabelledImage("Stairs shape block", stair),
                new LabelledImage("Basic tool", tool)};
        choiceBox = new JComboBox<LabelledImage>(labelledImages);
        choiceBox.setRenderer(new LabelledImageRenderer());
        boxPanel.add(choiceBox);
        boxPanel.setBorder(BorderFactory.createEmptyBorder(30,75,30,75));
    }

}

class LabelledImageRenderer extends JPanel implements ListCellRenderer<LabelledImage> {

    public LabelledImageRenderer() {
        this.setOpaque(true);
        this.setLayout(new GridLayout(1, 2));
        this.setPreferredSize(new Dimension(200, 70));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends LabelledImage> list, LabelledImage value, int index, boolean isSelected, boolean cellHasFocus) {
        this.removeAll();

        if (isSelected) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }

        JPanel labelledImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                final Image image = value.image();

                final int padding = 1,
                        cHeight = this.getHeight() - (padding) * 2,
                        cWidth = (int) ((float) cHeight * ((float) image.getWidth(null) / (float) image.getHeight(null))),
                        cX = 5,
                        cY = padding;

                g2.drawImage(image, cX, cY, null);
            }
        };
        labelledImagePanel.setOpaque(false);

        JLabel labelledImageLabel = new JLabel(value.label());
        /*if (isSelected)
            labelledImageLabel.setForeground(Color.WHITE);
        else*/
        labelledImageLabel.setForeground(Color.BLACK);

        labelledImageLabel.setOpaque(false);

        this.add(labelledImagePanel);
        this.add(labelledImageLabel);

        return this;
    }

}

