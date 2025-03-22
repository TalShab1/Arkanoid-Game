package listeners;


public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * be notified of hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);

}
