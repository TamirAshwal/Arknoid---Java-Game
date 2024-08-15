// Tamir ashwal 209374867
package Arknoid;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Creating a new Sprite collection.
     */
    public SpriteCollection() {
        // creating arrary of sprites
        sprites = new ArrayList<>();
    }

    /**
     * Add sprite to the list.
     *
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        // add the sprite to the array list
        sprites.add(s);
    }

    /**
     * removing the block from the game.
     * @param s the block we want to remove
     */
    public void removeSpriteFromList(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all the object to change their position.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.sprites);
        // notifying the objects to change their position
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all the object on the draw surface.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.sprites);
        // notifying the objects to change their position
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}