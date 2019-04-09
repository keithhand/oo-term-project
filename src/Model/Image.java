package Model;

import Controller.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends GameFigure {

    private BufferedImage image;

    public Image(String fileLocation, int x, int y){
        super(x,y);
        try{
            image = ImageIO.read(new File(fileLocation));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, (int)location.x, (int)location.y, null);
    }

    @Override
    public void update() {

    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
