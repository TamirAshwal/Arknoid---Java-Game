// Tamir ashwal 209374867
package Arknoid.Shapes;
/**
 * The type Velocity.
 */
public class Velocity {
    private Point p1;


    /**
     * Creating a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        // constructor
        Point p1 = new Point(dx, dy);
        setPoint(p1);

    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle at movement
     * @param speed the speed of the moving object
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dy = -speed * Math.cos(radian);
        double dx = speed * Math.sin(radian);
        return new Velocity(dx, dy);
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param p1 the velocity
     */
    public Velocity(Point p1) {
        setPoint(p1);
    }

    /**
     * Sets a new velocity.
     *
     * @param p1 the velocity
     */
    public void setPoint(Point p1) {
        this.p1 = p1;
    }

    /**
     * Sets dx of the velocity.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.p1.setX(dx);
    }

    /**
     * Sets dy of the velocity.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.p1.setY(dy);
    }

    /**
     * Gets p 1.
     *
     * @return the p 1
     */
    public Point getP1() {
        return this.p1;
    }

    /**
     * Get dx of the velocity.
     *
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.p1.getX();
    }

    /**
     * Get the dy of the velocity.
     *
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.p1.getY();
    }

    /**
     * Apply the velocity to a point.
     *
     * @param p the point to apply the velocity to
     * @return the point
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.p1.getX();
        double newY = p.getY() + this.p1.getY();
        return new Point(newX, newY);
    }
}
