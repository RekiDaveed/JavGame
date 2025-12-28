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
        G_State.SetPlaying(gameThread);
    }

    public void PauseGame(){
        G_State.SetPause(gameThread);
    }

    public void Beginning(){
        this.CurrentMap = new Map(this);
    }

    @Override
    public void run() {
        while(gameThread != null){
            updateGame(); // main entry point method
        }
    }

    public void updateGame(){
        if(CurrentMap != null){
            CurrentMap.UpdateMap();
        } else {
            System.out.println("No map loaded");
        }
    }
}
