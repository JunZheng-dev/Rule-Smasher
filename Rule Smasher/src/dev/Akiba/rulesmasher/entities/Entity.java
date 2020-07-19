package dev.Akiba.rulesmasher.entities;

import dev.Akiba.rulesmasher.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

    protected Handler handler;
    public float x;
    public float y;
    public int width, height;
    public Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
