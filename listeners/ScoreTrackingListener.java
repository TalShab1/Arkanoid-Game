package listeners;

import game.Counter;
import sprites.Ball;
import sprites.Block;


public class ScoreTrackingListener implements HitListener {
    private Counter s;

    /**
     * Constructs a ScoreTrackingListener.
     *
     * @param scoreCounter the counter that keeps track
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.s = scoreCounter;
    }

    /**
     * This method is called whenever is hit.
     * increases the score by 5 points.
     *
     * @param beingHit the Block
     * @param hitter   the Ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.s.increase(5);
    }
}