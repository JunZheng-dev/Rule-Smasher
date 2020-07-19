package dev.Akiba.rulesmasher.entities.creatures;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.entities.Entity;
import dev.Akiba.rulesmasher.tiles.Tile;

public abstract class Creature extends Entity {

    // Character Dynamics
    public static float gravity;
    public static float accelerationX;
    public static float friction;
    public static float jumpSpeed;
    public static float terminalVelocity;

    public static final int MAX_HEALTH = 100;
    public static final int DEFAULT_CREATURE_WIDTH = (int) (Tile.tileWidth * 1.8),
                            DEFAULT_CREATURE_HEIGHT = (int) (Tile.tileHeight * 1.8);
    protected boolean jumped;
    public int health;
    public float speedX, speedY;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = MAX_HEALTH;
        jumpSpeed = handler.getHeight() / 48f;
        gravity = handler.getHeight() / 1200f;
        accelerationX = handler.getWidth() / 2600f;
        friction = handler.getWidth() / 6000f;
        terminalVelocity = handler.getWidth() / 180f;
        
        jumped = false;
    }

    public void move() {
        jumped = true;
        moveX();
        moveY();

    }

    public void moveX() {
        if (xMove > 0) {         // Move right
            // Collision with terrain
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.tileWidth;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.tileHeight)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height / 2) / Tile.tileHeight)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight)) {
                x += xMove;
            } else {
                speedX = 0;
                x = tx * Tile.tileWidth - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // Move left
            // Collision with terrain
            int tx = (int) (x + xMove + bounds.x) / Tile.tileWidth;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.tileHeight)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height / 2) / Tile.tileHeight)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight)) {
                x += xMove;
            } else {
                speedX = 0;
                x = tx * Tile.tileWidth + Tile.tileWidth - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {        // Move up
            // Collision with terrain
            int ty = (int) (y + yMove + bounds.y) / Tile.tileHeight;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.tileWidth, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.tileHeight + Tile.tileHeight - bounds.y;
                speedY = 0;
            }
        } else if (yMove > 0) { // Move down
            // Falling out of map
            if (y > handler.getHeight()) {
                health = 0;
            }

            // Collision with terrain
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.tileHeight;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.tileWidth, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.tileHeight - bounds.y - bounds.height - 1;
                speedY = 0;
                jumped = false;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // ALL GETTERS AND SETTERS
    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getxMove() {
        return xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
