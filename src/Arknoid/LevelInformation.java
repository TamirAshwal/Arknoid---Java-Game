// Tamir Ashwal 209384867
package Arknoid;
import Arknoid.Collidiable.Block;
import Arknoid.Shapes.Velocity;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * returns the number of balls in the level.
     *
     * @return int number of balls
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * creating a list of the velocities of the balls and returning the list.
     *
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * the speed of the paddle.
     *
     * @return int the speed of the paddle
     */
    int paddleSpeed();

    /**
     * the width of the paddle.
     *
     * @return int the width of the paddle
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.

    /**
     * string representing the level.
     *
     * @return string the name of the level
     */
    String levelName();

    // Returns a sprite with the background of the level

    /**
     * creating the background of the specific level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * the block of the specific level.
     *
     * @return the blocks
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * the number of blocks in the game.
     *
     * @return int the number of blocks
     */
    int numberOfBlocksToRemove();

    /**
     * the number of the level.
     *
     * @return the level number
     */
    int getLevelNumber();
}
