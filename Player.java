<<<<<<< HEAD
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class governs the behavior of the Player.
 * 
 * @author Michael Hoover and Rebekah Stephenson
 * @version 4/16/2014
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
        health = 10;
        score = 0;
    }

    /**
     * This method moves the player as the user directs.
     */
    public void act() 
    {
        applyGravity(movement);
        userInput();
        move();
        imageSwap();
    }    

    /**
     * This method decreases the player's health.
     */
    public void hit(int damage)
    {
        health -= damage;
        Quidditch qd = (Quidditch) getWorld();
        qd.updateScoreboard();
    }
    
    /**
     * This method returns the player's health.
     */
    public int getHealth()  {
        return health;
    }

    /**
     * This method controls the movement of the player according to left and right
     * keys and spacebar.
     */
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
    
    /**
     * This method increments the player's score.
     */
    public void incrementScore()    {
        score++;
    }
    
    /**
     * This method returns the player's score.
     */
    public int getScore()   {
        return score;
    }
    
    /**
     * This method decreases the player's score by the given amount.
     */
    public void decreaseScore(int amount) {
        score -= amount;
    }
}

=======
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class governs the behavior of the Player.
 * 
 * @author Michael Hoover and Rebekah Stephenson
 * @version 4/16/2014
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
        health = 10;
        score = 0;
    }

    /**
     * This method moves the player as the user directs.
     */
    public void act() 
    {
        applyGravity(movement);
        userInput();
        move();
        imageSwap();
    }    

    /**
     * This method decreases the player's health.
     */
    public void hit(int damage)
    {
        health -= damage;
        Quidditch qd = (Quidditch) getWorld();
        qd.updateScoreboard();
    }
    
    /**
     * This method returns the player's health.
     */
    public int getHealth()  {
        return health;
    }

    /**
     * This method controls the movement of the player according to left and right
     * keys and spacebar.
     */
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
    
    /**
     * This method increments the player's score.
     */
    public void incrementScore()    {
        score++;
    }
    
    /**
     * This method returns the player's score.
     */
    public int getScore()   {
        return score;
    }
    
    /**
     * This method decreases the player's score by the given amount.
     */
    public void decreaseScore(int amount) {
        score -= amount;
    }
}

>>>>>>> fa0bb712bd95593cd713296ac0e58cd81ee45cb4
