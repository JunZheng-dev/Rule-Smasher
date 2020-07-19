package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.gfx.Assets;

public class BrickTile extends Tile {
    
    public BrickTile(int id) {
        super(Assets.brick, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
