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

public class Glove1 extends Entity {

    private Player1 p1;
    private Player2 p2;
    private static final int DEFAULT_GLOVE_WIDTH = Creature.DEFAULT_CREATURE_WIDTH,
                             DEFAULT_GLOVE_HEIGHT = Creature.DEFAULT_CREATURE_HEIGHT;
    private final float DEFAULT_GLOVE_SPEED;
    private final float RELOAD_TIME;
    private int bulletDamage;
    private boolean attacking = false;
    private int lastX, lastY;
    private int gloveBoundX1, gloveBoundX2, gloveBoundY1, gloveBoundY2; // Actual boundaries of glove
    private int x1, y1, x2, y2; // Actual boundaries of p2
    private boolean lastFacedRight;
    private long startTime;
    private float damageMultiplier = 1f, knockMultiplier = 1.5f;
    
    public Glove1(Handler handler, Player1 p1, Player2 p2) {
        super(handler, p1.x, p1.y, DEFAULT_GLOVE_WIDTH, DEFAULT_GLOVE_HEIGHT);
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
        if (handler.getKeyManager().attack1) {
            attacking = true;
            y = lastY;  // Needed to see what location they attacked at
            x = lastX;
            startTime = System.currentTimeMillis();
        }
    }

    private boolean isIn(int x, int y, int x1, int y1, int x2, int y2) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }
    
    public void hit(Boolean facingRight) {
        y = handler.getHeight() + bounds.height;
        p2.health -= 10 * damageMultiplier;
        p2.speedX = (facingRight ? 1 : -1) * Creature.terminalVelocity * knockMultiplier;
        p2.speedY = -p2.jumpSpeed / 3.0f;
    }
    
    @Override
    public void tick() {
        if (attacking) {   
            gloveBoundX1 = (int) x + bounds.x;
            gloveBoundX2 = gloveBoundX1 + bounds.width;
            gloveBoundY1 = (int) y + bounds.y;
            gloveBoundY2 = gloveBoundY1 + bounds.height;
            x1 = (int) p2.x + p2.bounds.x;
            x2 = x1 + p2.bounds.width;
            y1 = (int) p2.y + p2.bounds.y;
            y2 = y1 + p2.bounds.height;
            
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
        } else {            // Else, carry on and render glove depending on face
            getInput(); 
            x = p1.x + (p1.facingRight ? 1 : -1) * p1.bounds.x + 
                     (p1.facingRight ? 1 : -1) * p1.bounds.width;
            y = p1.y;
            lastX = (int) x;
            lastY = (int) y;
            if (p1.facingRight) {
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
        g.drawImage(lastFacedRight ? Assets.blueGloveRight : Assets.blueGloveLeft,
                (int) x, (int) y, DEFAULT_GLOVE_WIDTH, DEFAULT_GLOVE_WIDTH, null);
    }
}
