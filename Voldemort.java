import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Voldemort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Voldemort extends Projectile
{
    private int reloadDelayCount; //How long its been since we fired the gun.
    private int wandReload; // The minimum delay between firing.
    private Vector movement;
    private int count = 0;
    public Voldemort() {
        reloadDelayCount = 0;
        wandReload = 20;
        Greenfoot.playSound("Voldemort.wav");
    }

    /**
     * Act - do whatever the Voldemort wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (count == 40) {
            fly();
            count = 0;
        }
        if (getY() == 0 || getY() == 600) {
            movement.revertVertical(1);
        }
        if (getX() == 0 || getX() == 900) {
            movement.revertHorizontal(1);
        }
        move();
        if(reloadDelayCount >= wandReload) {
            fire();
        }
        count++;
        reloadDelayCount++;
    }   

    /**
     * Fire a spell.
     */
    private void fire() 
    {
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
        if (player.getX() >= getX()) {
            int angle = (getX() > player.getY()) ? 90 : 270;
            Spells sp = new Spells(new Vector(angle, 10));
            getWorld().addObject(sp, getX(), getY());
            sp.move();
            reloadDelayCount = 0;
        }
        if (player.getY() >= getY()) {
            int angle = (getX() > player.getX()) ? 180 : 0;
            Spells sp = new Spells(new Vector(angle, 10));
            getWorld().addObject(sp, getX(), getY());
            sp.move();
            reloadDelayCount = 0;
        }             
    }

    /**
     * Movement of Voldemort.
     */
    public void fly() {
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
        int diffX = player.getX() - getX();
        int diffY = player.getX() - getY();
        if (Math.abs(diffX) < Math.abs(diffY)) {
            if (diffX >= 0) {
                movement = new Vector(180, 10);
                setMovement(movement);
            }
            else {
                movement = new Vector(0, 10);
                setMovement(movement);
            } 
        }
        else {
            if (diffY < 0) {
                movement = new Vector(90, 10);
                setMovement(movement);
            }
            else {
                movement = new Vector(270, 10);
                setMovement(movement);
            }
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

}