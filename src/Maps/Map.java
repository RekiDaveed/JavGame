package Maps;

import Entity.Player;
import Input.Keyboard;
import UI.Game;

import javax.swing.*;
import java.awt.*;


public class Map extends JPanel {
    // Home of the first map
    // IF player dies and Chooses to restart, they will be sent back here

    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;

    Keyboard keyboard = new Keyboard();
    public Player player;

    // Set Public player position
    int Plrx = 100;
    int Plry = 100;
    int PlrSpeed = 4;


    public Map(Game mainpanel) {
        mainpanel.add(this, "MAP1");
        mainpanel.revalidate();
        mainpanel.repaint();
        mainpanel.GamecardLayout.show(mainpanel, "MAP1");
        setBackground(Color.DARK_GRAY);
        System.out.println("Map1 Loaded");
        this.keyboard = mainpanel.keyboard;

        mainpanel.CurrentMapDrawing = Mapdata.Map1;

        player = new Player(this, mainpanel.keyboard);
    }


    public void UpdateMap(){
        // Update map elements here
        repaint();
        Movement();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Custom painting code here

        Graphics2D g2d = (Graphics2D) g;
        player.drawPlayer(g2d);
        g2d.dispose();
    }

    public void Movement(){
        player.UpdatePlayerMovement();
    }

}



