package Entity;

import java.awt.*;

public class Entity {
    public int WorldX;
    public int WorldY;
    public int Speed;
    public int ScreenX;
    public int ScreenY;
    public String direction = "down";

    public Rectangle CollisionSize;
    public boolean Collide = false;

}
