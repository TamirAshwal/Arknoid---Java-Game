// Tamir Ashwal 209374867
package Arknoid.levels;
import Arknoid.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Background level 2.
 */
public class BackgroundLevel2 implements Sprite {
    /**
     * the background for level 2.
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        Color color = new Color(255, 255, 153);
        Color color1 = new Color(204, 204, 0);
        Color color2 = new Color(255, 255, 0);
        d.setColor(color1);
        for (int i = 0; i < 100; i++) {
            d.drawLine(120, 150, 80 + i * 7, 250);
        }
        d.setColor(color);
        d.fillCircle(120, 150, 50);
        d.setColor(color1);
        d.fillCircle(120, 150, 40);
        d.setColor(color2);
        d.fillCircle(120, 150, 30);

    }

    @Override
    public void timePassed() {
    }

}
