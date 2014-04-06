import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beater extends Jouster
{
    private int searchRange;
    public Beater(double broom, int searchRange)
    {
        super("beaterleft.png", "beaterright.png", broom);
        this.searchRange = searchRange;

    }

    /**
     * Act - do whatever the Beater wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
      applyGravity(movement);
      randomInput();
      followBludger();
      move();
      imageSwap();
    }  

    /**
     * This version creates consistent rando movement, but it's poorly written and unclear and
     * it isn't obvious how this would affect a beater's ability to find the bludger.
     */

    public void randomInput()//this method creates consistent rando movement, but
    {
     if ((int)movement.dx % 2 == 0 && getX() < 450) {
         int angle  = Greenfoot.getRandomNumber(45) + 315;
         addForce(new Vector(angle, broom));
        }
     if ((int)movement.dx % 2 == 0 && getX() > 450) {
         int angle = Greenfoot.getRandomNumber(45) + 180;
         addForce(new Vector(angle, broom));
     }
    }

    /**
     * This method changes allows the Beaters to follow the Bludgers.
     * If there is no Bludger in range, it just moves back and forth.
     */
    public void followBludger()
    {
        if (getObjectsInRange(searchRange, Bludger.class).size() > 0)    {
            //Bludger bludger = (Bludger)getObjectsInRange(10, Bludger.class).get(0);

            Bludger bludger = (Bludger)getObjectsInRange(searchRange, Bludger.class).get(0);
            double x = bludger.getX() - getX();
            double y = bludger.getY() - getY();
            int angle = (int)Math.toDegrees(Math.atan2(y, x));
            //if (angle < 0) {
            //    angle = 360 - angle;                       
            //}
            addForce(new Vector(angle, 2));
            //setLocation(bludger.getX(), bludger.getY());
        }
    }
}
