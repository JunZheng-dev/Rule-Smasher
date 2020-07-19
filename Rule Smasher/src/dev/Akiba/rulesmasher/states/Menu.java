package dev.Akiba.rulesmasher.states;

import dev.Akiba.rulesmasher.Game;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu {
 
    public static JFrame menuFrame;
    private JPanel panel;
    private JButton startButton;
    private Font font;
    private JLabel widthLabel, heightLabel, nameLabel;
    private static JTextField widthTF, heightTF, nameTF;
    
    private Game game;
    private int gameWidth, gameHeight;
    private String worldName;
    
    public Menu() {
        createDisplay();
    }
    
    public void createDisplay() {
        Font menuFont = new Font("Arial", Font.BOLD, 16);
        menuFrame = new JFrame("Rule Smasher");
        menuFrame.setSize(250, 170);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        widthLabel = new JLabel("Width:  ");
        heightLabel = new JLabel("Height: ");
        nameLabel = new JLabel ("Level: ");
        widthTF = new JTextField("1600");
        heightTF = new JTextField("900");
        nameTF = new JTextField("world1.lvl");
        startButton = new JButton("Start Game!");
        
        widthLabel.setFont(menuFont);
        heightLabel.setFont(menuFont);
        nameLabel.setFont(menuFont);
        startButton.setFont(menuFont);
        startButton.setFocusPainted(false);
        
        widthTF.setPreferredSize(new Dimension(171, 22));
        heightTF.setPreferredSize(new Dimension(170, 22));
        nameTF.setPreferredSize(new Dimension(180, 22));
        startButton.setPreferredSize(new Dimension(234, 50));

        panel.add(widthLabel);
        panel.add(widthTF);
        panel.add(heightLabel);
        panel.add(heightTF);
        panel.add(nameLabel);
        panel.add(nameTF);
        panel.add(startButton);
        
        startButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent action) { 
                menuFrame.setVisible(false);
                worldName = nameTF.getText();
                gameWidth = Integer.parseInt(widthTF.getText());
                gameHeight = Integer.parseInt(heightTF.getText());

                game = new Game("Rule Smasher", worldName, gameWidth, gameHeight);
                game.start();
            } 
        });
        
        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
    
    public void startGame() {
        
    }
}
