package main.java.com.softserve.project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI {
    Game game;

    int frameHeight = 394;
    int frameWidth = 328;
    int gameFieldSize = 296;
    int marginSize = 16;
    Color backgroundColor = new Color(148, 128, 84);

    Font largeFeedbackFont = new Font("SansSerif", 0, 40);
    Font smallFeedbackFont =new Font("SansSerif", 0, 20);

    JLabel scoreLabel;

    Map numberTiles;
    GameField gf;

    MyFrame frame;

    public GUI() {
        game = new Game();
        frame = new MyFrame();
        frame.setFocusable(true);
        frame.addKeyListener(new MyFrame());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadNumberTiles();

        gf = new GameField();
        //gf.setFocusable(true);

        //Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        topPanel.setPreferredSize(new Dimension(frameWidth, 82));

        JLabel gameLabel = new JLabel("1024", SwingConstants.CENTER);
        gameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(gameLabel);
        scoreLabel=new JLabel("<html>Score:<br>0</html>", SwingConstants.CENTER);
        topPanel.add(scoreLabel);
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
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(leftBuffer, BorderLayout.WEST);
        frame.getContentPane().add(rightBuffer, BorderLayout.EAST);
        frame.getContentPane().add(bottomBuffer, BorderLayout.SOUTH);
        frame.getContentPane().add(gf, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.pack();
        frame.setVisible(true);


    }

    private void loadNumberTiles() {
        numberTiles = new Hashtable();
        ClassLoader cldr = this.getClass().getClassLoader();
        URL url0000 = cldr.getResource("main/resources/images/tile_64.png");
        URL url0001 = cldr.getResource("main/resources/images/tile01_64.png");
        URL url0002 = cldr.getResource("main/resources/images/tile02_64.png");
        URL url0004 = cldr.getResource("main/resources/images/tile04_64.png");
        URL url0008 = cldr.getResource("main/resources/images/tile08_64.png");
        URL url0016 = cldr.getResource("main/resources/images/tile16_64.png");
        URL url0032 = cldr.getResource("main/resources/images/tile32_64.png");
        URL url0064 = cldr.getResource("main/resources/images/tile64_64.png");
        URL url0128 = cldr.getResource("main/resources/images/tile128_64.png");
        URL url0256 = cldr.getResource("main/resources/images/tile256_64.png");
        URL url0512 = cldr.getResource("main/resources/images/tile512_64.png");
        URL url1024 = cldr.getResource("main/resources/images/tile1024_64.png");

        numberTiles.put(0, new ImageIcon(url0000));
        numberTiles.put(1, new ImageIcon(url0001));
        numberTiles.put(2, new ImageIcon(url0002));
        numberTiles.put(4, new ImageIcon(url0004));
        numberTiles.put(8, new ImageIcon(url0008));
        numberTiles.put(16, new ImageIcon(url0016));
        numberTiles.put(32, new ImageIcon(url0032));
        numberTiles.put(64, new ImageIcon(url0064));
        numberTiles.put(128, new ImageIcon(url0128));
        numberTiles.put(256, new ImageIcon(url0256));
        numberTiles.put(512, new ImageIcon(url0512));
        numberTiles.put(1024, new ImageIcon(url1024));
    }

    class GameField extends JPanel{

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(109, 114, 97));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            int[][] field = game.getGameField();
            //int[][] field ={{0,2,4,8},{16,32,512,128},{1,256,512,1024},{0,0,0,0}};
            for (int y = 1; y < 5; y++) {
                for (int x = 1; x < 5; x++) {
                    int X = (8 * x) + (64 * (x - 1));
                    int Y = (8 * y) + (64 * (y - 1));
                    int thisNumber = field[y - 1][x - 1];
                    if (numberTiles.containsKey(thisNumber)) {
                        ImageIcon thisTile = (ImageIcon) numberTiles.get(thisNumber);
                        thisTile.paintIcon(this, g, X, Y);
                    }
                }
            }
        }
    }
    class WinBoard extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(0, 80, 0));
            g.drawString("You won!", 20, 40);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255, 255, 255));
            g.drawString("Press ENTER to play again!", 20, 70);
        }
    }
    class LoseBoard extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(200, 0, 0));
            g.drawString("You lose(", 20, 40);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255, 255, 255));
            g.drawString("Press ENTER to try again!", 20, 70);
        }
    }

    class MyFrame extends JFrame implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (game.getState() == GameState.CONTINUE) {
                if (key == KeyEvent.VK_UP) {
                    System.out.println("Up pushed...");
                    game.pushUp();
                    game.addNewNumber();
                    game.checkState();
                    gf.repaint();
                    updateScore();

                } else if (key == KeyEvent.VK_DOWN) {
                    System.out.println("Down pushed...");
                    game.pushDown();
                    game.addNewNumber();
                    game.checkState();
                    gf.repaint();
                    updateScore();
                } else if (key == KeyEvent.VK_LEFT) {
                    System.out.println("Left pushed...");
                    game.pushLeft();
                    game.addNewNumber();
                    game.checkState();
                    gf.repaint();
                    updateScore();
                } else if (key == KeyEvent.VK_RIGHT) {
                    System.out.println("Right pushed...");
                    game.pushRight();
                    game.addNewNumber();
                    game.checkState();
                    gf.repaint();
                    updateScore();
                }
                GameState gs = game.getState();
                if (gs == GameState.LOOSE) {
                    frame.getContentPane().remove(gf);
                    frame.getContentPane().add(new LoseBoard(), BorderLayout.CENTER);
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();

                } else if (gs == GameState.WIN) {
                    frame.getContentPane().remove(gf);
                    frame.getContentPane().add(new WinBoard(), BorderLayout.CENTER);
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();
                    System.out.println("Game Over, User won!");
                }
            }  else {
                if (key == KeyEvent.VK_ENTER) {
                    game = new Game();
                   frame.remove(((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.CENTER));
                   frame.getContentPane().add(gf);
                   gf.repaint();
                   frame.getContentPane().invalidate();
                   frame.getContentPane().validate();
                }
            }
        }
    }
    public  void updateScore(){
        scoreLabel.setText("<html>Score:<br>"+game.getScore()+"</html>");
    }
}

