package geometry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point with  x and y.
     *
     * @param x x coordinate of the point.
     * @param y y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = round(x, 2);
        this.y = round(y, 2);
    }

    /**
     * Rounds a double value.
     *
     * @param v value to round.
     * @param p places.
     * @return rounded value.
     */
    private static double round(double v, int p) {
        BigDecimal bd = BigDecimal.valueOf(v);
        bd = bd.setScale(p, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Constructor for a point with random coordinates.
     */
    public Point() {
        Random random = new Random();
        this.x = 1 + (999 * random.nextDouble());
        this.y = 1 + (999 * random.nextDouble());
    }

    /**
     * Calculates the distance between points.
     *
     * @param other other point to calculate the distance between.
     * @return distance between the points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if points are equal.
     *
     * @param other other point to compare.
     * @return true if the points are equal, false if the points are not equal.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Gets the x coordinate of the point.
     *
     * @return The x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate of the point.
     *
     * @return The y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x coordinate of this point.
     *
     * @param x The new x coordinate of this point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of this point.
     *
     * @param y The new y coordinate of this point.
     */
    public void setY(double y) {
        this.y = y;
    }
}