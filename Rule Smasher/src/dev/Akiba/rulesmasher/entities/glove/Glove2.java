package dev.Akiba.rulesmasher.entities.glove;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.entities.Entity;
import dev.Akiba.rulesmasher.entities.creatures.Creature;
import dev.Akiba.rulesmasher.entities.creatures.Player1;
import dev.Akiba.rulesmasher.entities.creatures.Player2;
import dev.Akiba.rulesmasher.gfx.Assets;
import dev.Akiba.rulesmasher.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Glove2 extends Entity {

    private Player1 p1;
    private Player2 p2;
    private static final int DEFAULT_GLOVE_WIDTH = (int) (Tile.tileWidth * 1.8),
                             DEFAULT_GLOVE_HEIGHT = (int) (Tile.tileHeight * 1.8);
    private final float RELOAD_TIME;
    private final float DEFAULT_GLOVE_SPEED;
    private int bulletDamage;
    private boolean attacking = false;
    private int lastX, lastY;
    private int gloveBoundX1, gloveBoundX2, gloveBoundY1, gloveBoundY2; // Actual boundaries of glove
    private int x1, y1, x2, y2; // Actual boundaries of p1
    private boolean lastFacedRight;
    private long startTime;
    private float damageMultiplier = 1f, knockMultiplier = 1.5f;
    
    public Glove2(Handler handler, Player1 p1, Player2 p2) {
        super(handler, p2.x, p2.y, DEFAULT_GLOVE_WIDTH, DEFAULT_GLOVE_HEIGHT);
        this.DEFAULT_GLOVE_SPEED = Tile.tileWidth / 2f;
        this.RELOAD_TIME = 1.5f;
        this.p1 = p1;
        this.p2 = p2;
        
        bulletDamage = 10;
        
        bounds.y = (int) (DEFAULT_GLOVE_HEIGHT / 2.0);
        bounds.width = (int) (DEFAULT_GLOVE_WIDTH * 3.0 / 8.0);
        bounds.height = (int) (DEFAULT_GLOVE_HEIGHT / 3.5);
    }
    
    private void getInput() {
        if (handler.getKeyManager().attack2) {
            attacking = true;
            y = lastY;
            x = lastX;
            startTime = System.currentTimeMillis();
        }
    }
    
    private boolean isIn(int x, int y, int x1, int y1, int x2, int y2) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }
    
    public void hit(Boolean facingRight) {
        y = handler.getHeight() + bounds.height;
        p1.health -= 10 * damageMultiplier;;
        p1.speedX = (facingRight ? 1 : -1) * Creature.terminalVelocity * knockMultiplier;
        p1.speedY = -p1.jumpSpeed / 3;
    }
    
    @Override
    public void tick() {
            if (attacking) {   
            gloveBoundX1 = (int) x + bounds.x;
            gloveBoundX2 = gloveBoundX1 + bounds.width;
            gloveBoundY1 = (int) y + bounds.y;
            gloveBoundY2 = gloveBoundY1 + bounds.height;
            x1 = (int) p1.x + p1.bounds.x;
            x2 = x1 + p1.bounds.width;
            y1 = (int) p1.y + p1.bounds.y;
            y2 = y1 + p1.bounds.height;
            
            if (lastFacedRight) {
                if (isIn(gloveBoundX2, gloveBoundY1, x1, y1, x2, y2) || 
                        isIn(gloveBoundX2, gloveBoundY2, x1, y1, x2, y2)) {
                    hit(lastFacedRight);
                }
                x += DEFAULT_GLOVE_SPEED;   // Move glove right
            } else {
                if (isIn(gloveBoundX1, gloveBoundY1, x1, y1, x2, y2) || 
                        isIn(gloveBoundX1, gloveBoundY2, x1, y1, x2, y2)) {
                    hit(lastFacedRight);
                }
                x-= DEFAULT_GLOVE_SPEED;    // Move glove left
            }
            
            if ((System.currentTimeMillis() - startTime) / 1000.0f > RELOAD_TIME) {
                attacking = false;
            }   
        } else {
            getInput();
            x = p2.x + (p2.facingRight ? 1 : -1) * p2.bounds.x + 
                     (p2.facingRight ? 1 : -1) * p2.bounds.width;
            y = p2.y;
            lastX = (int) x;
            lastY = (int) y;
            if (p2.facingRight) {
                bounds.x = 0;
                lastFacedRight = true;
            } else {
                bounds.x = (int) (DEFAULT_GLOVE_WIDTH * 2.0 / 3.0);
                lastFacedRight = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(lastFacedRight ? Assets.redGloveRight : Assets.redGloveLeft,
                (int) x, (int) y, DEFAULT_GLOVE_WIDTH, DEFAULT_GLOVE_WIDTH, null);
    }
}
