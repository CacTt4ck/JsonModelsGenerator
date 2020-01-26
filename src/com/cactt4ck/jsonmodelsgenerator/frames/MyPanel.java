package com.cactt4ck.jsonmodelsgenerator.frames;

import com.cactt4ck.jsonmodelsgenerator.LabelledImage;
import com.cactt4ck.jsonmodelsgenerator.types.Block;
import com.cactt4ck.jsonmodelsgenerator.types.Items;
import com.cactt4ck.jsonmodelsgenerator.types.Tool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private JButton generateBtn, buttonPathChooser;
    private JTextField name;
    private File modPath;
    private JLabel title, pathFound;
    private Image block, item, tool, stair;
    private JComboBox<LabelledImage> choiceBox;
    private JPanel boxPanel, buttonPanel;
    private JFileChooser filechooser;
    private boolean pathSelected;

    public MyPanel() {
        super();
        this.pathSelected = false;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {
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

    private void nameField() {
        name = new JTextField();
        name.setToolTipText("Enter name here");
        name.setFont(new Font("Consolas", Font.PLAIN, 15));
        name.addActionListener(e -> this.actionListener());
        boxPanel.add(name);
    }

    private void pathFound() {
        pathFound = new JLabel();
        pathFound.setFont(new Font("Monaco",Font.PLAIN,12));
        pathFound.setForeground(Color.green);
        boxPanel.add(pathFound);
    }

    private void pathChooser() {
        buttonPathChooser = new JButton();
        buttonPathChooser.setText("Choose path");
        buttonPathChooser.addActionListener(e -> {
            modPath = paths();
            File[] directories = modPath.listFiles(File::isDirectory);
            if (isPathCorrect(directories)) {
                buttonPathChooser.setVisible(false);
                this.pathSelected = true;
                pathFound.setText("Path found !");
            } else
                this.displayErrorMessage();
        });
        boxPanel.add(buttonPathChooser);
    }

    private boolean isPathCorrect(File[] files) {
        ArrayList<String> directories = new ArrayList<String>();
        for (File file : files) {
            directories.add(file.getAbsolutePath());
            System.out.println(file.getAbsolutePath());
        }
        for (String directory :
                directories) {
            if(directory.contains(".gradle"))
                return true;
        }
        return false;
    }

    private void displayErrorMessage() {
        JOptionPane.showMessageDialog(this, "Error path incorrect", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private File paths() {
        filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.showOpenDialog(this);
        return filechooser.getSelectedFile();
    }

    private void actionListener() {
        if(pathSelected){
            Image choice = ((LabelledImage) choiceBox.getSelectedItem()).getImage();
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
            JOptionPane.showMessageDialog(this, "You have to select a correct path to your mod project!", "Incorrect Path",JOptionPane.ERROR_MESSAGE);
    }

    private void title() {
        title = new JLabel("Json Generator");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Monaco",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        this.add(title, BorderLayout.NORTH);
    }

    private void generateButton() {
        generateBtn = new JButton("Generate File");
        generateBtn.addActionListener(e -> this.actionListener());
        buttonPanel.add(generateBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void choiceBox(){
        try {
            block = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/block.png"));
            item = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/item.png"));
            tool = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/tool.png"));
            stair = ImageIO.read(MyPanel.class.getClassLoader().getResourceAsStream("assets/pictures/stairs.png"));
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
        boxPanel.setBorder(BorderFactory.createEmptyBorder(75,150,75,150));
    }

}

class LabelledImageRenderer extends JPanel implements ListCellRenderer<LabelledImage> {

    public LabelledImageRenderer() {
        this.setOpaque(true);
        this.setLayout(new GridLayout(1, 2));
        this.setPreferredSize(new Dimension(282, 70));
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


                final Image image = value.getImage();

                final int padding = 1,
                        cHeight = this.getHeight() - (padding) * 2,
                        cWidth = (int) ((float) cHeight * ((float) image.getWidth(null) / (float) image.getHeight(null))),
                        cX = 5,
                        cY = padding;

                g2.drawImage(image, cX, cY, null);
            }
        };
        labelledImagePanel.setOpaque(false);

        JLabel labelledImageLabel = new JLabel(value.getLabel());
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

