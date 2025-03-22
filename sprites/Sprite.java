package sprites;

import biuoop.DrawSurface;
import game.Game;


public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d is DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed and do something's.
     */
    void timePassed();

    /**
     * add the object to the game.
     *
     * @param game is the game of the object.
     */
    void addToGame(Game game);
}