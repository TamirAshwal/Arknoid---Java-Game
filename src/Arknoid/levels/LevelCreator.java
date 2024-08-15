// Tamir Ashwal 209374867
package Arknoid.levels;
import Arknoid.LevelInformation;

/**
 * The type Level creator.
 */
public class LevelCreator {
    private int levelNumber;

    /**
     * Instantiates a new Level creator.
     *
     * @param levelNumber the level number
     */
    public LevelCreator(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Level information level the user wants to play created by the level
     * number.
     * @return the level according to the level number
     */
    public LevelInformation levelInformation() {
        if (this.levelNumber == 1) {
            return new LevelDirectHit();
        }
        if (this.levelNumber == 2) {
            return new WideEasy();
        }
        if (this.levelNumber == 3) {
            return new Green3();
        } else {
            return null;
        }
    }
}
