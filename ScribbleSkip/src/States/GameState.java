package States;

import Entities.Entity;
import Entities.Player;
import Rendering.Background;
import Rendering.Camera;
import Rendering.Score;
import Rendering.Textures.Sprite;
import Rendering.Textures.SpriteSheet;
import Rendering.Textures.Texture;
import ScribbleSkipGame.Game;
import World.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState implements State {

    private Score score;


    private ArrayList<Entity> entities;
    private ArrayList<Tile> tiles;
    private static Background background;
    private Camera camera;
    private Player player;
    private double maxHeight;


    @Override
    public void init() {
        entities = new ArrayList<Entity>();
        tiles = new ArrayList<Tile>();
        Sprite bg = new Sprite("background");
        background = new Background(bg, 0, 0);
        Sprite sprite = new Sprite("maincharv6");
        player = new Player (sprite, Game.WIDTH / 2 - sprite.getWidth() / 2, Game.HEIGHT / 2 - sprite.getHeight() / 2, this);
        camera = new Camera(player);
        score = new Score();
        entities.add(player);
        float x = Game.WIDTH / 2 - 32;
        float y = Game.HEIGHT - 400;
        maxHeight = player.getY();
        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x + 100, y - 150, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x + 132, y - 230, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x - 50, y - 350, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x - 200, y - 520, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x - 30, y - 630, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x - 100, y - 700, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x, y - 740, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x + 170, y - 850, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        tiles.add(new Tile(x + 130, y - 1000, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
//        for(int i = 0; i < 10; i++) {
//            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1,1)));
//            y -= 100;
//            x += 50;
//        }
//        for(int i = 0; i < 10; i++) {
//            tiles.add(new Tile(i * 50, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1,1)));
//            y -= 100;
//
//        }

    }

    @Override
    public void enter() {

    }

    @Override
    public void tick(StateManager stateManager) {
        for (Entity e : entities)
            e.tick();
        background.update(camera);
        camera.tick();
        if (player.getY() + player.getHeight() >= camera.getY() + 960)
            this.init();

        if (maxHeight >= player.getY()){
            maxHeight = player.getY();
            platformGenerate();
        }
        score.tick(camera);
    }

    @Override
    public void exit() {
        entities.clear();
    }

    @Override
    public void render(Graphics2D g) {
        background.render(g, camera);
        for (Entity e : entities)
            e.render(g, camera);
        for (Tile t : tiles)
            t.render(g, camera);
        score.render(g);
    }

    @Override
    public String getName() {
        return "level1";
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public static Background getBackground() {
        return background;
    }

    public void platformGenerate() {
        int challenge = (int)player.getY() / -1000;
        Random chance = new Random();
        int chancenum = chance.nextInt(5 + challenge);
        Random rand = new Random();
        int num = rand.nextInt(Game.WIDTH - 64);
        if(chancenum == 1) {
            tiles.add(new Tile(num, (float)player.getY() - Game.HEIGHT, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
        }
        if(tiles.get(tiles.size() - 1).getHeight() - (player.getY() - Game.HEIGHT) > 250 ){
            tiles.add(new Tile(num, (float)player.getY() - Game.HEIGHT, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
            System.out.println("added saver tile");
        }
    }

}
