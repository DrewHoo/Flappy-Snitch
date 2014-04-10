import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Class;
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class QuidditchPitch here.
 * 
 * @author Michael Hoover
 * @version 4/8/14
 */
public class Quidditch extends World
{
    /**
     * Constructor for objects of class Quidditch.
     * 
     */
    private GreenfootImage backgroundImage;
    private int level = 0;
    private GreenfootImage youwin = new GreenfootImage("youwin.jpg");
    private Font font = new Font("Arial", 1, 24);
    
    public Quidditch()
    {    
        super(900, 600, 1); 
        backgroundImage = new GreenfootImage("QuidditchPitch_Beginning.png");
        setBackground(backgroundImage);
        addObject(new Player(.5), width(), height());
        addObject(new Snitch(3), width(), height());
        updateScoreboard();
        Greenfoot.setSpeed(43);
    }
    
    public void act() {
        Player player = (Player)getObjects(Player.class).get(0);
        Snitch snitch = (Snitch)getObjects(Snitch.class).get(0);
        if (player.getHealth() <= 0)    {
            //Game over
        }
        if (snitch.seekerCaught())  {
            player.hit(1);
            updateScoreboard();
            snitch.setLocation(width(), height());
        }
        else if (snitch.playerCaught())  {
            player.incrementScore();
            updateScoreboard();
            snitch.setLocation(width(), height());
            nextLevel();
        }
    }
    
    public void nextLevel() {
        Player player = (Player)getObjects(Player.class).get(0);
        //Add Seeker
        if (player.getScore() == 2 && getObjects(Seeker.class).isEmpty())   {
            addObject(new Seeker(3, 50), width(), height());
        }       
        //Add first Beater and Bludger
        if (player.getScore() == 5 && getObjects(Beater.class).isEmpty() && getObjects(Bludger.class).isEmpty())  {
            addObject(new Beater(1.3, 150), width(), height());
            addObject(new Bludger(7), width(), height());
        }
        //Add second Beater and Bludger
        if (player.getScore() == 8 && getObjects(Beater.class).size() == 1 && getObjects(Bludger.class).size() == 1)  {
            addObject(new Beater(1.3, 150), width(), height());
            addObject(new Bludger(7), width(), height());
        }
        //Increase difficulty
        if (player.getScore() % 10 == 0)  {
            Bludger bludger1 = (Bludger)getObjects(Bludger.class).get(0);
            Bludger bludger2 = (Bludger)getObjects(Bludger.class).get(1);
            Seeker seeker = (Seeker)getObjects(Seeker.class).get(0);
            bludger1.incrementDamage();
            bludger2.incrementDamage();
            seeker.increaseRange(50);
        }
        //Add Voldemort
        //if (player.getScore() == 20 && getObjects(Voldemort.class).isEmpty()) {
        //    addObject(new Voldemort(), width(), height());
        //}
        //Add Snitch each time level increments
    }

    public void youWin() {
        setBackground(youwin);
    }
    
    /**
     * gets a random number less than the width of the world.
     */
    public int width()
    {
        return Greenfoot.getRandomNumber(getWidth());
    }
    
    /**
     * gets a random number less than the height of the world.
     */
    public int height()
    {
        return Greenfoot.getRandomNumber(getHeight());
    }
    public void updateScoreboard()
    {
        GreenfootImage bg = new GreenfootImage("QuidditchPitch_Beginning.png");
        bg.setColor(Color.WHITE);
        bg.setFont(font);
        String playerHealth = new String("Health: ");
        Player player = (Player)getObjects(Player.class).get(0);
        int pH = player.getHealth();
        for (int i = 0; i <= pH; i++) {
            playerHealth += "@";
        }
        bg.drawString(playerHealth, 200 , 20);
        String playerScore = new String("Score: ");
        playerScore += player.getScore();
        bg.drawString(playerScore, 200 , 50);
        setBackground(bg);
    }
    
}
