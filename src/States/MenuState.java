package States;

import Input.KeyInput;
import Input.MouseInput;
import Rendering.Textures.UI.Button;
import ScribbleSkipGame.Game;
import Utils.Managers.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState implements State {

    private Button[] options;
    private int currentSelection;

    @Override
    public void init() {
        options = new Button[3];
        options[0] = new Button("Play", 200 + 0 * 80,
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
        options[1] = new Button("Options", 200 + 1 * 80,
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
        options[2] = new Button("Exit", 200 + 2 * 80,
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
    }

    @Override
    public void enter() {

    }

    @Override
    public void tick(StateManager stateManager){

        if(KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)){
            currentSelection--;
            if(currentSelection < 0) currentSelection = options.length - 1;
        }

        if(KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)){
            currentSelection++;
            if(currentSelection >= options.length) currentSelection = 0;
        }

        boolean clicked = false;
        for(int i = 0; i < options.length; i++) {
            if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))){
                currentSelection = i;
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }
        }

        if(clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
            select(stateManager);
        }

    }

    private void select(StateManager stateManager) {
        switch(currentSelection){
            case 0:
                stateManager.setState("level1");
                break;
            case 1:
                System.out.println("Options");
                break;
            case 2:
                System.out.println("Exit");
                Game.INSTANCE.stop();
                break;
        }
    }


    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        Fonts.drawString(g, new Font("Segoe Print", Font.BOLD, 72), Color.RED, Game.TITLE, 100);

        for (int i = 0; i < options.length; i++){
            if(i == currentSelection)
                options[i].setSelected(true);
            else options[i].setSelected(false);

            options[i].render(g);
        }
    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return "menu";
    }

}
