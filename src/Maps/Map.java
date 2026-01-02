package Maps;

import Entity.Player;
import Input.Keyboard;
import UI.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Map extends JPanel {

    Keyboard keyboard = new Keyboard();
    public Player player;
    Game mainpanel;
    Tilemanager Tilemanager;

    public Map(Game mainpanel) {
        mainpanel.add(this, "MAP1");
        mainpanel.revalidate();
        mainpanel.repaint();
        this.mainpanel = mainpanel;
        this.Tilemanager = mainpanel.tilemanager;
        mainpanel.GamecardLayout.show(mainpanel, "MAP1");
        setBackground(Color.BLACK);
        System.out.println("Map1 Loaded");
        this.keyboard = mainpanel.keyboard;

        mainpanel.setFocusable(true);
        mainpanel.requestFocusInWindow();
        try {
            mainpanel.grabFocus();
        } catch (Exception ignored) {}

        mainpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainpanel.requestFocusInWindow();
                try { mainpanel.grabFocus(); } catch (Exception ignored) {}
            }
        });

        mainpanel.CurrentMapDrawing = mainpanel.tilemanager.getDefaultMap();

        player = new Player(this, mainpanel);
    }


    public void UpdateMap(){
        Movement();
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int offsetX = 0;
        int offsetY = 0;
        if (player != null) {
            offsetX = player.WorldX - player.ScreenX;
            offsetY = player.WorldY - player.ScreenY;

            int mapCols = mainpanel.tilemanager.getMapCols();
            int mapRows = mainpanel.tilemanager.getMapRows();
            int worldWidth = mapCols * mainpanel.TileSize;
            int worldHeight = mapRows * mainpanel.TileSize;

            int maxOffsetX = Math.max(0, worldWidth - mainpanel.JFrameWidth);
            int maxOffsetY = Math.max(0, worldHeight - mainpanel.JFrameHeight);
            if (offsetX < 0) offsetX = 0;
            if (offsetY < 0) offsetY = 0;
            if (offsetX > maxOffsetX) offsetX = maxOffsetX;
            if (offsetY > maxOffsetY) offsetY = maxOffsetY;
        }

        Tilemanager.DrawTiles(g2d, offsetX, offsetY);
        player.drawPlayer(g2d, offsetX, offsetY);
        g2d.dispose();
    }

    public void Movement(){
        player.UpdatePlayerMovement();
        Tilemanager.WorldUpdateLoop();
    }


}
