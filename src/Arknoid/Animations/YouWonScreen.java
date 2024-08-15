// Tamir Ashwal 209374867
package Arknoid.Animations;
import Arknoid.Listeners.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The type You won screen.
 */
public class YouWonScreen implements Animation {
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;


    /**
     * Instantiates a new You won screen.
     *
     * @param score the score
     * @param k     the key to stop the animation
     */
    public YouWonScreen(Counter score, KeyboardSensor k) {
        this.score = score;
        this.stop = false;
        this.keyboard = k;
    }

    /**
     * executing the frame of end screen animation.
     *
     * @param d the drawing surface
     */

    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 25);

    }

    /**
     * return if the animation should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
