package dev.Akiba.rulesmasher.tiles;

import dev.Akiba.rulesmasher.gfx.Assets;

public class CloudTile extends Tile {
    
    public CloudTile(int id) {
        super(Assets.cloud, id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
}
