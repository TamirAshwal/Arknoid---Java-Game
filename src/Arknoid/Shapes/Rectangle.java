// Tamir ashwal 209374867
package Arknoid.Shapes;
import java.util.List;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {

    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Line left;
    private Line right;
    private Line top;
    private Line bottom;
    private double width;
    private double height;


    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        // constructor
        setPoints(upperLeft, width, height);
        setSegments(upperLeft, width, height);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets width of the rectangle.
     *
     * @param width the width of the rectangle
     */
    public void setWidth(double width) {
        // setting the width
        this.width = width;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        // setting the height
        this.height = height;
    }

    /**
     * Sets the upper left point of the rectangle.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setUpperLeft(double x, double y) {
        this.topLeft.setX(x);
        this.topLeft.setY(y);
    }

    /**
     * Sets the points of the rectangle.
     *
     * @param upper  the upper left point of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public void setPoints(Point upper, double width, double height) {
        // initializing the points of the rectangle
        Point tLeft = new Point(upper.getX(), upper.getY());
        Point tRight = new Point(upper.getX() + width, upper.getY());
        Point bLeft = new Point(upper.getX(), upper.getY() + height);
        Point bRight = new Point(upper.getX() + width, upper.getY()
                + height);
        this.topLeft = tLeft;
        this.topRight = tRight;
        this.bottomLeft = bLeft;
        this.bottomRight = bRight;
    }

    /**
     * Sets the line segments of the rectangle.
     *
     * @param upper  the upper point of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public void setSegments(Point upper, double width, double height) {
        this.left = new Line(this.bottomLeft, topLeft);
        this.right = new Line(this.bottomRight, topRight);
        this.top = new Line(this.topLeft, topRight);
        this.bottom = new Line(this.bottomLeft, bottomRight);
    }

    /**
     * Get top left point of the rectangle.
     *
     * @return the point
     */
    public Point getTopLeft() {
        return this.topLeft;
    }

    /**
     * Get the width of the rectangle.
     *
     * @return double the height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height of the rectangle.
     *
     * @return double the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left point of the rectangle.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }

    /**
     * Gets top line of the rectangle.
     *
     * @return the top
     */
    public Line getTop() {
        return new Line(this.topLeft, this.topRight);
    }

    /**
     * Gets bottom line of the rectangle.
     *
     * @return the bottom line
     */
    public Line getBottom() {
        return new Line(this.bottomLeft, this.bottomRight);
    }

    /**
     * Gets right line of the rectangle.
     *
     * @return the right line
     */
    public Line getRight() {
        return new Line(this.bottomRight, this.topRight);
    }

    /**
     * Gets left line of the rectangle.
     *
     * @return the left line
     */
    public Line getLeft() {
        return new Line(this.bottomLeft, this.topLeft);
    }

    /**
     * The method return a list of intersection point of a line and the
     * rectangle.
     *
     * @param line to check for intersecting with the rectangle lines
     * @return list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<Point>();
        // checking for intersection of the line with the rectangle's left line
        if (this.left.isIntersecting(line)) {
            // making sure there is an intersection point
            if ((this.left.intersectionWith(line)) != null) {
                // adding the intersection point to the list
                pointList.add(this.left.intersectionWith(line));
            }
        }
        // checking for intersection of the line with the rectangle's right line
        if (this.right.isIntersecting(line)) {
            // making sure there is an intersection point
            if ((this.right.intersectionWith(line)) != null) {
                // adding the intersection point to the list
                pointList.add(this.right.intersectionWith(line));
            }
        }
        //checking for intersection of the line with the rectangle's top line
        if (this.top.isIntersecting(line)) {
            // making sure there is an intersection point
            if ((this.top.intersectionWith(line)) != null) {
                // adding the intersection point to the list
                pointList.add(this.top.intersectionWith(line));
            }
        }
        //checking for intersection of the line with the rectangle's bottom line
        if (this.bottom.isIntersecting(line)) {
            // making sure there is an intersection point
            if ((this.bottom.intersectionWith(line)) != null) {
                // adding the intersection point to the list
                pointList.add(this.bottom.intersectionWith(line));
            }
        }
        /* in case there is a specific point in the list more than
         *once we filter the list
         */
        pointList = filterPoints(pointList);
        // returing the list
        return pointList;
    }

    private static java.util.List<Point>
    filterPoints(java.util.List<Point> pList) {
        // creating a new list
        java.util.List<Point> newList = new ArrayList<Point>();
        // if we sent an empty list we return null
        if (pList.isEmpty()) {
            return null;
        }
        // iterating through the list
        for (int i = 0; i < pList.size(); i++) {
            boolean toAdd = true;
            if (newList.size() == 0) {
                newList.add(pList.get(i));
            }
            // checking if the point is already in the new list
            for (Point p : newList) {
                if ((p.equals(pList.get(i)))
                        || Double.isNaN(pList.get(i).getX())
                        || Double.isNaN(pList.get(i).getY())) {
                    // no adding the point
                    toAdd = false;
                }
            }
            // if to add is true we add the point to the new list
            if (toAdd) {
                newList.add(pList.get(i));
            }
        }
        // returning the new list
        return newList;
    }
}
