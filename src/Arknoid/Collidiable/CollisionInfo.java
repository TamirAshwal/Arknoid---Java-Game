// Tamir ashwal 209374867
package Arknoid.Collidiable;

import Arknoid.Shapes.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * creating a new Collision info.
     *
     * @param collisionPoint   the collision point.
     * @param collidableObject the collidable object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        // constructor
        this.collidableObject = collidableObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * The collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return collidableObject;
    }
}