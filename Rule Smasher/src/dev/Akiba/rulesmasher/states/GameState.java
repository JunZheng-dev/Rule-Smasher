package dev.Akiba.rulesmasher.states;

import dev.Akiba.rulesmasher.Handler;
import dev.Akiba.rulesmasher.display.Display;
import dev.Akiba.rulesmasher.entities.creatures.Player1;
import dev.Akiba.rulesmasher.entities.creatures.Player2;
import dev.Akiba.rulesmasher.entities.glove.Glove1;
import dev.Akiba.rulesmasher.entities.glove.Glove2;
import dev.Akiba.rulesmasher.overlay.HealthBar1;
import dev.Akiba.rulesmasher.overlay.HealthBar2;
import dev.Akiba.rulesmasher.tiles.Tile;
import dev.Akiba.rulesmasher.worlds.World;
import java.awt.Graphics;
import javax.swing.JLabel;

public class GameState extends State {

    private Player1 player1;
    private Player2 player2;
    private Glove1 glove1;
    private Glove2 glove2;
    private HealthBar1 healthBar1;
    private HealthBar2 healthBar2;
    private World world;
    public static boolean gameEnded;
    
    public GameState(Handler handler, String worldName) {
        super(handler);
        gameEnded = false;
        world = new World(handler, "res/worlds/" + worldName);
        handler.setWorld(world);
        player1 = new Player1(handler, world.getSpawn1X() * Tile.tileWidth,
                world.getSpawn1Y() * Tile.tileHeight);
        player2 = new Player2(handler, (world.getSpawn2X() - 1) * Tile.tileWidth,
                world.getSpawn2Y() * Tile.tileHeight);
        glove1 = new Glove1(handler, player1, player2);
        glove2 = new Glove2(handler, player1, player2);
        healthBar1 = new HealthBar1(handler, player1);
        healthBar2 = new HealthBar2(handler, player2);
    }
    
    @Override
    public void tick() {
        if (!gameEnded) {
            world.tick();
            player1.tick();
            player2.tick();
            glove1.tick();
            glove2.tick();
            healthBar1.tick();
            healthBar2.tick();
            if (player1.health == 0) {
                gameEnded = true;
            } else if (player2.health == 0) {
                gameEnded = true;
            } 
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player1.render(g);
        glove1.render(g);
        player2.render(g);
        glove2.render(g);
        healthBar1.render(g);
        healthBar2.render(g);
    }
}
