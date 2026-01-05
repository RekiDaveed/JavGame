package Maps;

import Entity.Entity;
import UI.Game;

public class CollisionChecker {

    Game game;
    public CollisionChecker(Game gamepanel) {
        this.game = gamepanel;

    }
    public void CheckTile(Entity entity) {
        int EntityLeftWorldX = entity.WorldX + entity.CollisionSize.x;
        int EntityRightWorldX = entity.WorldX + entity.CollisionSize.x + entity.CollisionSize.width;
        int EntityTopWorldY = entity.WorldY + entity.CollisionSize.y;
        int EntityBottomWorldY = EntityTopWorldY + entity.CollisionSize.y + entity.CollisionSize.height;

        int Entityleftcol = EntityLeftWorldX/game.TileSize;
        int EntityRightcol = EntityRightWorldX/game.TileSize;
        int EntityTopCol = EntityTopWorldY/game.TileSize;
        int EntityBottomCol = EntityBottomWorldY/game.TileSize;

        int Tilenum1, Tilenum2;

        switch (entity.direction){
            case "left":
            case "right":
            case "down":
            case "up": EntityTopCol =(EntityTopWorldY- entity.Speed) / game.TileSize;
            Tilenum1 = game.tilemanager.mapToDraw[Entityleftcol][EntityTopCol];
            Tilenum2 = game.tilemanager.mapToDraw[EntityRightcol][EntityTopCol];
            if (game.tilemanager.tiles[Tilenum1].collision || game.tilemanager.tiles[Tilenum2].collision) {
                entity.Collide = true;
            }
            break;
        }
    }
}
