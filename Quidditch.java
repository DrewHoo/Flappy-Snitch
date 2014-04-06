import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Class;
/**
 * Write a description of class QuidditchPitch here.
 * 
 * @author Michael Hoover
 * @version 4/4/14
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
    
    public Quidditch()
    {    
        super(900, 600, 1); 
        backgroundImage = new GreenfootImage("QuidditchPitch_Beginning.png");
        setBackground(backgroundImage);
        addObject(new Player(.5), width(), height());
        addObject(new Snitch(3), width(), height());
        //commented out for beta
        /*addObject(new Bludger(7), width(), height()); 
        addObject(new Bludger(7), width(), height());
        addObject(new Beater(1.3, 150), width(), height());
        addObject(new Seeker(3, 50), width(), height());
        */
        Greenfoot.setSpeed(43);
    }
    
    public void act() {
        
    }
    
    public void nextLevel() {
        level++;
        List list = getObjects(null);
        Player player = (Player)getObjects(Player.class).get(0);
        list.remove(player);
        removeObjects(list);
        switch (level) {
            case 1:
                level1();
                break;
            case 2:
                level2();
                break;
            case 3:
                level3();
                break;
            case 4:
                level4();
                break;
            case 5:
                youWin();
            }
                
    }
    
    public void level1() {
        addObject(new Snitch(3), width(), height());
        addObject(new Bludger(10), width(), height());
        addObject(new Bludger(10), width(), height());
        addObject(new Beater(1.2, 125), width(), height());
        Greenfoot.setSpeed(43);
    }
    
    public void level2() {
        addObject(new Snitch(3), width(), height());
        addObject(new Bludger(13), width(), height());
        addObject(new Bludger(13), width(), height());
        addObject(new Beater(1.5, 150), width(), height());
        addObject(new Seeker(3, 50), width(), height());
        Greenfoot.setSpeed(43);
    }
    
    public void level3() {
        addObject(new Snitch(3), width(), height());
        addObject(new Bludger(17), width(), height());
        addObject(new Bludger(17), width(), height());
        addObject(new Beater(1.5, 150), width(), height());
        addObject(new Beater(1.5, 150), width(), height());
        addObject(new Seeker(3, 75), width(), height());
        Greenfoot.setSpeed(43);
    }
    
    public void level4() {
        addObject(new Snitch(3), width(), height());
        addObject(new Bludger(20), width(), height());
        addObject(new Bludger(20), width(), height());
        addObject(new Beater(1.5, 150), width(), height());
        addObject(new Beater(1.5, 150), width(), height());
        addObject(new Seeker(3, 100), width(), height());
        Greenfoot.setSpeed(43);
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
}
