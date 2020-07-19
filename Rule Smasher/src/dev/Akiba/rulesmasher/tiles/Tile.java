package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.Handler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    // Static stuff
    public static Tile[] tiles = new Tile[256];
    public static Tile skyTile = new SkyTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile grassTile = new GrassTile(2);
    public static Tile brickTile = new BrickTile(3);
    public static Tile woodTile = new WoodTile(4);
    public static Tile cloudTile = new CloudTile(5);

    // Class
    public static int tileWidth, tileHeight;
    
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, tileWidth, tileHeight, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
