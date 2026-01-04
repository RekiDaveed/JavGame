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
    int[][] mapToDraw;
    int[][] MapReference;

    // Animation Var
    int AnimCounter = 0;
    int AnimFrame = 0;

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
        game.CurrentMapDrawing = MapsArray.WorldMap1;
        tiles = new Tile[20];
        mapToDraw = game.CurrentMapDrawing;
        MapReference = MapsArray.WorldMap1;
        tiles = new Tile[10];
        mapToDraw = game.CurrentMapDrawing;
        GettileImage();
        WorldUpdateLoop();
    }

    public void GettileImage(){
        try{
            BufferedImage FullSheetSunnySideWorld = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Pack/Environment/spr_tileset_sunnysideworld_16px.png")));
            BufferedImage TinyWorldTilesPng = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Pack/Environment/Tiny World Tiles.png")));
            BufferedImage TileSet_V1Png = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Pack/Environment/TileSet_V1.png")));

            int srcTile = game.TileSize;

            BufferedImage Grass4frameTiles = TileSet_V1Png.getSubimage(0,192, srcTile * 4, srcTile * 4);  // Grass tile  0 - 3 (animated)
            for (int i = 0; i < 4; i++) {
                tiles[i] = new Tile();
                tiles[i].image = Grass4frameTiles.getSubimage(i * srcTile, 0, srcTile, srcTile);
            }

            BufferedImage CornerImageBL = TileSet_V1Png.getSubimage(0,160, srcTile, srcTile);
            tiles[4] = new Tile();
            tiles[4].image = CornerImageBL;  // Corner BOTTOM LEFT

            tiles[5] = new Tile();
            tiles[5].image = FlippedImage(CornerImageBL, true, false); // Corner BOTTOM RIGHT

            tiles[6] = new Tile();
            tiles[6].image = FlippedImage(CornerImageBL, false, true);  // Corner TOP RIGHT

            tiles[7] = new Tile();
            tiles[7].image = FlippedImage(CornerImageBL, true, true);  // Corner TOP LEFT

            BufferedImage WallGrassTile = TileSet_V1Png.getSubimage(128,64, srcTile, srcTile);
            tiles[8] = new Tile();
            tiles[8].image = FlippedImage(WallGrassTile, true, true); // LEFT WALL GRASS TILE

            tiles[9] = new Tile();
            tiles[9].image = WallGrassTile; // TOP WALL GRASS TILE

            tiles[10] = new Tile();
            tiles[10].image = FlippedImage(WallGrassTile, false, true);

            tiles[11] = new Tile();
            tiles[11].image = FlippedImage(WallGrassTile, false, true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage FlippedImage(BufferedImage image, boolean Horizontal, boolean Vertical){
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, image.getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, Horizontal ? w : 0, Vertical ? h : 0, Horizontal ? -w : w, Vertical ? -h : h, null);
        g.dispose();
        return flippedImage;

    }

    public void DrawTiles(Graphics2D g2, int offsetX, int offsetY){

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
    public void AnimateTiles(){
        AnimCounter++;
        if (AnimCounter > 50){
            AnimFrame++;
            if (AnimFrame > 3){
                AnimFrame = 0;
            }
            // Update animated tiles in mapToDraw
            for (int row = 0; row < MapReference.length; row++) {
                for (int col = 0; col < MapReference[0].length; col++) {

                    if (MapReference[row][col] >= 0 && MapReference[row][col] <= 3) {
                        int individualFrame = (AnimFrame + row + col) % 4;
                        mapToDraw[row][col] = individualFrame;
                    }
                }
            }
            AnimCounter = 0;
        }
    }

    public void WorldUpdateLoop(){
        AnimateTiles();
    }

    public int getMapCols() {
        return mapToDraw[0].length;
    }

    public int getMapRows() {
        return mapToDraw.length;
    }

    public int[][] getDefaultMap(){
        return mapToDraw;
    }
}
