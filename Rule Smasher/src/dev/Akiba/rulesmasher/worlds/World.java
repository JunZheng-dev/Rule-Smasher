package dev.Akiba.rulesmasher.worlds;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.tiles.Tile;
import dev.Akiba.rulesmasher.utils.Utils;
import java.awt.Graphics;

public class World {

    private int width, height;
    private int spawn1X, spawn1Y, spawn2X, spawn2Y;
    private int[][] tiles;
    private static Handler handler;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Tile.tileWidth, y * Tile.tileHeight);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {;
            return Tile.skyTile;
        }
        
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.cloudTile;
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        // read the width and height of map
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawn1X = Utils.parseInt(tokens[2]);
        spawn1Y = Utils.parseInt(tokens[3]);
        spawn2X = Utils.parseInt(tokens[4]);
        spawn2Y = Utils.parseInt(tokens[5]);

        Tile.tileWidth = (int) (handler.getWidth() / width);
        Tile.tileHeight = (int) (handler.getHeight() / height);
        
        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[x + y * width + 6]);
            }
        }
    }

    public int getSpawn1X() {
        return spawn1X;
    }

    public int getSpawn1Y() {
        return spawn1Y;
    }

    public int getSpawn2X() {
        return spawn2X;
    }

    public int getSpawn2Y() {
        return spawn2Y;
    }
}
