package listeners;

import biuoop.DrawSurface;
import game.Counter;
import game.Game;
import sprites.Sprite;

import java.awt.Color;


public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructs a ScoreIndicator.
     *
     * @param scoreCounter the counter that keeps track
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * Draws the score.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.BLACK);
        d.drawText(350, 28, "Score: " + scoreCounter.getC(), 18);
    }

    /**
     * Notifies the score.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the score indicator.
     *
     * @param game the game to which the score indicator
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the score.
     *
     * @param game the game from which the score indicator
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}