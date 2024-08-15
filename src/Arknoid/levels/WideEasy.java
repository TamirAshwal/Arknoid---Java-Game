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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 10;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final int LEVEL_NUMBER = 2;

    /**
     * returns the number of balls in the level.
     *
     * @return int number of balls
     */
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }
    /**
     * gets the level number.
     * @return the level number
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
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            Velocity velocity = new Velocity(-5 + i, -5);
            velocities.add(velocity);
        }
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            Velocity velocity = new Velocity(5 - i, -5);
            velocities.add(velocity);
        }
        return velocities;
    }
    /**
     * the speed of the paddle.
     *
     * @return int the speed of the paddle
     */
    public int paddleSpeed() {
        return 50;
    }

    /**
     * the width of the paddle.
     *
     * @return int the width of the paddle
     */
    public int paddleWidth() {
        return 500;
    }

    /**
     * string representing the level.
     *
     * @return string the name of the level
     */

    public String levelName() {
        return "Wide Easy";
    }
    /**
     * creating a background for the specific level.
     * @return a sprite the background
     */

    public Sprite getBackground() {
        return new BackgroundLevel2();

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
        Color[] color = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN,
                Color.CYAN};
        for (int i = 0; i < 14; i++) {
            Point p = new Point(20 + (i * 50), 250);
            Rectangle rectangle = new Rectangle(p, 50, 35);
            Block block = new Block(rectangle, color[i]);
            gameBlocks.add(block);
        }
        // adding the final block so it will look good
        Point p = new Point(720, 250);
        Rectangle rectangle = new Rectangle(p, 60, 35);
        Block block = new Block(rectangle, color[14]);
        gameBlocks.add(block);
        return gameBlocks;
    }
    /**
     * the number of blocks in the game.
     * @return an int the number of blocks
     */

    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

}
