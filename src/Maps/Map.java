package Maps;

import Entity.Player;
import Input.Keyboard;
import UI.Game;

import javax.swing.*;
import java.awt.*;


public class Map extends JPanel {
    // Home of the first map
    // IF player dies and Chooses to restart, they will be sent back her

    Keyboard keyboard = new Keyboard();
    public Player player;

    public Map(Game mainpanel) {
        mainpanel.add(this, "MAP1");
        mainpanel.revalidate();
        mainpanel.repaint();
        mainpanel.GamecardLayout.show(mainpanel, "MAP1");
        setBackground(Color.BLACK);
        System.out.println("Map1 Loaded");
        this.keyboard = mainpanel.keyboard;

        player = new Player(this, mainpanel);
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



