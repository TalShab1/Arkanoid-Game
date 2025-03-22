package listeners;

import game.Counter;
import game.Game;
import sprites.Ball;
import sprites.Block;


public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs new ball remover.
     *
     * @param game            game object.
     * @param remainingBlocks object that tracks remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * called whenever the beingHit object is hit.
     *
     * @param beingHit block that is hit.
     * @param hitter   Ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
            hitter.setColor(beingHit.getColor());
        }
    }
}