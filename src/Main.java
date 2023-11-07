import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Editor");
        frame.setLocation(0, 0);

        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = e.getDefaultScreenDevice();
        GraphicsConfiguration config = graphicsDevice.getDefaultConfiguration();

        frame.setSize(config.getBounds().getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        //Setting up the Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        String[] fileItems = {"New Tab", "New Window", "Open", "Save", "Save As", "Save All", "Page Setup", "Print", "Close Tab", "Close Window", "Exit"};
        for(String fileItem : fileItems){
            JMenuItem item = new JMenuItem(fileItem);
            fileMenu.add(item);
        }

        JMenu editMenu = new JMenu("Edit");
        String[] editItems = {"Undo", "Cut", "Copy", "Paste", "Delete", "Go to", "Select All", "Time/Date"};
        for(String editItem : editItems){
            JMenuItem item = new JMenuItem(editItem);
            editMenu.add(item);
        }

        JMenu reviewMenu = new JMenu("Review");
        String[] reviewItems = {"Zoom", "Status Bar", "Word Wrap"};
        for(String reviewItem : reviewItems){
            if(reviewItem.equals("Zoom")){
                JMenu subMenu = new JMenu(reviewItem);
                JMenuItem in = new JMenuItem("Zoom in");
                JMenuItem out = new JMenuItem("Zoom out");
                JMenuItem defaultItem = new JMenuItem("Restore Default Zoom");

                subMenu.add(in);
                subMenu.add(out);
                subMenu.add(defaultItem);
                reviewMenu.add(subMenu);
            }
            else {
                JMenuItem item = new JMenuItem(reviewItem);
                reviewMenu.add(item);
            }
        }

        JMenu helpMenu = new JMenu("Help");
        String[] helpItems = {"About", "Java Docs"};
        for(String helpItem : helpItems){
            JMenuItem item = new JMenuItem(helpItem);
            helpMenu.add(item);
        }

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(reviewMenu);
        menuBar.add(helpMenu);

        //SketchPad
        JPanel sketchPad = new JPanel();
        sketchPad.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sketchPad.setLayout(new BorderLayout());
        sketchPad.setBackground(Color.GRAY);

        JPanel topSketch = new JPanel();
        topSketch.setLayout(new GridLayout(2, 1));

        JLabel title = new JLabel();
        title.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel sketchTitle = new JLabel("SketchPad");
        title.add(sketchTitle);
        topSketch.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEADING, 0 ,0));
        String[] shapes = {"Rectangle", "Oval", "Line", "Triangle", "Pentagon"};
        for(String shape : shapes){
            JButton button = new JButton(shape);
            buttons.add(button);
        }

        JPanel spaceBetween = new JPanel();
        spaceBetween.setSize(10, 200);
        spaceBetween.setBackground(new Color(211, 211, 211));
        buttons.add(spaceBetween);

        JButton clearButton = new JButton("CLEAR");
        buttons.add(clearButton);

        topSketch.add(buttons, BorderLayout.CENTER);
        sketchPad.add(topSketch, BorderLayout.NORTH);


        //LeftSide of the screen
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(211, 211, 211));
        optionsPanel.setLayout(new GridLayout(6, 1,0, 0));
        optionsPanel.setPreferredSize(new Dimension(795, 200));

        JPanel fontPanel = new JPanel();
        fontPanel.setBackground(new Color(211, 211, 211));
        fontPanel.setSize(new Dimension(700, 50));
        fontPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        String[] iconsArray = {"bold", "italic", "underline", "strikethrough", "left_align", "center_align", "right_align", "justify"};
        for(int i = 0; i < iconsArray.length ; i++){
            ImageIcon icn = new ImageIcon("icons/" + iconsArray[i] + ".png");
            JButton btn = new JButton(icn);
            fontPanel.add(btn);
        }
        fontPanel.add(spaceBetween);

        String[] fonts = e.getAvailableFontFamilyNames();
        JComboBox<String> availableFonts = new JComboBox<String>(fonts);
        fontPanel.add(availableFonts);

        Integer[] fontSizes = {8, 10, 12, 14, 16, 18, 20};
        JComboBox<Integer> availableSizes = new JComboBox<>(fontSizes);
        fontPanel.add(availableSizes);

        
        optionsPanel.add(fontPanel);
        optionsPanel.add(new JLabel("Find"));
        optionsPanel.add(new JTextField());
        optionsPanel.add(new JLabel("Replace"));
        optionsPanel.add(new JTextField());

        JPanel findReplaceButtons = new JPanel();
        findReplaceButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        findReplaceButtons.setSize(new Dimension(700, 50));
        findReplaceButtons.setBackground(new Color(211, 211, 211));

        findReplaceButtons.add(new JButton("Find All"));
        findReplaceButtons.add(new JButton("Find Next"));
        findReplaceButtons.add(new JButton("Replace"));
        findReplaceButtons.add(new JButton("Replace All"));
        JTextArea textBox = new JTextArea();
        textBox.setLineWrap(true);

        optionsPanel.add(findReplaceButtons);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        leftPanel.add(optionsPanel,BorderLayout.NORTH);
        leftPanel.add(textBox,BorderLayout.CENTER);

        //Footer of the screen
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));
        footerPanel.setSize(new Dimension(frame.getWidth(), 50));
        footerPanel.setBackground(Color.GRAY);
        footerPanel.add(new JLabel("Word Count: "));
        footerPanel.add(new JLabel("Character Count: "));

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(sketchPad, BorderLayout.EAST);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
}