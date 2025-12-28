package Entity;

import Input.Keyboard;
import Maps.Map;
import UI.Game;

import java.awt.*;

public class Player extends  Entity {

    Map map;
    Keyboard keyboard;

    public Player(Map map, Keyboard keyboard) {
        this.map = map;
        this.keyboard = keyboard;
        SetDefaultValues();
    }
    
    public void drawPlayer(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(PositionX, PositionY, map.TileSize, map.TileSize);

    }

    public void SetDefaultValues(){
        PositionX = 100;
        PositionY = 100;
        Speed = 4;
    }
    
    public void UpdatePlayerMovement(){
        // Update player elements here
        if (keyboard.Uppressed) {
            PositionY -= Speed;
            System.out.println("Plry up");
        }
        if (keyboard.Dpressed) {
            PositionX += Speed;
            System.out.println("Plry Right");
        }
        if (keyboard.Apressed) {
            PositionX -= Speed;
            System.out.println("Plrx Left");
        }
        if (keyboard.Spressed) {
            PositionY += Speed;
            System.out.println("Plrx down");
        }
    }
}
