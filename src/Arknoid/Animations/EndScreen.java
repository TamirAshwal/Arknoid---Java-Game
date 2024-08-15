// Tamir Ashwal 209374867
package Arknoid.Animations;
import Arknoid.Listeners.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param k     the k
     */
    public EndScreen(Counter score, KeyboardSensor k) {
        this.score = score;
        this.stop = false;
        this.keyboard = k;
    }

    /**
     * executing the frame of end screen animation.
     * @param d the drawing surface
     */

    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 25);

    }
    /**
     * return if the animation should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;

    }
}
