// Tamir Ashwal 209374867
package Arknoid.Listeners;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}