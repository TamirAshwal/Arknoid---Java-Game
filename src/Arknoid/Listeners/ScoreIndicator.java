// Tamir Ashwal 209374867
package Arknoid.Listeners;
import Arknoid.GameLevel;
import Arknoid.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel game;
    private Counter score;


    /**
     * Instantiates a new Score indicator.
     *
     * @param game  the game
     * @param score the score
     */
    public ScoreIndicator(GameLevel game, Counter score) {
        this.game = game;
        this.score = score;
    }

    /**
     * drawing the score indicator on the window.
     * @param d the drawing surface
     */


    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawRectangle(0, 0, 800, 20);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(400, 15, String.format("Score: %d", this.score.getValue()), 15);
    }

    /**
     * Adding the score indicator to the game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        // adding the score to the sprite collection
        g.addSprite(this);
    }

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    public void timePassed() {
    }
}
