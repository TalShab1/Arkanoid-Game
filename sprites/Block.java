package sprites;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import listeners.HitListener;
import game.Game;


public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Creates a new sprites.Block with the given rectangle shape and black color.
     *
     * @param r the rectangle shape of the block.
     */
    public Block(Rectangle r) {
        this.rectangle = r;
        this.color = Color.BLACK;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets the collision rectangle.
     *
     * @return the rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the event of the block being hit.
     *
     * @param hitter          Ball
     * @param collisionPoint  the point at which the collision
     * @param currentVelocity the current velocity
     * @return new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dy = currentVelocity.getDy();
        double dx = currentVelocity.getDx();

        if (collisionPoint.getX() >= this.rectangle.getUpperLeft().getX()
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()
                && collisionPoint.getY() >= this.rectangle.getUpperLeft().getY()
                && collisionPoint.getY() <= this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
            if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                    || collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
                dx = -dx;
            }
            if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()
                    || collisionPoint.getY() == this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
                dy = -dy;
            }
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return new Velocity(dx, dy);
        }
        return hitter.getVelocity();
    }

    /**
     * Draws the block.
     *
     * @param surface DrawSurface to draw
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Notifies the block that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the block.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Update the block color from the default.
     *
     * @param c is the color to the update.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * removes the block.
     *
     * @param g the game
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Checks if the color of the ball matches.
     *
     * @param ball ball to check
     * @return true if the ball's color matches
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Adds a hit listener.
     *
     * @param hl the HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener.
     *
     * @param hl the HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Notifies all registered.
     *
     * @param hitter the Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}
