package Entities;

import Rendering.Background;
import Rendering.Textures.Sprite;
import ScribbleSkipGame.Game;
import States.GameState;
import World.Tile;

public abstract class Mob extends Entity {

    protected double dx, dy;
    protected double maxDY;
    protected double gravity;
    protected boolean falling = true;
    protected boolean canJump = false;
    private GameState state;

    private double yLimit = Game.HEIGHT / 2;

    private static Background background1 = GameState.getBackground();


    public Mob(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y);
        gravity = 0.6;
        maxDY = 9;
        this.state = state;
    }

    @Override
    public void tick() {
        move();
        fall();
    }

    public void move(){
        x += dx;
        if (!hasVerticalCollision())
            y += dy;
    }

    protected boolean hasVerticalCollision() {
        for(int i = 0; i < state.getTiles().size(); i++){
            Tile t = state.getTiles().get(i);
            if(getBottom().intersects(t.getTop()) && dy > 0) {
                canJump = true;
                falling = false;
                dy = 0;
                jump(18);
                return true;
            } else {
                falling = true;
            }
        }
        return false;
    }

    protected void fall() {
        if (falling) {
            dy += gravity;
            canJump = false;
            if (dy > maxDY)
                dy = maxDY;
        }
    }

    protected void jump(double jumpHeight) {
        if(canJump) {
            dy -= jumpHeight;
            falling = false;
            canJump = false;
        }
    }
}
