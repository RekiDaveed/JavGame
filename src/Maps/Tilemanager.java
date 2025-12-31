package Maps;

import UI.Game;

public class Tilemanager {
    Game game;
    Tile[] tiles;

    public Tilemanager(Game game) {
        this.game = game;

        Tile[] tiles = new Tile[10];
        GettileImage();
    }

    public void GettileImage(){

    }
}
