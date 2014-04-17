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
    private int splife = 15;
    /** 
     * The amount of damge the spells have.
     */
    private int damage = 1;
    public static final Vector gravity = new Vector(90, GRAVITY);
    public Spells(Vector vector) 
    {
        movement = vector;
        Greenfoot.playSound("");
        int damage = 1;
        travel();
    }

    /**
     * Act - do whatever the Spells wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(splife <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            move();
            // spells = (Spells) getOneIntersectingObject(Spells.class);
            Seeker seeker = (Seeker)getWorld().getObjects(Seeker.class).get(0);
            Player player = (Player)getWorld().getObjects(Player.class).get(0);
            if(canSee (Seeker.class)) {
                seeker.hit(damage);
            }
            else if (canSee( Player.class)) {
                player.hit(damage);
            }
            else {
                splife--;
            }
        }
    }  

    public void travel() {
        addForce(gravity);
        int angle = (movement.dx < 0) ? 0 : 180;
        addForce (new Vector(angle, 0.5));
        angle = (movement.dy > 0) ? 90 : 270;
        addForce (new Vector(angle, 0.5));
    }

    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        return actor != null;        
    }
}

