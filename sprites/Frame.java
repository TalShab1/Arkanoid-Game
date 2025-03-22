package sprites;

import geometry.Point;

public class Frame {
    private Point firstP;
    private Point secP;

    /**
     * Creates a new frame.
     *
     * @param startPoint start point of the frame
     * @param endPoint   end point of the frame
     */
    public Frame(Point startPoint, Point endPoint) {
        this.firstP = startPoint;
        this.secP = endPoint;
    }


    /**
     * Checks if a point is inside the frame.
     *
     * @param p the point to check
     * @return true if the point is inside the frame, false otherwise
     */
    public boolean isInside(Point p) {
        return p.getX() >= firstP.getX() && p.getX() <= secP.getX()
                && p.getY() >= firstP.getY() && p.getY() <= secP.getY();
    }

    /**
     * Gets width of the frame.
     *
     * @return width of the frame
     */
    public int getWidth() {
        return (int) (secP.getX() - firstP.getX());
    }

    /**
     * Gets height of the frame.
     *
     * @return height of the frame
     */
    public int getHeight() {
        return (int) (secP.getY() - firstP.getY());
    }

    /**
     * Sets end point of the frame.
     *
     * @param endPoint new end point
     */
    public void setEndPoint(Point endPoint) {
        this.secP = endPoint;
    }

    /**
     * Sets start point of the frame.
     *
     * @param startPoint new start point
     */
    public void setStartPoint(Point startPoint) {
        this.firstP = startPoint;
    }

    /**
     * Gets start point of the frame.
     *
     * @return start point of the frame
     */
    public Point getStartPoint() {
        return firstP;
    }

    /**
     * Gets end point of the frame.
     *
     * @return end point of the frame
     */
    public Point getEndPoint() {
        return secP;
    }

}