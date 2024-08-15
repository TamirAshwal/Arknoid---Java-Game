// Tamir Ashwal 209374867
package Arknoid.levels;
import Arknoid.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Background level 3.
 */
public class BackgroundLevel3 implements Sprite {
    /**
     * the background for level 3.
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        Color color = new Color(0, 125, 0);
        d.setColor(color);
        d.fillRectangle(20, 40, 760, 580);
        d.setColor(Color.WHITE);
        d.fillRectangle(70, 400, 100, 230);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(100, 320, 40, 80);
        d.setColor(Color.gray);
        d.fillRectangle(115, 150, 10, 170);
        Color color1 = new Color(255, 169, 152);
        Color color2 = new Color(255, 71, 71);
        d.setColor(color1);
        d.fillCircle(120, 150, 10);
        d.setColor(color2);
        d.fillCircle(120, 150, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(120, 150, 3);
        Color color3 = new Color(32, 32, 32);
        d.setColor(color3);
        d.fillRectangle(60, 400, 10, 180);
        d.fillRectangle(170, 400, 10, 180);
        d.fillRectangle(70, 400, 100, 10);
        d.fillRectangle(85, 400, 5, 180);
        d.fillRectangle(107, 400, 5, 180);
        d.fillRectangle(127, 400, 5, 180);
        d.fillRectangle(149, 400, 5, 180);
        d.fillRectangle(70, 435, 100, 8);
        d.fillRectangle(70, 470, 100, 8);
        d.fillRectangle(70, 505, 100, 8);
        d.fillRectangle(70, 540, 100, 8);


    }

    @Override
    public void timePassed() {
    }
}
