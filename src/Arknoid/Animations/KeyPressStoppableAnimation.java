// Tamir Ashwal 209374867
package Arknoid.Animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key which would stop the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * executing the frame of end screen animation.
     * @param d the drawing surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Delegate doOneFrame to the decorated animation
        this.animation.doOneFrame(d);

        // Check if the specified key is pressed
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * return if the animation should stop.
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        // Delegate shouldStop to the decorated animation
        return this.stop;
    }
}
