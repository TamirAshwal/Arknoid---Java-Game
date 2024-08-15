// Tamir ashwal 209374867
package Arknoid.Collidiable;
import Arknoid.GameLevel;
import Arknoid.Shapes.Ball;
import Arknoid.Shapes.Point;
import Arknoid.Shapes.Rectangle;
import Arknoid.Shapes.Velocity;
import Arknoid.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private double rightScreen;
    private double leftScreen;
    private double speed;


    /**
     * Instantiates a new Paddle.
     *
     * @param rectangle   the rectangle of the paddle
     * @param color       the color of the paddle
     * @param keyboard    the keyboard to respond the user movement
     * @param leftScreen  the left boarder
     * @param rightScreen the right boarder
     * @param speed the speed of the paddle
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard,
                  double leftScreen, double rightScreen, double speed) {
        // calling the set method
        setPaddle(rectangle, color, keyboard, leftScreen, rightScreen, speed);
    }

    private void setPaddle(Rectangle rectangle, Color color,
                           KeyboardSensor keyboard,
                           double leftScreen, double rightScreen, double speed) {
        // setting the values of the paddle
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.leftScreen = leftScreen;
        this.rightScreen = rightScreen;
        this.speed = speed;
    }

    /**
     * Moving the paddle to the left.
     */
    public void moveLeft() {
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        Point upperLeft = this.rectangle.getUpperLeft();
        // checking if we pressed the right arrow key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // if so we update the rectangle's upper left point
            Point newPoint = new Point(Math.max(upperLeft.getX() - width
                    / this.speed, this.leftScreen), upperLeft.getY());
            this.rectangle = new Rectangle(newPoint, width, height);
        }
    }

    /**
     * Moving the paddle to the right.
     */
    public void moveRight() {
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        Point upperLeft = this.rectangle.getUpperLeft();
        // checking if we pressed the right arrow key
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // if so we update the rectangle's upper left point
            Point newPoint = new Point(Math.min(upperLeft.getX() + width
                    / this.speed, rightScreen - width), upperLeft.getY());
            this.rectangle = new Rectangle(newPoint, width, height);
        }
    }
    /**
     * notify the object that the time is passed.
     */
    public void timePassed() {
        moveLeft();
        moveRight();
    }
    /**
     * the method draw the paddle on the draw surface.
     * @param d the drawing surface
     */

    public void drawOn(DrawSurface d) {
        // clorong the rectangle
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getTopLeft().getX(),
                (int) this.rectangle.getTopLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        // drwaing the edges of the rectangle
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getTopLeft().getX(),
                (int) this.rectangle.getTopLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * the method returns the rectangle involved in the collision.
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        // returning the paddle's rectangle
        return this.rectangle;
    }
    /**
     * calculates the new velocity accordiong to where the ball hit on the
     * paddle.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball.
     * @return the new velocity
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = new Velocity(currentVelocity.getP1());
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        // calculating the speed using dx and dy
        double speed = Math.round(Math.sqrt((dx * dx) + (dy * dy)));
        double collision = ((collisionPoint.getX()
                - this.rectangle.getUpperLeft().getX())
                / (this.rectangle.getWidth()));

        if (collision >= 0.4 && collision <= 0.6) {
            /* if the ball collides in the middle region setting the angle to
            * 360 when 360 is up and calculating the new velocity
             */
            Velocity newV = Velocity.fromAngleAndSpeed(360, speed);
            velocity.setDx(Math.round(newV.getDx()));
            velocity.setDy(Math.round(newV.getDy()));
        }
        if (collision >= 0.2 && collision < 0.4) {
            /* if the ball collides is between the left and middle region
             * setting the angle to 330 and calculating the new velocity
             */
            Velocity newV = Velocity.fromAngleAndSpeed(330, speed);
            velocity.setDx(Math.round(newV.getDx()));
            velocity.setDy(Math.round(newV.getDy()));
        }
        if (collision > 0.6 && collision <= 0.8) {
            /* if the ball collides is between the right and middle region
             * setting the angle to 30 and calculating the new velocity
             */
            Velocity newV = Velocity.fromAngleAndSpeed(30, speed);
            velocity.setDx(Math.round(newV.getDx()));
            velocity.setDy(Math.round(newV.getDy()));
        }
        if (collision >= 0 && collision < 0.2
                || this.rectangle.getLeft().isPointOnLine(collisionPoint)) {
            /* if the ball collides is between the start and the left middle
             * region or the left side of the paddle setting the angle to 300
             * and calculating the new velocity
             */
            Velocity newV = Velocity.fromAngleAndSpeed(300, speed);
            velocity.setDx(Math.round(newV.getDx()));
            velocity.setDy(Math.round(newV.getDy()));
        }
        if (collision > 0.8 && collision <= 1
                || this.rectangle.getRight().isPointOnLine(collisionPoint)) {
            /* if the ball collides is between the right middle and the right
             * region or the left side of the paddle setting the angle to 60
             * and calculating the new velocity
             */
            Velocity newV = Velocity.fromAngleAndSpeed(60, speed);
            velocity.setDx(Math.round(newV.getDx()));
            velocity.setDy(Math.round(newV.getDy()));
        }
        // returning the new velocity
        return velocity;
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
