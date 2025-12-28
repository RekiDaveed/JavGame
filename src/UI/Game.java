package UI;

import Input.Keyboard;
import Main.G_State;
import Maps.Map;

import javax.swing.*;
import java.awt.*;


public class Game extends JPanel implements Runnable{
    public CardLayout GamecardLayout = new CardLayout();
    public Keyboard keyboard = new Keyboard();
    public Thread gameThread;
    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;
    public Map CurrentMap;
    public int[][] CurrentMapDrawing;



    int Plrx = 100;
    int Plry = 100;
    int PlrSpeed = 4;
    int FPS = 60;


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
    }

    public void Beginning(){
        G_State.SetState(G_State.Playing);
        this.CurrentMap = new Map(this);
    }

    @Override
    public void run() {
        while(gameThread != null){
            if (G_State.GetState() == G_State.Playing){
                updateGame(); // main entry point method With FPS control
            } else {
                // Nothing
            }
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void updateGame(){
        if(CurrentMap != null){
            CurrentMap.UpdateMap();
        }
    }
}
