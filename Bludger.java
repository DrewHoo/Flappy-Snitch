
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bludger here.
 * 
 * @author (Michael Hoover) 
 * @version (04.04.14)
 */
public class Bludger extends Projectile
{
   /**
    * Act - do whatever the Bludger wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
   int damage;
   int speed;
   public boolean canSeePlayer = false;
   public Bludger(int speed) {
      this.speed = speed;
      damage = 1;
   }

   public void act() 
   {
      destroy();
      if (getY() == 0 || getY() == 600) {
         movement.revertVertical(1);
      }
      if (getX() == 0 || getX() == 900) {
         movement.revertHorizontal(1);
      }
      if (lookForBeater()) {
          hitTowardPlayer();
      }
      applyForces();
      move();
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

   public boolean lookForPlayer()
   {
      if ( canSee(Player.class) ) 
      {
         return true;
      }
      return false;
   }

   public boolean lookForBeater()
   {
      if ( canSee(Beater.class) ) 
      {
         return true;
      }
      return false;
   }

   public boolean lookForSeeker()
   {
      if ( canSee (Seeker.class) )
      {
         return true;
      }
      return false;
   }
   
   public void destroy() {
      if (lookForSeeker() ) {
         Seeker seeker = (Seeker)getWorld().getObjects(Seeker.class).get(0);
         seeker.hit(damage);
         canSeePlayer = false;
      }
      if (lookForPlayer() && !canSeePlayer) {
         Player player = (Player)getWorld().getObjects(Player.class).get(0);
         player.hit(damage);
         canSeePlayer = true;
      }
      if (!lookForPlayer() && canSeePlayer) {
         canSeePlayer = false;
      }
   }

   public boolean canSee(Class clss)
   {
      Actor actor = getOneObjectAtOffset(0, 0, clss);
      return actor != null;        
   }
   
   public void hitTowardPlayer()
   {
        Player player = (Player)getObjectsInRange(1082, Player.class).get(0);
        double x = player.getX() - getX();
        double y = player.getY() - getY();
        int angle = (int)Math.toDegrees(Math.atan2(y, x));
        addForce(new Vector(angle, speed));
   }
   
   public void incrementDamage()    {
       damage++;
    }
}