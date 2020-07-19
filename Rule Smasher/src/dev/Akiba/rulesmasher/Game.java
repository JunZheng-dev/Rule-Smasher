package dev.Akiba.rulesmasher;

import dev.Akiba.rulesmasher.states.GameState;
import dev.Akiba.rulesmasher.states.State;
import dev.Akiba.rulesmasher.display.Display;
import dev.Akiba.rulesmasher.gfx.Assets;
import dev.Akiba.rulesmasher.input.KeyManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    // Window
    private Display display;
    private int width, height;
    public String title;

    // Threads
    private Thread thread;
    private boolean running = false;

    // Graphics
    private BufferStrategy bs;
    private Graphics g;

    // States
    private State gameState;

    // Input
    private KeyManager keyManager;

    // Handler
    private Handler handler;

    // Other
    private String worldName;
    
    public Game(String title, String worldName, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.worldName = worldName;
        keyManager = new KeyManager();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps; // Divided into 1 bil (nano-second)
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();
    }

    private void init() {
        Assets.init();
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        handler = new Handler(this);

        gameState = new GameState(handler, worldName);
        State.setState(gameState);
    }

    private void tick() {
        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
        
        if (GameState.gameEnded) {
            gameState = new GameState(handler, worldName);
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //start drawing
        if (State.getState() != null) {
            State.getState().render(g);
        }
        //end drawing
        bs.show();
        g.dispose();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // GETTERS AND SETTERS
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
