// Tamir Ashwal 209374867
package Arknoid.levels;
import Arknoid.Collidiable.Block;
import Arknoid.LevelInformation;
import Arknoid.Shapes.Point;
import Arknoid.Shapes.Rectangle;
import Arknoid.Shapes.Velocity;
import Arknoid.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 2;
    private static final int NUMBER_OF_BLOCKS = 40;
    private static final int LEVEL_NUMBER = 3;

    /**
     * returns the number of balls in the level.
     *
     * @return int number of balls
     */
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * getting the level number.
     * @return returning the level number
     */

    public int getLevelNumber() {
        return LEVEL_NUMBER;
    }

    /**
     * creating a list of the velocities of the balls and returning the list.
     *
     * @return list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>(NUMBER_OF_BALLS);
        Velocity velocity = new Velocity(5, -3);
        Velocity velocity1 = new Velocity(-5, -3);
        velocities.add(velocity);
        velocities.add(velocity1);
        return velocities;
    }

    /**
     * the speed of the paddle.
     *
     * @return int the speed of the paddle
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * the width of the paddle.
     *
     * @return int the width of the paddle
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * string representing the level.
     *
     * @return string the name of the level
     */

    public String levelName() {
        return "Green 3";
    }

    /**
     * creating a background for the specific level.
     * @return sprite the background
     */

    public Sprite getBackground() {
        return new BackgroundLevel3();

    }

    /**
     * the block of the specific level.
     *
     * @return the blocks
     */
    public List<Block> blocks() {
        List<Block> gameBlocks = new ArrayList<>(NUMBER_OF_BLOCKS + 4);
        Point upperLeft = new Point(0, 20);
        Point midLeft = new Point(0, 20);
        Point bottomLeft = new Point(20, 580);
        Point midRight = new Point(780, 20);
        Rectangle leftRect = new Rectangle(midLeft, 20, 580);
        Rectangle rightRect = new Rectangle(midRight, 20, 580);
        Rectangle topRect = new Rectangle(upperLeft, 800, 20);
        Rectangle bottomtRect = new Rectangle(bottomLeft, 760, 20);
        Block leftBlock = new Block(leftRect, Color.gray);
        Block rightBlock = new Block(rightRect, Color.gray);
        Block topBlock = new Block(topRect, Color.gray);
        Block bottomBlock = new Block(bottomtRect, Color.gray);
        gameBlocks.add(leftBlock);
        gameBlocks.add(rightBlock);
        gameBlocks.add(topBlock);
        gameBlocks.add(bottomBlock);
        // colors of the blocks
        Color[] color = {Color.RED, Color.ORANGE,
                Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6 + i; j++) {
                Point start = new Point(725 - (j * 55),
                        300 - (i * 25));
                Rectangle rectangle = (new Rectangle(start, 55,
                        25));
                Block block = new Block(rectangle, color[i]);
                gameBlocks.add(block);
            }
        }
        return gameBlocks;
    }

    /**
     * the number of blocks in the game.
     * @return the number of blocks in the game
     */
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

}
