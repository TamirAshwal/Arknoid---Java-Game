// Tamir Ashwal 209374867
package Arknoid.Listeners;
import Arknoid.Collidiable.Block;
import Arknoid.GameLevel;
import Arknoid.Shapes.Ball;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    /**
     * removing the block from thr game.
     * @param beingHit the block that was hit
     * @param hitter   the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);

    }
}
