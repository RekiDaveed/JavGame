package UI;

import Main.G_State;
import Maps.Map1;

import javax.swing.*;
import java.awt.*;


public class Game extends JPanel {
    public CardLayout GamecardLayout = new CardLayout();


    public Game(){
        setLayout(GamecardLayout);
        GamecardLayout.show(this, "MENU");
        new Menu(this);

    }

    public void GameStart(){
        System.out.println("Game Started");
        G_State.SetState(G_State.Playing);
        new Map1(this);
    }
}
