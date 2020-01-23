package ScribbleSkipGame;

import Input.KeyInput;
import Input.MouseInput;
import States.GameState;
import States.MenuState;
import States.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final String TITLE = "Scribble Skip";
    public static final int WIDTH = 540;
    public static final int HEIGHT = WIDTH/ 9 * 16;

    private boolean alive;

    private StateManager stateManager;

    public static Game INSTANCE;

    public Game() {

        addKeyListener(new KeyInput());
        MouseInput mi = new MouseInput();
        addMouseListener(mi);
        addMouseMotionListener(mi);
        stateManager = new StateManager();

        stateManager.addState(new MenuState());
        stateManager.addState(new GameState());

        INSTANCE = this;
    }

    private void tick(){
        stateManager.tick();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-6, -28);
        //////////////////////////


        stateManager.render(g2d);

        //////////////////////////
        g.dispose();
        bs.show();
    }

    protected void start() {
        if (alive) return;
        alive = true;
        new Thread(this, "Game_Thread").start();
    }

    public void stop() {
        if(!alive) return;
        alive = false;
    }

    @Override
    public void run() {
        requestFocus();
        double target = 60.0;
        double nsPerTick = 1000000000.0 / target;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double unprocessed = 0.0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while(alive) {
            long now = System.nanoTime();
            unprocessed += (now - startTime) / nsPerTick;
            startTime = now;

            if (unprocessed >= 1.0){
                tick();
                KeyInput.update();
                MouseInput.update();
                unprocessed--;
                tps++;
                canRender = true;
            }
            else canRender = false;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender){
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                //System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }
        }
        System.exit(0);
    }

}
