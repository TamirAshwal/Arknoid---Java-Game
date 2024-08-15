// Tamir Ashwal 209374867
package Arknoid.levels;
import java.awt.Color;
import Arknoid.Sprite;
import biuoop.DrawSurface;

/**
 * The type Background level 1.
 */
public class BackgroundLevel1 implements Sprite {
    /**
     * the background for level 1.
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(20, 40, 760, 540);
        d.setColor(Color.blue);
        d.drawCircle(400, 225, 60);
        d.drawCircle(400, 225, 80);
        d.drawCircle(400, 225, 100);
        d.drawLine(400, 195, 400, 45);
        d.drawLine(400, 255, 400, 405);
        d.drawLine(400, 195, 400, 45);
        d.drawLine(370, 225, 225, 225);
        d.drawLine(430, 225, 575, 225);
    }
    @Override
    public void timePassed() {
    }
}
