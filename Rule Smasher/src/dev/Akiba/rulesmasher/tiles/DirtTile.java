package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.gfx.Assets;

public class DirtTile extends Tile {
    
    public DirtTile(int id) {
        super(Assets.dirt, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
