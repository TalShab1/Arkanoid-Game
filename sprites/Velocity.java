package sprites;

import geometry.Point;

public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a velocity change in position.
     *
     * @param dx the change in X.
     * @param dy the change in Y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the change in X.
     *
     * @return the change in X.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets the change X.
     *
     * @param dx the change in X.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Gets the change in Y.
     *
     * @return the change in Y.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change Y.
     *
     * @param dy the change in Y.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * gets a point with and returns a new point with updated position.
     *
     * @param p point which the velocity is applied.
     * @return a new point with updated position.
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * Calculates the angle of the change in position.
     *
     * @param dx change in X.
     * @param dy change in Y.
     * @return the angle.
     */
    public static double getAngle(double dx, double dy) {
        double calculatedAngle = Math.toDegrees(Math.atan2(dy, dx));
        if (calculatedAngle < 0) {
            calculatedAngle += 360;
        }
        return calculatedAngle;
    }

    /**
     * Creates a new velocity from an angle in degrees and a speed.
     *
     * @param angle the angle in degrees.
     * @param speed the speed.
     * @return a new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        return new Velocity(speed * Math.sin(Math.toRadians(angle)), -speed * Math.cos(Math.toRadians(angle)));
    }

    /**
     * Calculates the speed.
     *
     * @return the speed
     */
    public double speed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

}