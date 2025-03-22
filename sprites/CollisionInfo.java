package sprites;

import geometry.Point;


public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collistionObject;

    /**
     * Returns point of collision.
     *
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the object in the collision.
     *
     * @return collision object.
     */

    public Collidable collisionObject() {
        return this.collistionObject;
    }

    /**
     * Constructs a new sprites.CollisionInfo instance.
     *
     * @param collisionPoint   point of collision.
     * @param collistionObject object that is collided.
     */
    public CollisionInfo(Point collisionPoint, Collidable collistionObject) {
        this.collisionPoint = collisionPoint;
        this.collistionObject = collistionObject;
    }


}