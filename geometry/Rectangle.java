package geometry;

import java.util.ArrayList;

public class Rectangle {

    private Point p;
    private double width;
    private double height;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft is the left upper point of the rectangle.
     * @param height    is the height of the rectangle.
     * @param width     is the width of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.p = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Find intersection points.
     *
     * @param line left upper point of the rectangle.
     * @return return a List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionP(Line line) {
        java.util.List<Point> points = new ArrayList<>();
        Line[] lines = new Line[4];
        lines[0] = new Line(this.p.getX(), this.p.getY(), this.p.getX() + width, this.p.getY()); // Top
        lines[1] = new Line(this.p.getX(), this.p.getY(), this.p.getX(), this.p.getY() + height); // Left
        lines[2] = new Line(this.p.getX(), this.p.getY() + height,
                this.p.getX() + width, this.p.getY() + height); // Bottom
        lines[3] = new Line(this.p.getX() + width, this.p.getY(),
                this.p.getX() + width, this.p.getY() + height); // Right
        for (Line l : lines) {
            Point intersects = l.intersectionWith(line);
            if (intersects != null) {
                points.add(intersects);
            }
        }

        return points;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.p;
    }


    /**
     * @param p is the new upper-left point of the rectangle.
     */
    public void setUpperLeft(Point p) {
        this.p = p;
    }

    /**
     * Checks if line intersects with the edges of the rectangle.
     *
     * @param l the line to check.
     * @return true if the line intersects with the edges false otherwise.
     */
    public boolean intersectsRec(Line l) {
        Line[] lines = new Line[4];
        lines[0] = new Line(this.p.getX(), this.p.getY(), this.p.getX() + width, this.p.getY()); // Top
        lines[1] = new Line(this.p.getX(), this.p.getY(), this.p.getX(), this.p.getY() + height); // Left
        lines[2] = new Line(this.p.getX(), this.p.getY() + height,
                this.p.getX() + width, this.p.getY() + height); // Bottom
        lines[3] = new Line(this.p.getX() + width, this.p.getY(),
                this.p.getX() + width, this.p.getY() + height); // Right
        return l.isIntersecting(lines[0])
                ||
                l.isIntersecting(lines[1])
                ||
                l.isIntersecting(lines[2])
                || l.isIntersecting(lines[3]);
    }


    /**
     * Checks if a given y-coordinate is inside the rectangle.
     *
     * @param p the y-coordinate to check.
     * @return true if the y-coordinate is inside the rectangle, false otherwise.
     */


    public String edge(Point p) {
        if (p == null) {
            return "unknown";
        }
        String[] edges = new String[]{"top", "left", "bottom", "right"};
        Line[] lines = new Line[4];
        lines[0] = new Line(this.p.getX(), this.p.getY(), this.p.getX() + width, this.p.getY()); // Top
        lines[1] = new Line(this.p.getX(), this.p.getY(), this.p.getX(), this.p.getY() + height); // Left
        lines[2] = new Line(this.p.getX(), this.p.getY() + height,
                this.p.getX() + width, this.p.getY() + height); // Bottom
        lines[3] = new Line(this.p.getX() + width, this.p.getY(),
                this.p.getX() + width, this.p.getY() + height); // Right

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].pointOnL(p)) {
                return edges[i];
            }
        }
        return "unknown";
    }


}