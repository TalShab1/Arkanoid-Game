package game;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Collidable;
import sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GameEnvironment {
    private List<Collidable> collidableL;

    /**
     * Add an object to the game environment.
     *
     * @param c collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidableL.add(c);
    }

    /**
     * remove an object to the game environment.
     *
     * @param c collidable object to remove
     */

    public void removeCollidable(Collidable c) {
        collidableL.remove(c);
    }

    /**
     * Constructs a new game.GameEnvironment.
     *
     * @param blocks list of collidable objects
     */
    public GameEnvironment(List<Collidable> blocks) {
        this.collidableL = new ArrayList<>();
        this.collidableL.addAll(blocks);
    }

    /**
     * Gets list of collidable objects.
     *
     * @return list of collidable objects.
     */
    public List<Collidable> getCollidableList() {
        return collidableL;
    }

    /**
     * Constructs a new game.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableL = new ArrayList<>();
    }

    /**
     * Finds the closest collision point of a line with an object.
     *
     * @param trajectory trajectory to check
     * @return info about the closest collision.
     */
    public CollisionInfo closestC(Line trajectory) {
        Point closestIntersect = null;
        Collidable closestCollidable = null;
        double minD = Double.MAX_VALUE;

        for (Collidable collidable : collidableL) {
            Rectangle rectangle = collidable.getCollisionRectangle();
            if (rectangle.intersectsRec(trajectory)) {
                Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(rectangle);
                if (intersectionPoint != null) {
                    double d = intersectionPoint.distance(trajectory.start());
                    if (d < minD) {
                        minD = d;
                        closestIntersect = intersectionPoint;
                        closestCollidable = collidable;
                    }
                }
            }
        }
        for (Collidable collidable : collidableL) {
            if (!Objects.equals(collidable.getCollisionRectangle().edge(closestIntersect), "unknown")) {
                closestCollidable = collidable;
                break;
            }
        }

        if (closestIntersect == null) {
            return null;
        }

        return new CollisionInfo(closestIntersect, closestCollidable);
    }


}
