package UI;

import Main.G_State;
import Maps.Map1;

import javax.swing.*;
import java.awt.*;


public class Game extends JPanel implements Runnable{
    public CardLayout GamecardLayout = new CardLayout();
    public Thread gameThread;
    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;


    public Game(){
        setLayout(GamecardLayout);
        GamecardLayout.show(this, "MENU");
        new Menu(this);

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
            System.out.println("Game Running");
            updateGame();
            repaint();
            // UPDATE GAME LOGIC HERE
            // REDRAW GAME HERE
        }
    }

    public void updateGame(){
        // Update game logic here
    }

}
