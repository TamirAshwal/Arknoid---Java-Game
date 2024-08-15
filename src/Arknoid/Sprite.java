// Tamir ashwal 209374867
package Arknoid;
import biuoop.DrawSurface;

/**
 * The sprite interface is the interface of all the visual objects in the game.
 */
public interface Sprite {
    /**
     * Draw the object on the screen.
     *
     * @param d the drawing surface
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();
}