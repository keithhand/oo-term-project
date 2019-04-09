package Model.FakeButton;

import Model.GameFigure;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FakeButton extends GameFigure {

    String text;
    Color color;
    Font font;
    public Rectangle2D.Float boxButton;
    public final int BASE_SIZE = 20;

    public FakeButton(String text, int x, int y, Color color, Font font){
        super(x, y);
        this.text = text;
        this.color = color;
        this.font = font;

        boxButton = new Rectangle2D.Float(x - BASE_SIZE/2, y - 40, 130, 40);
    }

    @Override
    public void render(Graphics2D g2) {
        // rendering text
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, (int) location.x, (int) location.y);

        // rendering hidden button
        g2.setStroke(new BasicStroke(1));
        g2.draw(boxButton);
    }

    @Override
    public void update() {
        System.out.println("Test");
    }

    @Override
    public int getCollisionRadius() {
        return (int)(BASE_SIZE/2*0.75);
    }
}
