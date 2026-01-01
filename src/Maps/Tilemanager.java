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

            // GrassTile
            tiles[0] = new Tile();
            tiles[0].image = FullSheetSunnySideWorld.getSubimage(16,16, game.TileSize, game.TileSize);

            // WaterTile
            tiles[1] = new Tile();
            tiles[1].image =  FullSheetSunnySideWorld.getSubimage(176,288, game.TileSize, game.TileSize);
            tiles[1].collision = false;

            // Earth Tile
            tiles[2] = new Tile();
            tiles[2].image =  FullSheetSunnySideWorld.getSubimage(144,112, game.TileSize, game.TileSize);

            // Tree Tile
            tiles[3] = new Tile();
            tiles[3].image = FullSheetSunnySideWorld.getSubimage(816,16, game.TileSize, game.TileSize);
            tiles[3].collision = true;

            // Flower Tile 15X16
            tiles[4] = new Tile();
            tiles[4].image = FullSheetSunnySideWorld.getSubimage(864,160, 15, 16);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DrawTiles(Graphics2D g2){
        int width = 0; // calcuate tile position
        int height = 0; //Calculate tile position

        while (width < game.TileXCount) {
            while(height < game.TileYCount) {

                int tileNum = MapsArray.Map1[height][width];
                int WorldX = width * game.TileSize;
                int WorldY = height * game.TileSize;

                g2.drawImage(tiles[tileNum].image, WorldX, WorldY, game.TileSize, game.TileSize, null);
                height++;
            }
            height = 0;
            width++;
        }
    }
}
