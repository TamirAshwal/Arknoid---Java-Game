// Tamir Ashwal 209374867
package Arknoid.Listeners;
import Arknoid.Collidiable.Block;
import Arknoid.Shapes.Ball;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the being hit object
     * @param hitter   the ball
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}