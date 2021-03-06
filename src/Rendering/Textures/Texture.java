package Rendering.Textures;

import Utils.Managers.TextureManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Texture {

    private final static Map<String, TextureManager> textMap = new HashMap<String, TextureManager>();
    private TextureManager manager;
    private String fileName;


    public Texture(String fileName) {
        this.fileName = fileName;
        TextureManager oldTexture = textMap.get(fileName);
        if (oldTexture != null) {
            manager = oldTexture;
            manager.addReference();
        }
        else {
            try {
                System.out.println("Loading texture: " + fileName);
                manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
                textMap.put(fileName, manager);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if(manager.removeReference() && !fileName.isEmpty()) {
            textMap.remove(fileName);
        }
        super.finalize();
    }

    public void render(Graphics g, double x, double y){
        g.drawImage(manager.getImage(), (int) x, (int) y, null);
    }

    public BufferedImage getImage() {
        return manager.getImage();
    }
}
