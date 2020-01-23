package Rendering;

import Rendering.Textures.Sprite;

import java.awt.*;

public class Background implements Renderable{

    private double bg1X, bg1Y, bg2X, bg2Y;
    private Sprite background1, background2;

    public Background(Sprite sprite, double x, double y) {
        this.background1 = sprite;
        this.background2 = new Sprite("background");
        this.bg1X = x;
        this.bg1Y = y;
        this.bg2X = x;
        this.bg2Y = y - 960;

    }

    public void render(Graphics2D g, Camera camera) {
        background1.render(g, bg1X - camera.getX(), bg1Y - camera.getY());
        background2.render(g, bg2X - camera.getX(), bg2Y - camera.getY());
    }


    public void update(Camera camera) {
        //System.out.printf("bg1Y = %f, bg2Y = %f, camera.y = %f\n", bg1Y, bg2Y, camera.getY());

        if (camera.getY() <= bg1Y - 960) {
            bg1Y -= 1920;
        }
        if (camera.getY() <= bg2Y - 960) {
            bg2Y -= 1920;
        }
    }

    public double getBg1X() {
        return bg1X;
    }

    public void setBg1X(double bg1X) {
        this.bg1X = bg1X;
    }

    public double getBg1Y() {
        return bg1Y;
    }

    public void setBg1Y(double bg1Y) {
        this.bg1Y = bg1Y;
    }

}
