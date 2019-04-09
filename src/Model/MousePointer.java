package Model;

import java.awt.*;

public class MousePointer extends GameFigure {

    public final int SIZE = 10;

    public MousePointer(int x, int y){
        super(x, y);
    }


    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.CYAN);
        g2.drawLine((int)location.x - SIZE, (int)location.y, (int)location.x + SIZE, (int)location.y);
        g2.drawLine((int)location.x,(int)location.y - SIZE, (int)location.x, (int)location.y + SIZE);
    }

    @Override
    public void update() {
        // NA
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
