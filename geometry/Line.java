package geometry;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Line {
    private static final double C = 0.0001;

    private Point start;
    private Point end;

    /**
     * This method takes a double value and rounds it to the specified number of decimal places.
     * /**
     * Constructs a line with the given start and end points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line with coordinates.
     *
     * @param x1 x of start
     * @param y1 y of start
     * @param x2 x of end
     * @param y2 y of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(roundNum(x1, 2), roundNum(y1, 2));
        this.end = new Point(roundNum(x2, 2), roundNum(y2, 2));
    }

    /**
     * Return the length.
     *
     * @return return the length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return return the middle of this line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point.
     *
     * @return return the start.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end.
     *
     * @return return the end point.
     */
    public Point end() {
        return this.end;
    }


    /**
     * Rounds number.
     *
     * @param v number to round.
     * @param p number of places.
     * @return rounded number.
     */
    private static double roundNum(double v, int p) {
        try {
            BigDecimal var = BigDecimal.valueOf(v);
            var = var.setScale(p, RoundingMode.HALF_UP);
            return var.doubleValue();
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Checks if the line intersects with another.
     *
     * @param other other line to check.
     * @return true if intersects, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        boolean curVer = false;
        boolean othVer = false;
        if (this.end.getX() == this.start.getX()) {
            curVer = true;
        }
        if (other.end.getX() == other.start.getX()) {
            othVer = true;
        }

        if (curVer && othVer) {
            return this.end.getX() == other.end.getX()
                    &&
                    (overlap(this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())
                            ||
                            this.end.equals(other.start) || other.end.equals(this.start));
        }

        double m1 = roundNum((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()), 5);
        double b1 = roundNum(this.start.getY() - m1 * this.start.getX(), 5);
        double m2 = roundNum((other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX()), 5);
        double b2 = roundNum(other.start.getY() - m2 * other.start.getX(), 5);

        if (m1 == m2) {
            return b1 == b2;
        }

        double x = roundNum((b2 - b1) / (m1 - m2), 2);
        double y = roundNum(m1 * x + b1, 2);

        if (curVer) {
            x = this.end.getX();
            y = roundNum(m2 * x + b2, 2);
        }

        if (othVer) {
            x = other.end.getX();
            y = roundNum(m1 * x + b1, 2);
        }

        return this.pointOnL(new Point(x, y))
                &&
                other.pointOnL(new Point(x, y));
    }

    /**
     * Checks if there is an overlap.
     *
     * @param y1S start first range
     * @param y1E end of first range
     * @param y2S start of second range
     * @param y2E end of second range
     * @return true if overlaps, false otherwise
     */

    private boolean overlap(double y1S, double y1E, double y2S, double y2E) {
        double min1 = Math.min(y1S, y1E);
        double min2 = Math.max(y2S, y2E);

        return min1 <= min2 && min1 >= min2;
    }

    /**
     * Finds the intersection.
     *
     * @param other other line to find.
     * @return the intersection point.
     */
    public Point intersectionWith(Line other) {
        boolean curVer = false;
        boolean othVer = false;
        if (this.end.getX() == this.start.getX()) {
            curVer = true;
        }
        if (other.end.getX() == other.start.getX()) {
            othVer = true;
        }

        double x, y;
        double m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double b1 = this.start.getY() - m1 * this.start.getX();
        double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        double b2 = other.start.getY() - m2 * other.start.getX();


        if (curVer && othVer) {
            if (this.start.getX() == other.start.getX()) {
                return null;
            }
            return null;
        }


        if (othVer) {
            x = other.start.getX();
            y = m1 * x + b1;
        } else if (curVer) {
            x = this.start.getX();
            y = m2 * x + b2;
        } else {
            if (m1 == m2) {
                return null;
            }
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }
        if (!other.pointOnL(new Point(x, y)) || !this.pointOnL(new Point(x, y))) {
            return null;
        }

        return new Point(x, y);
    }


    /**
     * true is the lines are equal, false otherwise.
     *
     * @param other line to check.
     * @return return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }


    /**
     * calculate intersection Point.
     *
     * @param rect rectangle for search.
     * @return return intersection point .
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionP(new Line(this.start, this.end));

        if (points.isEmpty()) {
            return null;
        }

        Point minPoint = points.get(0);
        double minDistance = this.start.distance(minPoint);

        for (Point point : points) {
            double distance = this.start.distance(point);
            if (distance < minDistance) {
                minPoint = point;
                minDistance = distance;
            }
        }

        return minPoint;
    }

    /**
     * Checks if a given point lies on the line segment defined by this line.
     *
     * @param point point to check
     * @return true if the point lies on the line.
     */
    public boolean pointOnL(Point point) {
        return Math.abs(this.start.distance(this.end) - (point.distance(this.start) + point.distance(this.end))) < C;
    }

}