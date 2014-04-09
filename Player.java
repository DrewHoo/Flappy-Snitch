import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Jouster
{
    private int score;
    private int health;
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

    public void hit(int damage)
    {
        health -= damage;
    }
    
    public int getHealth()  {
        return health;
    }

    public void userInput()
    {
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
    
    public void incrementScore()    {
        score++;
    }
    
    public int getScore()   {
        return score;
    }
    
    public void decreaseScore(int amount) {
        score -= amount;
    }
}

