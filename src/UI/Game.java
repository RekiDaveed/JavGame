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
        }
    }

    public void updateGame(){
        // Update game logic here
    }

}
