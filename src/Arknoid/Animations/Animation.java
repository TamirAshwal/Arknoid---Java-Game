// Tamir Ashwal 209374867
package Arknoid.Animations;
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * executing one frame of the specific animation.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * return if the animation should stop.
     *
     * @return boolean if the animation should stop
     */
    boolean shouldStop();
}