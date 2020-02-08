package Entities;

import Rendering.Camera;
import Rendering.Renderable;
import Rendering.Textures.Sprite;

import java.awt.*;

public abstract class Entity implements Renderable{

    protected double x, y;
    protected Sprite sprite;

    public Entity(Sprite sprite, double x, double y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g, Camera camera);

//    public void render(Graphics2D g, Camera camera){
//        sprite.render(g, x - camera.getX(), y - camera.getY());
//        g.setColor(Color.RED);
//        g.draw(getTop());
//        g.setColor(Color.BLUE);
//        g.draw(getBottom());
//        g.setColor(Color.MAGENTA);
//        g.draw(getLeft());
//        g.setColor(Color.ORANGE);
//        g.draw(getRight());
//    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
    }

    public Rectangle getTop() {
        return new Rectangle((int)x + 10, (int)y + 6,
                sprite.getWidth() - 25,4);
    }

    public Rectangle getBottom() {
        return new Rectangle((int)x + 20, (int)y + sprite.getHeight() - 4,
                sprite.getWidth() - 30,6);
    }

    public Rectangle getRight() {
        return new Rectangle((int)x + sprite.getWidth() - 20, (int)y + 6,
                4,sprite.getHeight() - 6);
    }

    public Rectangle getLeft() {
        return new Rectangle((int)x + 10, (int)y + 6,
                4,sprite.getHeight() - 6);
    }

}
