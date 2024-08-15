// Tamir ashwal 209374867
package Arknoid.Shapes;
/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = 0.0001;

    /**
     * The entry point of application.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Instantiates a new Point.
     */
    public Point() {
        // creating a new point with the values o
        double x = 0;
        double y = 0;
        setX(x);
        setY(y);

    }

    /**
     * Distance double.
     *
     * @param otherP the other point we want to measure the distance to
     * @return the distance between the 2 points
     */
    public double distance(Point otherP) {
        if (otherP == null) {
            return 0;
        }
        double xd = this.x - otherP.x;
        double yd = this.y - otherP.y;
        return Math.sqrt(((xd) * (xd)) + ((yd) * (yd)));
    }

    /**
     * return true or false the method check if 2 point are the same.
     * @param otherP the other point to check if it is equal
     * @return the true or false
     */
    public boolean equals(Point otherP) {
        if (otherP == null) {
            return false;
        }
        // using threshold to check if 2 points are equal
        return (Math.abs(this.x - otherP.x) < EPSILON
                && Math.abs(this.y - otherP.y) < EPSILON);
    }

    /**
     * Set the x value to the point.
     *
     * @param x the x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the y value to the point.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the x value of the point.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y value of the point.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}