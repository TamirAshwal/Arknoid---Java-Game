// Tamir ashwal 209374867
package Arknoid;

import Arknoid.Collidiable.Collidable;
import Arknoid.Collidiable.CollisionInfo;
import Arknoid.Shapes.Line;
import Arknoid.Shapes.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private List<Collidable> collidableList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        //creating a list of a new collidables
        collidableList = new ArrayList<>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        // add the given collidable to the environment.
        collidableList.add(c);

    }

    /**
     * remove collidable.
     * @param c collidable we want to remove
     */

    public void removeCollidableFromList(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //Find all intersections with the collidable objects.
        List<Point> listOfIntersectionPoints = new ArrayList<>();
        for (int i = 0; i < collidableList.size(); i++) {
            listOfIntersectionPoints.add(trajectory.
                    closestIntersectionToStartOfLine(collidableList.get(i).
                            getCollisionRectangle()));
        }
        // no intersection points so we return null
        if (listOfIntersectionPoints.isEmpty()) {
            return null;
        }
        // finding the closest intersectoin point to the start of the line
        double minLength = Double.MAX_VALUE;
        Point closestPoint = null;
        Point currenPoint;
        //Find the first collision
        for (int i = 0; i < listOfIntersectionPoints.size(); i++) {
            currenPoint = listOfIntersectionPoints.get(i);
            if (currenPoint != null) {
                if (currenPoint.distance(trajectory.start()) < minLength) {
                    closestPoint = currenPoint;
                    minLength = currenPoint.distance(trajectory.start());
                }
            }
        }
        if (closestPoint == null) {
            return null;
        }
        /* returning the collidable object which index is the one with the
         *closest intersection point
         */
        Collidable collidable = collidableList.
                get(listOfIntersectionPoints.indexOf(closestPoint));
        return new CollisionInfo(closestPoint, collidable);
    }
}