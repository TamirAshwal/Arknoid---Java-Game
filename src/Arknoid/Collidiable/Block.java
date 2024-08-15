// Tamir ashwal 209374867
package Arknoid.Collidiable;

import Arknoid.GameLevel;
import Arknoid.Listeners.HitListener;
import Arknoid.Listeners.HitNotifier;
import Arknoid.Shapes.Ball;
import Arknoid.Shapes.Point;
import Arknoid.Shapes.Rectangle;
import Arknoid.Shapes.Velocity;
import Arknoid.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        // constructor
        hitListeners = new ArrayList<>();

        setRectangle(rectangle, color);
    }

    /**
     * Sets rectangle of the block.
     *
     * @param rectangle the rectangle that represents the block
     * @param color     the color of the block
     */
    public void setRectangle(Rectangle rectangle, Color color) {
        // setting the rectangle of the block
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * return the rectangle.
     *
     * @return Rectangle
     */

    public Rectangle getCollisionRectangle() {
        // returning thr rectangle when the collision occurs;
        return this.rectangle;
    }

    /**
     * checks where the hit occured on the object and return the velocity
     * accordingly.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball
     * @return the new velocity after the hit.
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = new Velocity(currentVelocity.getP1());
        /* if the ball hits the top or the bottom of a block we change it
         * vertical velocity
         */
        if (rectangle.getTop().isPointOnLine(collisionPoint)
                || rectangle.getBottom().isPointOnLine(collisionPoint)) {
            velocity.setDy(currentVelocity.getDy() * -1);
        }
        /* if the ball hits the left or the block of a block we change it
         * horizontal velocity
         */
        if (rectangle.getLeft().isPointOnLine(collisionPoint)
                || rectangle.getRight().isPointOnLine(collisionPoint)) {
            velocity.setDx(currentVelocity.getDx() * -1);
        }
        this.notifyHit(hitter);
        // returning the new velocity
        return velocity;
    }

    /**
     * the method draw the block on the draw surface.
     *
     * @param surface the drawing surface
     */

    public void drawOn(DrawSurface surface) {
        // coloring the edges of the rectangle's block
        // coloring the block
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getTopLeft().getX(),
                (int) this.rectangle.getTopLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getTopLeft().getX(),
                (int) this.rectangle.getTopLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * notify the object that the time is passed.
     */
    public void timePassed() {
    }

    /**
     * Add the block to game.
     *
     * @param g the game returning void
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removing the block from the game.
     *
     * @param game the game we remove the block from
     */

    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * adding a hit listener to the list.
     *
     * @param hl the hit listener
     */

    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removing a hit listener to the list.
     *
     * @param hl the hit listener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
