package dev.Akiba.rulesmasher.overlay;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.entities.creatures.Player1;
import java.awt.Color;
import java.awt.Graphics;

public class HealthBar1 {
    
    private Player1 p1;
    private Handler handler;
    private float percentage;
    private float maxHealth;
    private int borderLength, healthHeight, healthWidth, offset;
    
    public HealthBar1(Handler handler, Player1 p1) {
        this.p1 = p1;
        this.handler = handler;
        maxHealth = p1.MAX_HEALTH;
        healthHeight = (int) (handler.getHeight() / 20.0);
        healthWidth = (int) (handler.getWidth() / 4.0);
        borderLength = (int) (healthHeight / 8.0);
        offset = 10;
    }
    
    public void tick() {
        percentage = p1.health / maxHealth;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(offset, offset, healthWidth + borderLength * 2,
                healthHeight + borderLength * 2);
        g.setColor(Color.GRAY);
        g.fillRect(offset + borderLength, offset + borderLength, 
                healthWidth, healthHeight);
        g.setColor(new Color(70, 160, 255));
        g.fillRect(offset + borderLength, offset+ borderLength, 
                (int) (percentage * healthWidth), healthHeight);
    }
}
