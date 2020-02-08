package Rendering;

import Entities.Player;
import ScribbleSkipGame.Game;

public class Camera {

    private double x;
    private double y;

    private Player player;

    private static final double Y_LIMIT = Game.HEIGHT / 2;


    public Camera(Player player) {
        this.player = player;
        this.x = 0;
        this.y = 0;
    }

    public void tick() {
        // If the player is above some Y, move the camera to follow him
        if (player.getY() - y < Y_LIMIT){
            y = player.getY() - Y_LIMIT;
        }
        //y -= 1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
