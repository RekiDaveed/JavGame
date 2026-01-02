package Maps;

import UI.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tilemanager {
    Game game;
    Tile[] tiles;
    MapsArray MapsArray = new MapsArray();

    // X = Left Right 0 - MAX , Y = Top Bottom 0 - MAX
    // Grass = Tile[0]
    // Water = Tile[1]
    // Earth = Tile[2]
    // Tree = Tile[3]
    // Flower = Tile[4]
    // Sand = Tile[5]
    // House = Tile[6]
    // Animal = Tile[7]
    // Road = Tile[8]


    public Tilemanager(Game game) {
        this.game = game;
        tiles = new Tile[10];
        GettileImage();
    }

    public void GettileImage(){
        try{
            BufferedImage FullSheetSunnySideWorld = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Pack/Environment/spr_tileset_sunnysideworld_16px.png")));
            int srcTile = game.TileSize;

            tiles[0] = new Tile();
            tiles[0].image = FullSheetSunnySideWorld.getSubimage(16,16, srcTile, srcTile);

            tiles[1] = new Tile();
            tiles[1].image = FullSheetSunnySideWorld.getSubimage(176,288, srcTile, srcTile);
            tiles[1].collision = false;

            tiles[2] = new Tile();
            tiles[2].image = FullSheetSunnySideWorld.getSubimage(144,112, srcTile, srcTile);

            tiles[3] = new Tile();
            tiles[3].image = FullSheetSunnySideWorld.getSubimage(816,16, srcTile, srcTile);
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = FullSheetSunnySideWorld.getSubimage(864,160, 15, 16);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DrawTiles(Graphics2D g2, int offsetX, int offsetY){
        int[][] mapToDraw = game.CurrentMapDrawing;
        if (mapToDraw == null || mapToDraw.length == 0) {
            mapToDraw = MapsArray.WorldMap1;
            if (mapToDraw == null || mapToDraw.length == 0) {
                mapToDraw = MapsArray.Map1;
            }
        }

        int rows = mapToDraw.length;
        int cols = mapToDraw[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int tileNum = mapToDraw[row][col];
                int worldX = col * game.TileSize;
                int worldY = row * game.TileSize;
                int screenX = worldX - offsetX;
                int screenY = worldY - offsetY;

                if (screenX + game.TileSize < 0 || screenX > game.JFrameWidth || screenY + game.TileSize < 0 || screenY > game.JFrameHeight) {
                    continue;
                }

                BufferedImage img = null;
                if (tileNum >= 0 && tileNum < tiles.length && tiles[tileNum] != null) {
                    img = tiles[tileNum].image;
                }
                if (img == null) {
                    if (tiles[0] != null) img = tiles[0].image;
                }
                if (img != null) {
                    g2.drawImage(img, screenX, screenY, game.TileSize, game.TileSize, null);
                } else {
                    g2.setColor(Color.MAGENTA);
                    g2.fillRect(screenX, screenY, game.TileSize, game.TileSize);
                }
            }
        }
    }

    public int getMapCols() {
        int[][] mapToDraw = MapsArray.WorldMap1;
        if (mapToDraw == null || mapToDraw.length == 0) {
            mapToDraw = MapsArray.Map1;
        }
        return mapToDraw[0].length;
    }

    public int getMapRows() {
        int[][] mapToDraw = MapsArray.WorldMap1;
        if (mapToDraw == null || mapToDraw.length == 0) {
            mapToDraw = MapsArray.Map1;
        }
        return mapToDraw.length;
    }

    public int[][] getDefaultMap(){
        int[][] mapToDraw = MapsArray.WorldMap1;
        if (mapToDraw == null || mapToDraw.length == 0) mapToDraw = MapsArray.Map1;
        return mapToDraw;
    }
}
