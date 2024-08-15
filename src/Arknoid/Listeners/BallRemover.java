// Tamir Ashwal 209374867
package Arknoid.Listeners;
import Arknoid.Collidiable.Block;
import Arknoid.GameLevel;
import Arknoid.Shapes.Ball;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingballs;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingballs the remainingballs
     */
    public BallRemover(GameLevel game, Counter remainingballs) {
        this.game = game;
        this.remainingballs = remainingballs;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingballs;
    }

    /**
     * removing the ball from the game.
     * @param beingHit the being hit object
     * @param hitter   the ball
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingballs.decrease(1);
    }
}
