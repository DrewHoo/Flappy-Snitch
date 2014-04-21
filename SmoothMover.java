import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A variation of an actor that maintains precise location (using doubles for the co-ordinates
 * instead of ints). It also maintains a current movement in form of a movement vector.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * 
 * @version 2.1
 */
public abstract class SmoothMover extends Actor
{
    public Vector movement;
    public double exactX;
    public double exactY;
    
    public SmoothMover() {
        this(new Vector());
    }
    
    
    
    /**
     * Create new thing initialised with given speed.
     */
    public SmoothMover(Vector movement) {
        this.movement = movement;
    }
    

    public void setMovement(Vector movement) {
        this.movement = movement;
    }

    /**
     * Move in the current movement direction.
     */
    public void move() {
        exactX = exactX + movement.getX();
        checkExactX();
        exactY = exactY + movement.getY();
        checkExactY();
        super.setLocation((int) exactX, (int) exactY);
    }

    /**
     * Set the location using exact (double) co-ordinates.
     */
    public void setLocation(double x, double y) {
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }
    
    /**
     * Set the location of this actor. Redefinition of the standard Greenfoot 
     * method to make sure the exact co-ordinates are updated in sync.
     */
    public void setLocation(int x, int y) {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    /**
     * Return the exact x co-ordinate (as a double).
     */
    public double getExactX() {
        return exactX;
    }

    /**
     * Return the exact y co-ordinate (as a double).
     */
    public double getExactY() {
        return exactY;
    }

    /**
     * Modify the current movement by adding a new vector to the existing movement.
     */
    public void addForce(Vector force) {
        movement.add(force);
    }
    
    /**
     * Accelerate the speed of this mover by the given factor. (Factors less than 1 will
     * decelerate.) The direction remains unchanged.
     */
    public void accelerate(double factor) {
        movement.scale(factor);
        if (movement.getLength() < 0.15) {
            movement.setNeutralY();
            movement.setNeutralX();
        }
    }
    
    /**
     * Return the speed of this actor.
     */
    public double getSpeed() {
        return movement.getLength();
    }
    
    /**
     * Return the current movement of this object (as a vector).
     */
    public Vector getMovement() {
        return movement;
    }
    
    /**
     * Checks the SmoothMover's X coordinate to make sure it is within the bounds of the 
     * game world.
     */
    public void checkExactX() {
        if (exactX > getWorld().getWidth()) {
            exactX = getWorld().getWidth();
            movement.revertHorizontal(.25);// causes 'bounce' off edges
        }
        if (exactX < 0) {
            exactX = 0;
            movement.revertHorizontal(.25);
        }
    }
    /**
     * Checks the SmoothMover's Y coordinate to make sure it is within the bounds of the 
     * game world.
     */
    public void checkExactY() {
        if (exactY > getWorld().getHeight()) {
            exactY = getWorld().getHeight();
            movement.revertVertical(.25); // causes 'bounce' off edges
        }
        if (exactY < 0) {
            exactY = 0;
            movement.revertVertical(.25);
        }
    }
            
}
