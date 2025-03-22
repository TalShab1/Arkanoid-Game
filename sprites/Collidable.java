package sprites;

import geometry.Point;
import geometry.Rectangle;

public interface Collidable {
    /**
//     * Returns collision.
     *
     * @return collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns an updated velocity.
     *
     * @param hitter ball
     * @param collisionPoint  point collision.
     * @param currentVelocity current velocity.
     * @return new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}