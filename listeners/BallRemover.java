package listeners;

import game.Counter;
import game.Game;
import sprites.Ball;
import sprites.Block;


public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover.
     *
     * @param game          game from which balls removed
     * @param remainingBalls counter for remaining balls in the game
     */

    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * whenever beingHit object is hit.
     * removes ball from the game.
     *
     * @param beingHit block that is being hit
     * @param hitter   ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}