package dev.Akiba.rulesmasher.overlay;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.entities.creatures.Player2;
import java.awt.Color;
import java.awt.Graphics;

public class HealthBar2 {
    
    private Player2 p2;
    private Handler handler;
    private float percentage;
    private float maxHealth;
    private int borderLength, healthHeight, healthWidth, offset;
    
    public HealthBar2(Handler handler, Player2 p2) {
        this.p2 = p2;
        this.handler = handler;
        maxHealth = p2.MAX_HEALTH;
        healthHeight = (int) (handler.getHeight() / 20.0);
        healthWidth = (int) (handler.getWidth() / 4.0);
        borderLength = (int) (healthHeight / 8.0);
        offset = 10;
    }
    
    public void tick() {
        percentage = p2.health / maxHealth;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(handler.getWidth() - healthWidth - offset - borderLength * 2,
                offset, healthWidth + borderLength * 2, healthHeight + borderLength * 2);
        g.setColor(Color.GRAY);
        g.fillRect(handler.getWidth() - healthWidth - offset - borderLength,
                offset + borderLength, healthWidth, healthHeight);
        g.setColor(new Color(255, 70, 100));
        g.fillRect(handler.getWidth() - healthWidth - offset - borderLength,
                offset + borderLength, (int) (percentage * healthWidth), healthHeight);
    }
}
