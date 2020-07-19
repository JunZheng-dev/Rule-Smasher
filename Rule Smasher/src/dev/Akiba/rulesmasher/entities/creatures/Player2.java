package dev.Akiba.rulesmasher.entities.creatures;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.gfx.Animation;
import dev.Akiba.rulesmasher.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player2 extends Creature {

    private Animation animLeft, animRight;
    public static boolean facingRight;
    
    public Player2(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.handler = handler;

        bounds.x = (int) (16 * Creature.DEFAULT_CREATURE_WIDTH / 100);
        bounds.y = 0;
        bounds.width = (int) (67 * Creature.DEFAULT_CREATURE_HEIGHT / 100.0);
        bounds.height = Creature.DEFAULT_CREATURE_HEIGHT;
        facingRight = false;
        
        // Setting animations
        animLeft = new Animation(200, Assets.p2Left);
        animRight = new Animation(200, Assets.p2Right);
    }

    @Override
    public void tick() {
        // Animations
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        speedY += gravity;
        yMove = speedY;
        
        if (speedX != 0) {
            if (Math.abs(speedX) < friction) {
                speedX = 0;
            } else {
                if (speedX > 0) {
                    facingRight = true;
                    speedX -= friction;
                } else {
                    facingRight = false;
                    speedX += friction;
                }
            }
        }
        xMove = speedX;
        move();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
    }

    private void getInput() {
        xMove = 0;

        if (handler.getKeyManager().up && !jumped) {
            speedY = -jumpSpeed;
            jumped = true;
        }
        if (Math.abs(speedX) < terminalVelocity) {
            if (handler.getKeyManager().left) {
                 speedX -= accelerationX;
            }
            if (handler.getKeyManager().right) {
                speedX += accelerationX;
            }
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (facingRight) {
            return animRight.getCurrentFrame();
        } else {
            return animLeft.getCurrentFrame();
        }
    }
}
