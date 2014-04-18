import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends SmoothMover //maybe necessary for bludger?
{
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static final double GRAVITY = 0.5;
    public static final Vector gravity = new Vector(90, GRAVITY);
    
    
    public Projectile() {
        movement = new Vector(30, 10);
    }
    public void act() 
    {
        // Add your action code here.
    }
     public boolean atWorldEdgeVertical()
   {
      if(getY() < 20 || getY() > getWorld().getHeight() - 20) {
         return true;
      }
      else
         return false;
   }

   public boolean atWorldEdgeHorizontal()
   {
      if(getX() < 20 || getX() > getWorld().getWidth() - 20) {
         return true;
      }
      else {
         return false;
      }
   }
    
    public void applyForces() {
        addForce(gravity);
        int angle = (movement.dx < 0) ? 0 : 180; //is projectile moving left or right?
        addForce(new Vector(angle, 0.5));
    }
}
