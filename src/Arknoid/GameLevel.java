// Tamir ashwal 209374867
package Arknoid;
import Arknoid.Animations.Animation;
import Arknoid.Animations.AnimationRunner;
import Arknoid.Animations.KeyPressStoppableAnimation;
import Arknoid.Animations.PauseScreen;
import Arknoid.Collidiable.Block;
import Arknoid.Collidiable.Collidable;
import Arknoid.Collidiable.Paddle;
import Arknoid.Listeners.BallRemover;
import Arknoid.Listeners.BlockRemover;
import Arknoid.Listeners.ScoreIndicator;
import Arknoid.Listeners.ScoreTrackingListener;
import Arknoid.Listeners.Counter;
import Arknoid.Shapes.Ball;
import Arknoid.Shapes.Point;
import Arknoid.Shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.List;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private GUI gui;
    private Counter counter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private static final int LEFT_SCREEN = 20;
    private static final int RIGHT_SCREEN = 780;

    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level of the game
     * @param keyboardSensor   the keyboard sensor
     * @param animationRunner  the animation
     * @param gui              the gui
     * @param score            the score;
     */
    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, GUI gui, Counter score) {
        // constructor creating game and sprite
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.counter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.levelInformation = levelInformation;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.running = true;
    }

    /**
     * Gets running.
     *
     * @return the running
     */
    public boolean getRunning() {
        return this.running;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public int getBallCounter() {
        return this.ballCounter.getValue();
    }

    private void levelName(DrawSurface d) {
        d.drawText(550, 15, String.format("Level Name: "
                + this.levelInformation.levelName()), 15);
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        // adding the collidables to the game environment
        gameEnvironment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        // adding the objects to the game sprite collection
        sprites.addSprite(s);
    }


    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.sprites.addSprite(levelInformation.getBackground());
        BlockRemover blockRemover = new BlockRemover(this, this.counter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.score = scoreTrackingListener.getCurrentScore();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, this.score);
        List<Block> gameBlocks = this.levelInformation.blocks();
        // initializing balls
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Point newP = new Point(400, 450);
            Ball ball = new Ball(newP, 5, Color.white, this.gameEnvironment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            ballCounter.increase(1);
        }
        scoreIndicator.addToGame(this);
        // initializing blocks
        for (int i = 0; i < gameBlocks.size(); i++) {
            gameBlocks.get(i).addToGame(this);
        }
        /* adding the ball remover as a listener to the bottom block always in
         * index 3 of the blocks list
         */
        gameBlocks.get(3).addHitListener(ballRemover);
        // adding blocks
        for (int i = 4; i < gameBlocks.size(); i++) {
            // adding the block remover and score as a hit listeners to the blocks
            blockRemover.getCounter().increase(1);
            gameBlocks.get(i).addHitListener(blockRemover);
            gameBlocks.get(i).addHitListener(scoreTrackingListener);
            initializePaddle();
        }

    }

    /**
     * Initialize paddle.
     */
    public void initializePaddle() {
        /* if the level is not level number 2 we want to paddle to start in a
        * different place then the rest of the levels
        */
        if (this.levelInformation.getLevelNumber() != 2) {
            Paddle paddle = new Paddle(new Rectangle(new Point(350, 560),
                    levelInformation.paddleWidth(), 20),
                    Color.yellow, keyboard, LEFT_SCREEN,
                    RIGHT_SCREEN, levelInformation.paddleSpeed());
            paddle.addToGame(this);
        } else {
            Paddle paddle = new Paddle(new Rectangle(new Point(100, 560),
                    levelInformation.paddleWidth(), 20),
                    Color.yellow, keyboard, LEFT_SCREEN,
                    RIGHT_SCREEN, levelInformation.paddleSpeed());
            paddle.addToGame(this);
        }
    }

    /**
     * return if the animation should stop.
     *
     * @return boolean
     */

    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * all the game logic at every frame.
     *
     * @param d the draw surface
     */

    public void doOneFrame(DrawSurface d) {
        // checking if the user wants to pause the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
        d.setColor(Color.BLUE);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        levelName(d);
        /* if the number of blocks is 0 it means we finished the
         * game, so we exit the game
         */
        if (this.counter.getValue() == 0) {
            // adding 100 points for completing the level
            this.score.increase(100);
            this.running = false;
            return;
        }
        // if we there are no more balls we can't play so we exit the game
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     * removing the collidable object from the game
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        gameEnvironment.removeCollidableFromList(c);
    }

    /**
     * removong the sprite from the game.
     *
     * @param s the block we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSpriteFromList(s);
    }
}