package sprites;

import game.Game;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;


public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velo;
    private Frame frame;
    private GameEnvironment g;

    /**
     * Constructs a sprites.Ball.
     *
     * @param center center point ball
     * @param r      radius of ball
     * @param color  color of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a sprites.Ball with specified x y  radius and color.
     *
     * @param x     x of the center
     * @param y     y of the center
     * @param r     radius of ball
     * @param color color of ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a sprites.Ball with a specified center point radius color game environment and frame.
     *
     * @param point center point
     * @param y     radius of the ball
     * @param red   color of the ball
     * @param ge    game environment
     * @param frame frame
     */
    public Ball(Point point, int y, Color red, GameEnvironment ge, Frame frame) {
        this.center = point;
        this.r = y;
        this.color = red;
        this.g = ge;
        this.frame = frame;
    }

    /**
     * Returns the x center.
     *
     * @return the x center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Sets the center point.
     *
     * @param center the new center point.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Returns the y of the center.
     *
     * @return y coordinate of center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Sets the index of the frame.
     *
     * @param frameIndex the index of the frame.
     */

    /**
     * Gets the index of the frame.
     *
     * @return the index of the frame.
     */

    /**
     * Returns the radius.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Sets the color.
     *
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the game environment.
     *
     * @param g the game environment.
     */
    public void setG(GameEnvironment g) {
        this.g = g;
    }

    /**
     * Returns the color.
     *
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the center point.
     *
     * @return the center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velo = v;
    }

    /**
     * Sets the velocity .
     *
     * @param dx change in x
     * @param dy change in y
     */
    public void setVelocity(double dx, double dy) {
        this.velo = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity.
     *
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velo;
    }

    /**
     * Calculates the collision point.
     *
     * @return the collision point.
     */
    public Point collisionP() {
        double minRatio = Double.POSITIVE_INFINITY;
        double left = -this.center.getX() / this.velo.getDx();
        double right = (this.frame.getWidth() - this.center.getX()) / this.velo.getDx();
        double top = -this.center.getY() / this.velo.getDy();
        double bottom = (this.frame.getHeight() - this.center.getY()) / this.velo.getDy();
        double[] ratios = {left, right, top, bottom};
        for (double r : ratios) {
            if (r > 0 && r < minRatio) {
                minRatio = r;
            }
        }
        return new Point((this.getVelocity().getDx() * minRatio) + this.center.getX(),
                this.getVelocity().getDy() * minRatio + this.center.getY());
    }

    /**
     * Moves the ball one step.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, collisionP());
        CollisionInfo collision = this.g.closestC(trajectory);

        if (collision != null) {
            Point collisionP = collision.collisionPoint();
            Collidable collisionObj = collision.collisionObject();
            Rectangle collisionRec = collisionObj.getCollisionRectangle();
            String edge = collisionRec.edge(collisionP);

            boolean collisionClose = this.center.distance(collisionP) <= this.r * 2;

            if (collisionClose) {
                Point newPosition = this.velo.applyToPoint(this.center);

                switch (edge) {
                    case "top":
                        if (newPosition.getY() + this.r >= collisionP.getY()) {
                            this.setVelocity(collisionObj.hit(this, collisionP, this.velo));
                        }
                        break;
                    case "bottom":
                        if (newPosition.getY() - this.r <= collisionP.getY()) {
                            this.setVelocity(collisionObj.hit(this, collisionP, this.velo));
                        }
                        break;
                    case "right":
                        if (newPosition.getX() - this.r <= collisionP.getX()) {
                            this.setVelocity(collisionObj.hit(this, collisionP, this.velo));
                        }
                        break;
                    case "left":
                        if (newPosition.getX() + this.r >= collisionP.getX()) {
                            this.setVelocity(collisionObj.hit(this, collisionP, this.velo));
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        this.center = this.velo.applyToPoint(this.center);
    }

    /**
     * Draws the ball on the DrawSurface.
     *
     * @param surface the surface to draw
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color); // Set the color of the ball
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Updates the ball's state.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to game.
     *
     * @param game the game to add
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball to game.
     *
     * @param g the game to add
     */

    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

}