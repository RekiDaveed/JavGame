package Maps;

import Input.Keyboard;
import UI.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class Map1 extends JPanel {
    // Home of the first map
    // IF player dies and Chooses to restart, they will be sent back here

    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;

    public int[][] CurrentMapLayout;

    protected int[][] MapLayout;

    // Set Public player position
    int Plrx = 100;
    int Plry = 100;
    int PlrSpeed = 4;

    public Map1(Game mainpanel) {
        mainpanel.add(this, "MAP1");
        mainpanel.revalidate();
        mainpanel.repaint();
        mainpanel.GamecardLayout.show(mainpanel, "MAP1");
        setBackground(Color.DARK_GRAY);
        System.out.println("Map1 Loaded");

        CurrentMapLayout = Mapdata.Map1;
    }

    public void UpdateMap(){
        // Update map elements here
        repaint();
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


