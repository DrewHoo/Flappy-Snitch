import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Voldemort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Voldemort extends Projectile
{
    private int reloadDelayCount;
    private int wandReload;
    public Voldemort() {
        reloadDelayCount = 0;
        wandReload = 5;
    }

    /**
     * Act - do whatever the Voldemort wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        fire();
        if(reloadDelayCount >= wandReload) {
            reloadDelayCount++;
        }
        if (atWorldEdgeVertical() == true) {
            movement.revertVertical(1);
        }
        if (atWorldEdgeHorizontal() == true) {
            movement.revertHorizontal(1);
        }
    }   

    /**
     * Fire a spell;
     */
    private void fire() 
    {
        Seeker seeker = (Seeker)getWorld().getObjects(Seeker.class).get(0);
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
        if (player.getX() == getX()) {
            int angle = (getY() > player.getY()) ? 270 : 90;
            Spells sp = new Spells(new Vector(10, angle));
            getWorld().addObject(sp, getX(), getY());
            reloadDelayCount = 0;
        }
        if (player.getY() == getY()) {
            int angle = (getX() > player.getX()) ? 180 : 0;
            Spells sp = new Spells(new Vector(10, angle));
            getWorld().addObject(sp, getX(), getY());
            reloadDelayCount = 0;
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

}
