package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.gfx.Assets;

public class GrassTile extends Tile {
    
    public GrassTile(int id) {
        super(Assets.grass, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
