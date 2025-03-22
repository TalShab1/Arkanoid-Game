package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * Constructs an empty sprites.SpriteCollection.
     */
    public SpriteCollection() {
        spritesList = new ArrayList<>();
    }

    /**
     * Adds a sprite.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }
    /**
     * removes a sprite.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        spritesList.remove(s);
    }

    /**
     * Calls timePassed on sprites.
     */
    public void notifyAllTimePassed() {
        // Create a copy of the list to avoid concurrent modification
        List<Sprite> spritesCopy = new ArrayList<>(this.spritesList);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn on all sprites.
     *
     * @param d the DrawSurface on which all sprites should be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        spritesList.forEach(sprite -> sprite.drawOn(d));
    }

}