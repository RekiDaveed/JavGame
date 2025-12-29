package Entity;

import Input.Keyboard;
import Maps.Map;
import UI.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends  Entity {

    Map map;
    Keyboard keyboard;

    public BufferedImage[] PlayerLeft = new BufferedImage[4];
    public BufferedImage[] PlayerRight = new BufferedImage[4];
    public BufferedImage[] PlayerUp = new BufferedImage[4];
    public BufferedImage[] PlayerDown = new BufferedImage[4];

    public String Direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Player(Map map, Keyboard keyboard) {
        this.map = map;
        this.keyboard = keyboard;
        SetDefaultValues();
        LoadPlayerMovementImages();
    }
    
    public void drawPlayer(Graphics2D g2d){
        BufferedImage CurrentRender = null;

        switch (Direction){
            case "left":
                CurrentRender = PlayerLeft[spriteNum];
                break;
            case "right":
                CurrentRender = PlayerRight[spriteNum];
                break;
            case "up":
                CurrentRender = PlayerUp[spriteNum];
                break;
            case "down":
                CurrentRender = PlayerDown[spriteNum];
                break;
        }

        if (CurrentRender != null) {
            g2d.drawImage(CurrentRender, PositionX, PositionY, map.TileSize, map.TileSize, null);
        } else {
            // Fallback: draw a blue rectangle if images are not loaded

            g2d.setColor(Color.BLUE);
            g2d.fillRect(PositionX, PositionY, map.TileSize, map.TileSize);
        }
    }

    public void SetDefaultValues(){
        PositionX = 100;
        PositionY = 100;
        Speed = 4;
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
        } catch (IOException _) {

        }
    }
    
    public void UpdatePlayerMovement(){
        // Update player elements here
        if (keyboard.Uppressed) {
            PositionY -= Speed;
            Direction = "up";
        }
        if (keyboard.Dpressed) {
            PositionX += Speed;
            Direction = "right";
        }
        if (keyboard.Apressed) {
            PositionX -= Speed;
            Direction = "left";
        }
        if (keyboard.Spressed) {
            PositionY += Speed;
            Direction = "down";
        }

        if (keyboard.Uppressed || keyboard.Spressed || keyboard.Apressed || keyboard.Dpressed) {
            spriteCounter++;
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
