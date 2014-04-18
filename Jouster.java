import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jouster here.
 * 
 * @author Michael Hoover
 * @version 03.21.14
 */
public abstract class Jouster extends SmoothMover
{
    //fields
    public static final double GRAVITY = 0.5;
    public static final Vector gravity = new Vector(90, GRAVITY); //not sure whether to make this different for dif classes
    public Vector horizontalLeft;
    public Vector horizontalRight;
    public Vector verticalUp;
    public double broom; // larger is faster
    public GreenfootImage leftImage;
    public GreenfootImage rightImage;
    public Jouster(String left, String right, double broom)
    {
        leftImage = new GreenfootImage(left);
        rightImage = new GreenfootImage(right);
        this.broom = broom;
        horizontalLeft = new Vector(180, broom);
        horizontalRight = new Vector(0, broom);
        verticalUp = new Vector(270, (broom + GRAVITY));
        movement = new Vector(270, 0);
    }
    
    public Jouster() {
    }
    
    /**
     * Swaps the image based on which way the Jouster is moving. It would be nice
     * to upgrade this to base it on which way Jouster is accelerating.
     */
    
    public void imageSwap() {
        GreenfootImage image = (movement.dx < 1) ? leftImage : rightImage;
        setImage(image);
    }
    
    /**
     * Act - do whatever the Jouster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void applyGravity(Vector movement) {
        movement.add(gravity);
    }
    
    
}
