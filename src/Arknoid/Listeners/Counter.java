// Tamir Ashwal 209374867
package Arknoid.Listeners;
/**
 * The type Counter.
 */
public class Counter {
    private int numberOfBlocks;

    /**
     * Increase.
     *
     * @param number the number in which we increase in.
     */
// add number to current count.
    public void increase(int number) {
        this.numberOfBlocks += number;

    }

    /**
     * Decrease.
     *
     * @param number the number in which we descent in.
     */
// subtract number from current count.
    public void decrease(int number) {
        this.numberOfBlocks -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return numberOfBlocks;

    }
}
