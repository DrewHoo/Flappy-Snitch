import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Jouster
{
    //public boolean spaceDown = false; holdover from old flight method
    public static int health;
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public Player(double broom)
    {
        super("playerleft.png", "playerright.png", broom);
        health = 3;
    }

    public void act() 
    {
        applyGravity(movement);
        userInput();
        move();
        imageSwap();
    }    

    public static void hit(int damage)
    {
        health -= damage;
        if (health <= 0)  {
            //Game over
            Greenfoot.stop();
        }
    }

    public void userInput()
    {
        //I like this method of flight but it feels too difficult for casual gaming
        /*if(Greenfoot.isKeyDown("space") && !spaceDown)
        {
            spaceDown = true;
            movement.add(verticalUp);
        }
        if(!Greenfoot.isKeyDown("space") && spaceDown)
        {
            spaceDown = false;
        }*/
        if(Greenfoot.isKeyDown("space")) {
            addForce(verticalUp);
        }
        if(Greenfoot.isKeyDown("left")) {
            addForce(horizontalLeft);
        }
        if(Greenfoot.isKeyDown("right")) {
            addForce(horizontalRight);
        }
    }
    
    public void changeDirectionalImage() {
        if (movement.dx > 0) {
            
            
        }   
    }
}

