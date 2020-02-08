package World;

import Entities.Entity;
import Rendering.Camera;
import Rendering.Textures.Sprite;

import java.awt.*;

public class Tile extends Entity{

    protected boolean solid;

    public Tile(float x, float y, Sprite sprite) {
        super(sprite, x, y);
        this.solid = true;
    }

//    public void render(Graphics2D g) {
//        sprite.render(g, x, y);
//        g.setColor(Color.RED);
//        g.draw(getTop());
//        g.setColor(Color.BLUE);
//        g.draw(getBottom());
//        g.setColor(Color.MAGENTA);
//        g.draw(getLeft());
//        g.setColor(Color.ORANGE);
//        g.draw(getRight());
//    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        sprite.render(g, x - camera.getX(), y - camera.getY());
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, sprite.getWidth(),
                sprite.getHeight());
    }

    public Rectangle getTop() {
        return new Rectangle((int)x + 6, (int)y + 52,
                sprite.getWidth() - 8,4);
    }

    public Rectangle getBottom() {
        return new Rectangle((int)x + 6, (int)y + sprite.getHeight() - 4,
                sprite.getWidth() - 6,4);
    }

    public Rectangle getRight() {
        return new Rectangle((int)x + sprite.getWidth() - 4, (int)y + 52,
                4,sprite.getHeight() - 52);
    }

    public Rectangle getLeft() {
        return new Rectangle((int)x, (int)y + 52,
                4,sprite.getHeight() - 52);
    }

    public double getHeight(){
        return y;
    }


}
