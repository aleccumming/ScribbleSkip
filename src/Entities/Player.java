package Entities;

import Input.KeyInput;
import Rendering.Camera;
import Rendering.Textures.Sprite;
import ScribbleSkipGame.Game;
import States.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Mob{

    public Player(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);
    }

    @Override
    public void tick() {
        //if(KeyInput.isDown(KeyEvent.VK_W)) jump(10);
        // if(KeyInput.isDown(KeyEvent.VK_S)) dy = 5;
        if(KeyInput.isDown(KeyEvent.VK_D) || KeyInput.isDown(KeyEvent.VK_RIGHT)) dx = 5;
        if(KeyInput.isDown(KeyEvent.VK_A) || KeyInput.isDown(KeyEvent.VK_LEFT)) dx = -5;

        // if(KeyInput.wasReleased(KeyEvent.VK_W) || KeyInput.wasReleased(KeyEvent.VK_S)) dy = 0;
        if(KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D) ||
                (KeyInput.wasReleased(KeyEvent.VK_RIGHT) || KeyInput.wasReleased(KeyEvent.VK_LEFT))) dx = 0;
        if(x <= 0 - sprite.getWidth() / 2)
            x = Game.WIDTH - sprite.getWidth() / 2;
        if(x > Game.WIDTH - sprite.getWidth() / 2)
            x = 0 - sprite.getWidth() / 2;
        super.tick();
    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        sprite.render(g, x - camera.getX(), y - camera.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDX() {
        return dx;
    }

    public double getDY() {
        return dy;
    }

    public double getHeight() {
        return sprite.getHeight();
    }
}
