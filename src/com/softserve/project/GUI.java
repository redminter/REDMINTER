package com.softserve.project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import javax.swing.*;
public class GUI {
int frameHeight = 394;
int frameWidth = 328;
int gameFieldSize = 296;
int marginSize = 16;
Color backgroundColor = new Color(225,225,120);
JFrame frame;
public GUI(){
    frame=new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GameField gf = new GameField();

    //Top panel
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new GridLayout());
    topPanel.setPreferredSize(new Dimension(frameWidth, 82));

    JLabel gameLabel = new JLabel("1024", SwingConstants.CENTER);
    gameLabel.setFont(new Font("Arial", Font.BOLD, 20));
    topPanel.add(gameLabel);
    topPanel.add(new JLabel("<html>Score:<br>524</html>", SwingConstants.CENTER));
    topPanel.add(new JLabel("<html>High Score:<br>22600</html>", SwingConstants.CENTER));
    topPanel.setBackground(backgroundColor);

    JPanel leftBuffer = new JPanel();
    leftBuffer.setPreferredSize(new Dimension(marginSize, gameFieldSize));
    leftBuffer.setBackground(backgroundColor);

    JPanel rightBuffer = new JPanel();
    rightBuffer.setPreferredSize(new Dimension(marginSize, gameFieldSize));
    rightBuffer.setBackground(backgroundColor);

    JPanel bottomBuffer = new JPanel();
    bottomBuffer.setPreferredSize(new Dimension(frameWidth, marginSize));
    bottomBuffer.setBackground(backgroundColor);

//add panels to the frame
    frame.getContentPane().add(topPanel,BorderLayout.NORTH);
    frame.getContentPane().add(leftBuffer,BorderLayout.WEST);
    frame.getContentPane().add(rightBuffer,BorderLayout.EAST);
    frame.getContentPane().add(bottomBuffer,BorderLayout.SOUTH);
    frame.getContentPane().add(gf,BorderLayout.CENTER);

    frame.getContentPane().setPreferredSize(new Dimension(frameWidth,frameHeight));
    frame.pack();
    frame.setVisible(true);


}
class GameField extends JPanel{

    protected void painComponent(Graphics g){
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }
}
}
