package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.gfx.Assets;

public class SkyTile extends Tile {
    
    public SkyTile(int id) {
        super(Assets.sky, id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
}