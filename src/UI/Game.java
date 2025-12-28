package UI;

import Input.Keyboard;
import Main.G_State;
import Maps.Map1;

import javax.swing.*;
import java.awt.*;


public class Game extends JPanel implements Runnable{
    public CardLayout GamecardLayout = new CardLayout();
    Keyboard keyboard = new Keyboard();
    public Thread gameThread;
    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;

    int Plrx = 100;
    int Plry = 100;
    int PlrSpeed = 4;


    public Game(){
        setLayout(GamecardLayout);
        GamecardLayout.show(this, "MENU");
        new Menu(this);
        addKeyListener(keyboard);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void GameStart(){
        gameThread = new Thread(this);
        gameThread.start();
        G_State.SetState(G_State.Playing);
      //  new Map1(this);
    }

    public void Beginning(){
        new Map1(this);
    }

    @Override
    public void run() {
        while(gameThread != null){

            // UPDATE GAME LOGIC HERE
            // REDRAW GAME HERE
            repaint();
            updateGame();
        }
    }

    public void updateGame(){
        // Update game logic here
        // Move player based on keyboard input
        if (keyboard.Uppressed) {
            Plry -= PlrSpeed;
        }
        if (keyboard.Dpressed) {
            Plry += PlrSpeed;
        }
        if (keyboard.Apressed) {
            Plrx -= PlrSpeed;
        }
        if (keyboard.Spressed) {
            Plrx += PlrSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Custom painting code here

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(Plrx,Plry,TileSize,TileSize);
        g2d.dispose();
    }

}
