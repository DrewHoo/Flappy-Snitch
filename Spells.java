import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spells here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spells extends Projectile
{
    /** Spell loses life each act as long as nothing is in range to hit. Eventually disappears
     */
    private int splife = 100;
    /** 
     * The amount of damage the spells have.
     */
    private int damage = 1;
    private boolean canSeePlayer = false;
    public static final Vector gravity = new Vector(90, GRAVITY);
    public Spells(Vector vector) 
    {
        movement = vector;
        //Greenfoot.playSound("");
        int damage = 1;
    }

    /**
     * Act - do whatever the Spells wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(splife <= 0 || getX() == 0 || getX() == 900 || getY() == 0 || getY() == 600) {
            getWorld().removeObject(this);
        } 
        else {
            move();
            destroy();
            splife--;
        }
    }
    
    public void destroy() {
      if (canSee(Player.class) && !canSeePlayer) {
         Player player = (Player)getWorld().getObjects(Player.class).get(0);
         player.hit(damage);
         canSeePlayer = true;
      }
      if (!canSee(Player.class) && canSeePlayer) {
         canSeePlayer = false;
      }
   }

    public void dragY() {
        int angle = (movement.dy > 0) ? 90 : 270;
        addForce (new Vector(angle, 0.5));
    }

    public void dragX() {
        int angle = (movement.dx < 0) ? 0 : 180;
        addForce (new Vector(angle, 0.5));
    }

    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        return actor != null;        
    }
}

