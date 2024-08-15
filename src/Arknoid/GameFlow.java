// Tamir Ashwal 209374867
package Arknoid;
import Arknoid.Animations.AnimationRunner;
import Arknoid.Animations.EndScreen;
import Arknoid.Animations.KeyPressStoppableAnimation;
import Arknoid.Animations.YouWonScreen;
import Arknoid.Listeners.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        createGui();
        createRunner();
        getKeyBoard();
        this.score = new Counter();
    }
    private void getKeyBoard() {
        this.keyboardSensor = gui.getKeyboardSensor();
    }

    private void createGui() {
        this.gui = new GUI("Arknoid", 800, 600);
    }

    private void createRunner() {
        this.runner = new AnimationRunner(this.gui, 60, new Sleeper());
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        // creating index to iterate through the levels
        int indexOfElement = 0;
        for (LevelInformation levelInfo : levels) {
            // creating a level and initializing it
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.runner, this.gui, this.score);
            level.initialize();
            // running the level
            while (level.getRunning()) {
                level.run();
                // the player won so running the you won animation
                if (indexOfElement == levels.size() - 1 && level.getBallCounter() > 0) {
                    this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                            KeyboardSensor.SPACE_KEY, new YouWonScreen(this.score,
                            this.keyboardSensor)));
                    // closing the game
                    this.gui.close();
                    break;
                }
            }
            /* if the number of balls is 0 the player lost, so we run the end
            * game animation
            */
            if (level.getBallCounter() == 0) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.score,
                        this.keyboardSensor)));
                // closing the game
                gui.close();
                break;
            }
            // moving to the next game
            indexOfElement++;
        }
    }
}
