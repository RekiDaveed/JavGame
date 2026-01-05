package Maps;

import UI.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tilemanager {
    Game game;
    public Tile[] tiles;
    MapsArray MapsArray = new MapsArray();
    int[][] mapToDraw;
    int[][] MapReference;

    // Animation Var
    int AnimCounter = 0;
    int AnimFrame = 0;

    // X = Left Right 0 - MAX , Y = Top Bottom 0 - MAX

    public Tilemanager(Game game) {
        this.game = game;
        game.CurrentMapDrawing = MapsArray.WorldMap1;
        tiles = new Tile[50];
        mapToDraw = game.CurrentMapDrawing;
        MapReference = MapsArray.WorldMap1;
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

            // -------------------------------------------------------------------------------------------------------------------------------------------//
            BufferedImage CornerImageBL = TileSet_V1Png.getSubimage(64,128, srcTile, srcTile);
            tiles[4] = new Tile();
            tiles[4].image = RotateImg(CornerImageBL, -90); // CORNER BOTTOM LEFT

            tiles[5] = new Tile();
            tiles[5].image = RotateImg(CornerImageBL, 180); // CORNER BOTTOM RIGHT

            tiles[6] = new Tile();
            tiles[6].image = CornerImageBL;  // CORNER TOP LEFT

            tiles[7] = new Tile();
            tiles[7].image = RotateImg(CornerImageBL, 90);  // CORNER TOP RIGHT

            // --------------------------------------------------------------------------------------------------------------------------------------------//
            BufferedImage WallGrassTile = TileSet_V1Png.getSubimage(128,64, srcTile, srcTile);

            // GRASS CORNERS

            tiles[8] = new Tile();
            tiles[8].image = RotateImg(WallGrassTile, -90); // LEFT WALL GRASS TILE

            tiles[9] = new Tile();
            tiles[9].image = WallGrassTile; // TOP WALL GRASS TILE

            tiles[10] = new Tile();
            tiles[10].image = RotateImg(tiles[8].image, 180); //  RIGHT WALL GRASS TILE

            tiles[11] = new Tile();
            tiles[11].image = FlippedImage(WallGrassTile, false, true);  // BOTTOM WALL GRASS TILE

            // ---------------------------------------------------------------------------------------------------------------------------------------//

            // Water Tiles

            BufferedImage WaterAnimation = TileSet_V1Png.getSubimage(41, 224, srcTile, srcTile); // 12
            BufferedImage WaterAnimation2 = TileSet_V1Png.getSubimage(55, 224, srcTile, srcTile); // 13
            BufferedImage WaterAnimation3 = TileSet_V1Png.getSubimage(0, 416, srcTile, srcTile); // 14

            tiles[12] = new Tile();
            tiles[12].image = WaterAnimation3;

            tiles[13] = new Tile();
            tiles[13].image = WaterAnimation;

            tiles[14] = new Tile();
            tiles[14].image = WaterAnimation2;


            // ----------------------------------------------------------------------------------------------------------------------------------------//

            BufferedImage WaterCorner = TileSet_V1Png.getSubimage(64, 352, srcTile, srcTile);
            tiles[15] = new Tile();
            tiles[15].image = WaterCorner;  // TOP LEFT CORNER

            tiles[16] = new Tile();
            tiles[16].image = RotateImg(WaterCorner, -90); // BOTTOM LEFT CORNER

            tiles[17] = new Tile();
            tiles[17].image = RotateImg(WaterCorner, -180); //  BOTTOM RIGHT CORNER

            tiles[18] = new Tile();
            tiles[18].image = RotateImg(WaterCorner, 90);  // TOP RIGHT CORNER

            BufferedImage WaterLine = TileSet_V1Png.getSubimage(128, 288, srcTile, srcTile);

            tiles[19] = new Tile();
            tiles[19].image = WaterLine;  // TOP
            tiles[19].collision = true;

            tiles[20] = new Tile();
            tiles[20].image = RotateImg(WaterLine, -90); // RIGHT
            tiles[20].collision = true;

            tiles[21] = new Tile();
            tiles[21].image = RotateImg(WaterLine, 90); // LEFT
            tiles[21].collision = true;

            tiles[22] = new Tile();
            tiles[22].image = RotateImg(WaterLine, -180); // BOTTOM
            tiles[22].collision = true;


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

    public BufferedImage RotateImg(BufferedImage image, int degrees) {
        int w = image.getWidth();
        int h = image.getHeight();

        boolean swapped = (degrees == 90 || degrees == 270 || degrees == -90 || degrees == -270);
        int newW = swapped ? h : w;
        int newH = swapped ? w : h;

        BufferedImage rotatedImage = new BufferedImage(newW, newH, image.getType());
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.translate((newW - w) / 2.0, (newH - h) / 2.0);

        g2.rotate(Math.toRadians(degrees), w / 2.0, h / 2.0);

        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return rotatedImage;
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
    public void WorldUpdateLoop(){
        AnimCounter++;
        if(AnimCounter > 50){
            AnimFrame++;
            if(AnimFrame > 3){
                AnimFrame = 0;
            }
            AnimCounter = 0;
        }
        AnimateTiles(0, 3); // Grass Animation
        AnimateTiles(12, 14);

    }

    public void AnimateTiles(int Startindex, int Endindex) {

        int totalFrames = (Endindex - Startindex) + 1;

        for (int row = 0; row < MapReference.length; row++) {
            for (int col = 0; col < MapReference[0].length; col++) {
                int originalTile = MapReference[row][col];

                if (originalTile >= Startindex && originalTile <= Endindex) {
                    int frameOffset = (AnimFrame + row + col) % totalFrames;
                    mapToDraw[row][col] = Startindex + frameOffset;
                }
            }
        }
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
