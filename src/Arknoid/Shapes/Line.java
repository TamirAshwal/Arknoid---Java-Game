// Tamir ashwal 209374867
package Arknoid.Shapes;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private static final double EPSILON = 0.0001;
    private Point start;
    private Point end;
    private boolean parallel2y;
    private double slope;
    private double b;


    private void calcSlope() {
        // checking if the line is parallel to the Y axis
        if (this.start.getX() == this.end.getX()) {
            this.slope = 0;
            this.parallel2y = true;
            return;
        } else {
            // calculating the slope
            this.slope = ((this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX()));
            this.parallel2y = false;
        }
    }


    private void calcB() {
        /* we treat the line as an infinite line so we want to see if the line
        intersects the Y axis
         */
        if (this.parallel2y) {
            // if the line is parallel to the y axis we set b to be the x value
            this.b = this.start.getX();
        } else {
            // calculate the intersection
            this.b = this.start.getY() - (this.slope * this.start.getX());
        }
    }

    /**
     * The entry point of application.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        // constructor to the line
        setLine(start, end);
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the end point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        // constructor to the line
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        setLine(start, end);

    }

    /**
     * Set line.
     *
     * @param start the start
     * @param end   the end
     */
    public void setLine(Point start, Point end) {
        this.start = start;
        this.end = end;
        // making sure the smaller x is the starting point
        // setting the slope and the b of the line
        calcSlope();
        calcB();
    }

    /**
     * calulate the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        // calculate the length of the line
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        // calculate the middle point of the line
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new Point(x, y);
    }

    /**
     * returns the start point of the line.
     *
     * @return the point
     */
    public Point start() {
        if (start == null) {
            return null;
        }
        return this.start;
    }

    /**
     * returns the end point of the line.
     *
     * @return the point
     */
    public Point end() {
        if (end == null) {
            return null;
        }
        return this.end;
    }

    /**
     * the method checks if the line intersect.
     * @param a the line parallel to the y axes
     * @param b line
     * @return boolean to check if they are parallel
     */

    public boolean checkForYIntersection(Line a, Line b) {
        if (Math.min(a.start.getY(), a.end.getY()) > Math.max(b.start.getY(), b.end.getY()) + EPSILON) {
            return false;
        }
        if (Math.min(b.start.getY(), b.end.getY()) > Math.max(a.start.getY(), a.end.getY()) + EPSILON) {
            return false;
        }
        return true;
    }
    /**
     * the method checks if the line have common x value.
     * @param a line
     * @param b line
     * @return boolean of they intersect
     */
    public boolean checkForXIntersection(Line a, Line b) {
        if (Math.min(a.start.getX(), a.end.getX()) > Math.max(b.start.getX(), b.end.getX())) {
            return false;
        }
        if (Math.min(b.start.getX(), b.end.getX()) > Math.max(a.start.getX(), a.end.getX())) {
            return false;
        }
        return true;
    }

    /**
     * the method checks if they have a common x value.
     * @param a line
     * @param b line
     * @return boolean if they have a common x
     */

    public boolean checkCommonX(Line a, Line b) {
        double xOfIntersection = (b.b - a.b)
                / (a.slope - b.slope);
        return xOfIntersection + EPSILON >= Math.max(Math.min(a.start.getX(), a.end.getX()),
                Math.min(b.start.getX(), b.end.getX()))
                && xOfIntersection - EPSILON
                <= Math.min(Math.max(a.start.getX(), a.end.getX()),
                Math.max(b.start.getX(), b.end.getX()));
    }

    /**
     * the method checks if the 2 lines have 1 common point.
     * @param a line parallel to the y axes
     * @param b line
     * @return boolean if they have a common point
     */
    public boolean oneParallelCommonPoint(Line a, Line b) {
        double xOfIntersection = a.b;
        if (!(Math.min(b.start.getX(), b.end.getX()) - EPSILON <= xOfIntersection
                && Math.max(b.start.getX(), b.end.getX()) + EPSILON
                >= xOfIntersection)) {
            return false;
        }
        //Line equation for b is y_b = m * x_b + n
        double yOfb = b.slope * xOfIntersection + b.b;


        if (!((Math.min(a.start.getY(), a.end.getY()) - EPSILON <= yOfb)
                && Math.max(a.start.getY(), a.end.getY()) + EPSILON >= yOfb)) {
            return false;
        }
        return true;
    }

    /**
     * the method return a boolean if the lines intersect.
     *
     * @param other the other line to check intersection with
     * @return the true or false if the lines intersect
     */
    public boolean isIntersecting(Line other) {
        // checking of the lines are parallel
        if (other.slope == this.slope && (this.parallel2y == other.parallel2y)) {
            if (other.b == this.b) {
                // both lines parallel to Y axis
                if (this.parallel2y) {
                    // checking if the lines are contained
                    return checkForYIntersection(this, other);
                } else {
                    // lines are not parallel to the Y axis
                    return checkForXIntersection(this, other);
                }
            } else {
                /* lines are parallel and intersect the Y axis at different
                 * points so they can't have a intersection point
                 */
                return false;
            }
        } else {
            //intersected and not parallel to the Y axis
            if (this.parallel2y == other.parallel2y) {
                return checkCommonX(this, other);
            } else {
                // intersected and not parallel to each other
                if (this.parallel2y) {
                    // checking if they have common x and y values
                    return oneParallelCommonPoint(this, other);
                } else {
                    return oneParallelCommonPoint(other, this);
                }
            }
        }
    }

    /**
     * the mrthod checks if the lines contained.
     * @param a line
     * @param b line
     * @return boolean if they contained
     */

    public boolean checkingParallelContained(Line a, Line b) {
        if (Math.abs(Math.min(a.start.getX(), a.end.getX())
                - Math.max(b.start.getX(), b.end.getX())) <= EPSILON
                && Math.max(a.start.getX(), a.end.getX())
                >= Math.min(b.start.getX(), b.end.getX())) {
            return false;
        }
        return true;
    }

    /**
     * the method checks if the lines that parallel to the y axes are contained.
     * @param a line
     * @param b line
     * @return boolean if they contained
     */

    public boolean checkingYparallelcontained(Line a, Line b) {
        if (Math.abs(Math.min(a.start.getY(), a.end.getY())
                - Math.max(b.start.getY(), b.end.getY())) <= EPSILON
                && Math.max(a.start.getY(), a.end.getY())
                >= Math.min(b.start.getY(), b.end.getY())) {
            return false;
        }
        return true;

    }

    /**
     * the method returns the intersection point of both lines.
     *
     * @param other the other line to check intersection with
     * @return the point of intersection
     */
    public Point intersectionWith(Line other) {
        // more than 1 intersection point return null
        if (this.equals(other)) {
            return null;
        }
        //no intersection
        if (!this.isIntersecting(other)) {
            return null;
        } else {
            // checking if contained lines have only 1 intersection point
            if (other.slope == this.slope && !other.parallel2y
                    && !this.parallel2y) {
                // start of the line equal to the end of the other line
                if (!checkingParallelContained(this, other)) {
                    if (this.start.getX() < this.end.getX()) {
                        return this.start;
                    } else {
                        return this.end;
                    }
                    // start of the other line equal to the end of the line
                } else if (!checkingParallelContained(other, this)) {
                    if (this.start.getX() < this.end.getX()) {
                        return this.end;
                    } else {
                        return this.start;
                    }
                    // lines contained
                } else {
                    return null;
                }
            } else {
                if (this.parallel2y && !other.parallel2y) {
//                    System.out.println("im here");
                    double xOfIntersection = this.b;
                    double yOfIntersection = (other.slope * this.b)
                            + other.b;
                    return new Point(xOfIntersection, yOfIntersection);
                }
                if (other.parallel2y && !this.parallel2y) {
                    double xOfIntersection = other.b;
                    double yOfIntersection = (this.slope * other.b)
                            + this.b;
                    return new Point(xOfIntersection, yOfIntersection);
                }
                /* both of the lines are parallel to the Y axis and not
                 *contained so we check for the intersection point
                 */
                if (this.parallel2y && other.parallel2y) {
                    if (!(checkingYparallelcontained(this, other))) {
                        double xOfIntersection = Math.min(this.start.getX(), this.end.getX());
                        double yOfIntersection = Math.min(this.start.getY(), this.end.getY());
                        return new Point(xOfIntersection, yOfIntersection);
                    }

                    // checking for the other option of the intersection point
                    if (!(checkingYparallelcontained(other, this))) {
                        double xOfIntersection = Math.max(this.start.getX(), this.end.getX());
                        double yOfIntersection = Math.max(this.start.getY(), this.end.getY());
                        return new Point(xOfIntersection, yOfIntersection);
                    }
                    return null;
                }

                double xOfIntersection = (other.b - this.b)
                        / (this.slope - other.slope);
                double yOfIntersection = this.slope * xOfIntersection + this.b;
                return new Point(xOfIntersection, yOfIntersection);
            }
        }
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        /* checking if the start and end of both points are equal or the start
         * of one point equals to the end of other point and the end of one
         * point equals to the start of the other point
         */
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end))
                && (this.end.equals(other.start)));
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // getting the list of intersection points of the line with the rectangle
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList == null) {
            return null;
        }
        Point minPoint = pointList.get(0);
        double minLength = minPoint.distance(this.start);
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).distance(this.start) < minLength) {
                minPoint = pointList.get(i);
                minLength = pointList.get(i).distance(this.start);
            }
        }
        return minPoint;
    }

    /**
     * checks if the point is on the line.
     * @param p the point
     * @return boolean
     */

    public boolean isPointOnLine(Point p) {
        if (this.parallel2y) {
            if ((Math.max(this.start.getY(), this.end.getY()) >= p.getY()
                    && Math.min(this.start.getY(), this.end.getY()) <= p.getY())
                    && this.start.getX() == p.getX()) {
                return true;
            } else {
                return false;
            }

        } else if (this.slope == 0) {
            if (Math.max(this.start.getX(), this.end.getX()) >= p.getX()
                    && Math.min(this.start.getX(), this.end.getX()) <= p.getX()
                    && this.start.getY() == p.getY()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}