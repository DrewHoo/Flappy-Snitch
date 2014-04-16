import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class governs the behavior of the Seeker position on the Quidditch team. This position tries
 * to catch the Snitch. If it gets hit by Bludgers in the process, some of its life is depleted.
 * 
 * @author Rebekah Stephenson
 * @version 4/8/2014
 */
public class Seeker extends Jouster
{
    private int searchRange;
    private Vector speed;
    public int life;

    /**
     * The constructor for the Seeker initializes its speed and search range (for the Snitch).
     * It also initializes the Seeker's life.
     */
    public Seeker(double broom, int searchRange)
    {
        super("seekerleft.png", "seekerright.png", broom);
        this.searchRange = searchRange;
        life = 30;
    }

    /**
     * Act - do whatever the Seeker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        catchSnitch();
        randomInput();
        applyGravity(movement);
        move();
        imageSwap();
        if (life <= 0) {
            //getWorld().removeObject(this); This causes a runtime error when Quidditch
            //tried to perform an operation on seeker
        }
    }

    /**
     * This method changes the direction of the Seeker when a Snitch comes into range.
     * If there is no Snitch in range, it does nothing.
     */
    public void catchSnitch()
    {
        if (!getObjectsInRange(searchRange, Snitch.class).isEmpty())    {
            Snitch snitch = (Snitch)getObjectsInRange(searchRange, Snitch.class).get(0);
            double x = snitch.getX() - getX();
            double y = snitch.getY() - getY();
            int angle = (int)Math.toDegrees(Math.atan2(y, x));
            getMovement().setDirection(angle);
        }
    }

    /**
     * This method causes the Seeker to move randomly.
     */
    public void randomInput() {
        double lngth = Greenfoot.getRandomNumber((int)broom);
        int angle = Greenfoot.getRandomNumber(180) + 180;
        addForce(new Vector(angle, lngth));
    }

    /**
     * This method decreases the life of the Seeker when it is hit by a Bludger. When the life
     * drops to 0 or below, the game ends.
     */
    public void hit(int damage)
    {
        life -= damage;
    }

    /**
     * This method returns the life of the Seeker.
     */
    public int getLife()
    {
        return life;
    }

    /**
     * This method allows the Seeker to dodge the approaching Bludgers by changing direction
     * randomly.
     */
    public void dodge()
    {
        if (!getObjectsInRange(10, Bludger.class).isEmpty())    {
            this.getMovement().setDirection(Greenfoot.getRandomNumber(360));
        }
    }
    
    public void increaseRange(int range) {
        searchRange += range;
    }
}
