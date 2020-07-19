package dev.Akiba.rulesmasher.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, w, a, s, d, attack1, attack2;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        attack1 = keys[KeyEvent.VK_F];
        
        up = keys[KeyEvent.VK_UP];
        left = keys[KeyEvent.VK_LEFT];
        down = keys[KeyEvent.VK_DOWN];
        right = keys[KeyEvent.VK_RIGHT];
        attack2 = keys[KeyEvent.VK_SLASH];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
