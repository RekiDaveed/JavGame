package Entity;

import Input.Keyboard;
import Maps.Map;
import Maps.Tilemanager;
import UI.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends  Entity {

    Map map;
    Keyboard keyboard;
    Game gamepanel;

    public BufferedImage[] PlayerLeft = new BufferedImage[4];
    public BufferedImage[] PlayerRight = new BufferedImage[4];
    public BufferedImage[] PlayerUp = new BufferedImage[4];
    public BufferedImage[] PlayerDown = new BufferedImage[4];

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Player(Map map, Game mainpanel) {
        this.map = map;
        this.keyboard = mainpanel.keyboard;
        this.gamepanel = mainpanel;
        this.ScreenX = mainpanel.JFrameWidth / 2 - (mainpanel.TileSize / 2);
        this.ScreenY = mainpanel.JFrameHeight / 2 - (mainpanel.TileSize / 2);

        CollisionSize = new Rectangle(8, 16, mainpanel.TileSize - 10, mainpanel.TileSize - 10);
        SetDefaultValues();
        LoadPlayerMovementImages();
    }
    
    public void drawPlayer(Graphics2D g2d, int offsetX, int offsetY){
        BufferedImage CurrentRender = switch (direction) {
            case "left" -> PlayerLeft[spriteNum];
            case "right" -> PlayerRight[spriteNum];
            case "up" -> PlayerUp[spriteNum];
            case "down" -> PlayerDown[spriteNum];
            default -> null;
        };

        int screenX = WorldX - offsetX;
        int screenY = WorldY - offsetY;

        if (CurrentRender != null) {
            g2d.drawImage(CurrentRender, screenX, screenY, gamepanel.TileSize, gamepanel.TileSize, null);
        } else {
            // Fallback: draw a blue rectangle if images are not loaded

            g2d.setColor(Color.BLUE);
            g2d.fillRect(screenX, screenY, gamepanel.TileSize, gamepanel.TileSize);
        }
    }

    public void SetDefaultValues(){
        WorldX = gamepanel.TileSize * 23;
        WorldY = gamepanel.TileSize * 21;
        Speed = 4;
        direction = "left";
    }

    public void LoadPlayerMovementImages(){
        // Load player movement images here
        try {
            BufferedImage PlayerLeftfullSheet = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Pack/Anim/Player_Walk_Left.png")));
            BufferedImage PlayerRightfullSheet  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Pack/Anim/Player_Walk_Right.png")));
            BufferedImage PlayerDownfullSheet  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Pack/Anim/Player_Walk_Forward.png")));
            BufferedImage PlayerUpfullSheet  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Pack/Anim/Player_Walk_Backwards.png")));

            for (int i=0; i<4; i++){
                PlayerLeft[i] = PlayerLeftfullSheet.getSubimage(i*16, 0, 16, 16);
                PlayerRight[i] = PlayerRightfullSheet.getSubimage(i*16, 0, 16, 16);
                PlayerDown[i] = PlayerDownfullSheet.getSubimage(i*16, 0, 16, 16);
                PlayerUp[i] = PlayerUpfullSheet.getSubimage(i*16, 0, 16, 16);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void UpdatePlayerMovement(){

        Tilemanager tm = gamepanel.tilemanager;
        int mapCols = tm.getMapCols();
        int mapRows = tm.getMapRows();
        int maxWorldX = (mapCols * gamepanel.TileSize) - gamepanel.TileSize;
        int maxWorldY = (mapRows * gamepanel.TileSize) - gamepanel.TileSize;
        if (WorldX < 0) WorldX = 0;
        if (WorldY < 0) WorldY = 0;
        if (WorldX > maxWorldX) WorldX = maxWorldX;
        if (WorldY > maxWorldY) WorldY = maxWorldY;

        if (keyboard.Uppressed || keyboard.Spressed || keyboard.Apressed || keyboard.Dpressed) {
            spriteCounter++;

            // Update player elements here
            if (keyboard.Uppressed) {
                direction = "up";
            }
            if (keyboard.Dpressed) {
                direction = "right";
            }
            if (keyboard.Apressed) {
                direction = "left";
            }
            if (keyboard.Spressed) {
                direction = "down";
            }

        // COLLISION CHECK HERE
        Collide = false;

        // Axis-separated movement: check X, then Y
        int dx = 0, dy = 0;
        switch (direction) {
            case "up": dy = -Speed; break;
            case "down": dy = Speed; break;
            case "left": dx = -Speed; break;
            case "right": dx = Speed; break;
        }

        // Tentatively move in X and check
        if (dx != 0) {
            int oldX = WorldX;
            WorldX += dx;
            gamepanel.CollisionChecker.CheckTile(this);
            if (Collide) {
                WorldX = oldX; // revert if collision
            }
        }

        // Tentatively move in Y and check
        if (dy != 0) {
            int oldY = WorldY;
            WorldY += dy;
            gamepanel.CollisionChecker.CheckTile(this);
            if (Collide) {
                WorldY = oldY; // revert if collision
            }
        }

        // sprite animation update
        if (spriteCounter > 10) {
            spriteNum++;
            if (spriteNum >= 4) spriteNum = 0;
            spriteCounter = 0;
        }
        } else {
            spriteNum = 0; // Reset to standing still frame when not moving
        }
    }
}
