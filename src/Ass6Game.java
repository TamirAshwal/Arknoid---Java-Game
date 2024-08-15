// Tamir ashwal 209374867
import Arknoid.levels.LevelCreator;
import Arknoid.levels.LevelDirectHit;
import Arknoid.LevelInformation;
import Arknoid.levels.WideEasy;
import Arknoid.levels.Green3;
import Arknoid.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 3 game.
 */
public class Ass6Game {
    /**
     * The entry point of the game.
     *
     * @param args the input arguments
     * @return a list of levels to play
     */
    public static List<LevelInformation> levelsToPlay(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        int levelNumber;
        for (String level : args) {
            try {
                levelNumber = Integer.parseInt(level);
                // check if the level number is valid
                if (levelNumber < 1 || levelNumber > 3) {
                    continue;
                }
                // creating a level according to the level number
                LevelCreator levelCreator = new LevelCreator(levelNumber);
                // adding the level to the list
                levels.add(levelCreator.levelInformation());
            } catch (Exception exception) {
            }
        }
        return levels;
    }

    /**
     * checking for at least 1 valid level.
     *
     * @param args the command line arguments
     * @return boolean true or false for at least 1 valid level
     */

    public static boolean checkForValidLevels(String[] args) {
        int levelNumber;
        for (String level : args) {
            try {
                levelNumber = Integer.parseInt(level);
                /*
                 * if we found at list 1 valid level we return that there is a
                 * valid level
                 */
                if (levelNumber >= 1 && levelNumber <= 3) {
                    return true;
                }
            } catch (Exception exception) {
            }
            // no valid levels found return false
        }
        return false;
    }

    /**
     * initializing the default game.
     * @return default levels
     */

    public static List<LevelInformation> defualtLevels() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new LevelDirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        return levels;
    }


    /**
     * the main the start of the game.
     *
     * @param args the level the user want to play
     */

    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        // in case the user didn't write which levels he wants to play
        if (args.length == 0 || args[0].equals("${args}")) {
            List<LevelInformation> levels = defualtLevels();
            // initializing and running the game
            gameFlow.runLevels(levels);
        } else if (!(checkForValidLevels(args))) {
            // making sure there is at least 1 valid level
            List<LevelInformation> levels = defualtLevels();
            // initializing and running the game
            gameFlow.runLevels(levels);
        } else {
            // creating a list of the levels and running them
            List<LevelInformation> levels = levelsToPlay(args);
            gameFlow.runLevels(levels);
        }
    }
}
