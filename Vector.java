/**
 * A 2D vector.
 * 
 * @author Poul Henriksen
 */
public class Vector
{
    double dx = 0;
    double dy = 0;
    int direction = 0;
    double length;

    public Vector()
    {

    }

    public Vector(int direction, double length)
    {
        this.length = length;
        this.direction = direction;
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction)); 
    }

    /**
     * Set the direction of this vector.
     */
    public void setDirection(int direction) {
        this.direction = direction;
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction)); 
    }

    public void setDirection(double xDirection, double yDirection) {
        this.direction = (int) Math.toDegrees(Math.sqrt(yDirection * yDirection + xDirection * xDirection));
        dx = length * xDirection;
        dy = length * yDirection; 
    }

    /**
     * Add other vector to this vector.
     */
    public void add(Vector other) {
        dx += other.dx;
        dy += other.dy; 
        this.direction = (int) Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    } 

    /**
     * Return the x offset of this vector.
     */
    public double getX() {
        return dx;
    }

    /**
     * Return the y offset of this vector.
     */
    public double getY() {
        return dy;
    }

    /**
     * Return the current direction (in degrees).
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Return the current length of the vector.
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Scale this vector up (factor greater than 1) or down (factor less than 1). 
     * The direction remains unchanged.
     */
    public void scale(double factor) 
    {
        length = length * factor;
        updateCartesian();
    }
    
    /**
     * Set this vector to the neutral vector (length 0).
     */
    public void setNeutralX() {
        dx = 0.0;
        updatePolar();
    }
    
    public void setNeutralY() {
        dy = 0.0;
        updatePolar();
    }
    
    /**
     * Revert to horizontal component of this movement vector.
     */
    public void revertHorizontal(double factor) {
        dx = -dx*factor;
        updatePolar();
    }
    
    /**
     * Revert to vertical component of this movement vector.
     */
    public void revertVertical(double factor) {
        dy = -dy*factor;
        updatePolar();
    }

    /**
     * Update the direction and length from the current dx, dy.
     */
    private void updatePolar() 
    {
        this.direction = (int) Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }   
    
    /**
     * Update dx and dy from the current direction and length.
     */
    private void updateCartesian() 
    {
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction));   
    }

    /**
     * Create a copy of this vector.
     */
    public Vector copy() {
        Vector copy = new Vector();
        copy.dx = dx;
        copy.dy = dy;
        copy.direction = direction;
        copy.length = length;
        return copy;
    } 
}