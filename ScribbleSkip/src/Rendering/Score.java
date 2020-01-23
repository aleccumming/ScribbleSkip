package Rendering;

import Entities.Player;
import Utils.Managers.Fonts;

import java.awt.*;

public class Score{

    Camera camera;
    Player player;


    private int score;
    private static final int SCORE_Y = 100;
    private double scoreX;

    public Score() {
        score = 0;
    }

    public void tick(Camera camera) {
        score = -(int)camera.getY() / 180;
    }

    public void render(Graphics g) {
        Fonts.drawString(g, new Font("Segoe Print", Font.PLAIN, 48), Color.BLUE, "Score : " + String.valueOf(score), SCORE_Y);
    }


}
