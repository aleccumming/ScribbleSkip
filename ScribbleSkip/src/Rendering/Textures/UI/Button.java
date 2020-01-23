package Rendering.Textures.UI;

import ScribbleSkipGame.Game;
import Utils.Managers.Fonts;

import java.awt.*;

public class Button extends Rectangle {

    private Font font, selectedFont;
    private Color color, selectedColor;
    private boolean selected;
    private String text;
    private int textY;

    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
        this.text = text;
        this.textY = textY;
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(Graphics g) {
        if(selected)
            Fonts.drawString(g, selectedFont, selectedColor, text, textY);
        else
            Fonts.drawString(g, font, color, text, textY);

        FontMetrics fm = g.getFontMetrics();
        this.x = (Game.WIDTH - fm.stringWidth(text)) / 2;
        this.y = textY - fm.getHeight();
        this.width = fm.stringWidth(text);
        this.height = fm.getHeight();
        //g.drawRect(x, y, width, height);
    }
}
