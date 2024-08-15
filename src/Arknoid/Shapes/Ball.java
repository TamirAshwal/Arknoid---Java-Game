// Tamir ashwal 209374867
package Arknoid.Shapes;
import java.awt.Color;
import Arknoid.Collidiable.CollisionInfo;
import Arknoid.GameLevel;
import Arknoid.GameEnvironment;
import Arknoid.Sprite;
import biuoop.DrawSurface;

/**
 * The type Bouncing ball animation.
 */
public class Ball implements Sprite {
    private Point center;
    private int radios;
    private Color color;
    private Velocity velocity;
    private static final Point DEFUALT_VELOCITY = new Point(0, 0);

    private static final double EPSILON = 0.001;
    // boarders
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Ball.
     *
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        // constructor to the ball
        setBall(center, r, color, gameEnvironment);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x               the x value of the center
     * @param y               the y value of the center
     * @param r               the radios size
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        // constructor to the ball
        Point center = new Point(x, y);
        setBall(center, r, color, gameEnvironment);
    }

    /**
     * Set the data of the new ball.
     *
     * @param center          the point
     * @param r               the radios an integer
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public void setBall(Point center, int r, java.awt.Color color,
                        GameEnvironment gameEnvironment) {
        // making sure the radios can't be negative;
        if (r < 0) {
            System.out.println("your input radios can't be negative the"
                    + " new radios if the absolute value");
            r = Math.abs(r);
        }
        // making sure radios can't be 0 which case setting radios to 1
        if (r == 0) {
            System.out.println("radios can't be 0 setting radios to 1");
            r = 1;
        }
        this.radios = r;
        this.center = center;
        this.color = color;
        // handling the case the user doesn't enter any velocity
        this.velocity = new Velocity(DEFUALT_VELOCITY);
        setGameEnvironment(gameEnvironment);
    }

    /**
     * Gets x of the center point.
     *
     * @return the x of the center point
     */
    public int getX() {
        // returns the x coordinate of the center of the ball
        return (int) this.center.getX();
    }

    /**
     * Gets y of the center.
     *
     * @return the y of the center point
     */
    public int getY() {
        // returns the y coordinate of the center of the ball
        return (int) this.center.getY();
    }

    /**
     * Gets the radios of the ball.
     *
     * @return the radios of the ball
     */
    public int getSize() {
        // return the radios of the ball
        return this.radios;

    }

    /**
     * Get color.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        // returns the color
        return this.color;
    }

    /**
     * Sets velocity for the ball.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        // setting the velocity
        this.velocity = v;
    }

    /**
     * Set velocity of the ball.
     *
     * @param dx the velocity in the x axis
     * @param dy the velocity in the y axis
     */
    public void setVelocity(double dx, double dy) {
        // setting the velocity by first creating a point
        Velocity v = new Velocity(dx, dy);
        setVelocity(v);
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Get velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    private Point fixCetner(Velocity v, Point collision) {
        Point newCenter = new Point(collision.getX(), collision.getY());
        if (v.getDx() < 0) {
            newCenter.setX(newCenter.getX() + 1);
        }
        if (v.getDx() > 0) {
            newCenter.setX(newCenter.getX() - 1);
        }
        if (v.getDy() > 0) {
            newCenter.setY(newCenter.getY() - 1);
        }
        if (v.getDy() < 0) {
            newCenter.setY(newCenter.getY() + 1);
        }
        return newCenter;
    }

    /**
     * Move one step.
     * the method moving the ball according to it's velocity
     */
    public void moveOneStep() {
        /* creating a line from the center of the ball to the point where it
         * should move and checking if the line intersects in any of the
         * collidable objects in the game
         */
        Line trajectory = new Line(this.center, this.velocity
                .applyToPoint(this.center));
        CollisionInfo collision = this.gameEnvironment
                .getClosestCollision(trajectory);
        // if there is no collision we move the ball
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // there is a collision and follow the algorithm
            Velocity oldV = this.velocity;
            /* we change the center point to just slightly before the collision
             * point and them calling the hit method to change the ball velocity
             */
            this.center = fixCetner(oldV, collision.collisionPoint());
            collision.collisionObject().hit(this, collision.collisionPoint(),
                    this.velocity);
        }
    }

    /**
     * Draw the ball on the draw surface.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        // drwaing the ball
        d.setColor(this.color);
        d.fillCircle(getX(), getY(), getSize());
        d.setColor(Color.black);
        d.drawCircle(getX(), getY(), getSize());
    }

    /**
     * the method notify the ball that time is passed and calls the move one
     * step method.
     */

    public void timePassed() {
        // moving the ball
        moveOneStep();
    }

    /**
     * Add the ball to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        // adding the ball to the sprite collection
        g.addSprite(this);
    }

    /**
     * removing the ball from the game.
     * @param g the game
     */

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}