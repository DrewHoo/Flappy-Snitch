import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class governs the behavior of the Snitch.
 * 
 * @author Rebekah Stephenson 
 * @version 4/1/2014
 */
public class Snitch extends Jouster
{   
    /**
     * The constructor initializes the Snitch with a speed. It also plays a sound as the Snitch is
     * created.
     */
    public Snitch(double broom)
    {
        super("snitchleft.png", "snitchright.png", broom);
        Greenfoot.playSound("birds001.wav");
    }

    /**
     * This method determines if the Snitch has been caught by the Seeker or the Player. If it has,
     * the method returns true and plays a sound. Otherwise, it returns false.
     */
    public boolean seekerCaught()
    {
        if (isTouching(Seeker.class))   {
            //Greenfoot.playSound("birds001.wav"); GAME OVER
            return true;
        }
        return false;
    }
    
    public boolean playerCaught()
    {
        if (isTouching(Player.class))   {
            Greenfoot.playSound("birds001.wav");
            return true;
        }
        return false;
    }
    
    /**
     * The Snitch flies as normal until it is caught by either the Seeker or the Player. When this
     * happens, the game ends.
     */
    public void act() 
    {
        if (seekerCaught()) {
            //game over
        }
        if (playerCaught()) {
            Quidditch quid = (Quidditch) getWorld();
            quid.nextLevel();
        }
        else {
        applyGravity(movement);
        randomInput();
        imageSwap();
        move();
    }
    }    
    
    /**
     * This method moves the Snitch randomly.
     */
    public void randomInput() {
        double lngth = Greenfoot.getRandomNumber((int)broom);
        int angle = Greenfoot.getRandomNumber(180) + 180;
        addForce(new Vector(angle, lngth));
    }
}
