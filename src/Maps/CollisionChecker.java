package Maps;

import Entity.Entity;
import UI.Game;

public class CollisionChecker {

    Game game;
    public CollisionChecker(Game gamepanel) {
        this.game = gamepanel;

    }
    public void CheckTile(Entity entity) {
        int leftWorldX = entity.WorldX + entity.CollisionSize.x;
        int topWorldY = entity.WorldY + entity.CollisionSize.y;
        int rightWorldX = entity.WorldX + entity.CollisionSize.x + entity.CollisionSize.width - 1;
        int bottomWorldY = entity.WorldY + entity.CollisionSize.y + entity.CollisionSize.height - 1;

        int tileSize = game.TileSize;

        int nextLeft = leftWorldX;
        int nextRight = rightWorldX;
        int nextTop = topWorldY;
        int nextBottom = bottomWorldY;

        // Convert world pixels to tile indices (row = y, col = x)
        int leftCol = nextLeft / tileSize;
        int rightCol = nextRight / tileSize;
        int topRow = nextTop / tileSize;
        int bottomRow = nextBottom / tileSize;

        int rows = game.tilemanager.getMapRows();
        int cols = game.tilemanager.getMapCols();

        if (leftCol < 0) leftCol = 0;
        if (rightCol < 0) rightCol = 0;
        if (topRow < 0) topRow = 0;
        if (bottomRow < 0) bottomRow = 0;
        if (leftCol >= cols) leftCol = cols - 1;
        if (rightCol >= cols) rightCol = cols - 1;
        if (topRow >= rows) topRow = rows - 1;
        if (bottomRow >= rows) bottomRow = rows - 1;

        boolean collisionFound = false;
        for (int r = topRow; r <= bottomRow && !collisionFound; r++) {
            for (int c = leftCol; c <= rightCol; c++) {
                int tileNum = game.tilemanager.mapToDraw[r][c];
                if (tileNum >= 0 && tileNum < game.tilemanager.tiles.length) {
                    if (game.tilemanager.tiles[tileNum] != null && game.tilemanager.tiles[tileNum].collision) {
                        collisionFound = true;
                        break;
                    }
                }
            }
        }

        entity.Collide = collisionFound;
    }
}
