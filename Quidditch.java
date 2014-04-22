import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Class;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * This class governs the Flappy Snitch game by manipulating the player's score and health.
 * 
 * @author Michael Hoover and Rebekah Stephenson
 * @version 4/16/14
 */
public class Quidditch extends World
{
    /**
     * The objects of this class are used to store scores in the ArrayList board.
     */
    public class Score  {
        String name;
        int score;
        /**
         * This constructor initializes the object's name and score.
         */
        public Score(String name, int score)    {
            this.name = name;
            this.score = score;
        }
    }

    private GreenfootImage backgroundImage;
    private int level = 0;
    private Font font = new Font("Arial", 1, 24);
    private ArrayList<Score> board = new ArrayList<Score>();
    private GreenfootSound background;
    
    /**
     * Constructor for objects of class Quidditch.
     * Loads the scoreboard and adds the Player and Seeker to the Quidditch pitch.
     */
    public Quidditch() throws IOException
    {    
        super(900, 600, 1); 
        background = new GreenfootSound("background.mp3");
        background.playLoop();

        backgroundImage = new GreenfootImage("QuidditchPitch_Beginning.png");
        setBackground(backgroundImage);
        loadBoard();
        addObject(new Player(.5), width(), height());
        addObject(new Snitch(3), width(), height());
        updateScoreboard();
        Greenfoot.setSpeed(43);
    }

    /**
     * If the player catches the snitch, his score is incremented and the snitch is relocated.
     * However, if the seeker catches the snitch, the player's health is decremented. The game ends
     * when the player's health reaches 0.
     */
    public void act() {
        Player player = (Player)getObjects(Player.class).get(0);
        Snitch snitch = (Snitch)getObjects(Snitch.class).get(0);
        if (player.getHealth() <= 0)    {
            //Game over
            background.stop();
            String name = JOptionPane.showInputDialog("GAME OVER!\n\nEnter name:");
            Score score = new Score(name, player.getScore());
            addHighScore(score);
            try {
                saveBoard();
            }
            catch (IOException e) {}
            displayBoard();
            Greenfoot.stop();
        }
        else if (snitch.seekerCaught())  {
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

    /**
     * This method loads the scores stored in the file "scores.txt."
     */
    private void loadBoard() throws FileNotFoundException   {
        Scanner scan = new Scanner(new File("scores.txt"));
        while (scan.hasNext())  {
            Score score = new Score(scan.next(), scan.nextInt());
            board.add(score);
        }
    }

    /**
     * This method displays the scores stored in the scoreboard.
     */
    private void displayBoard() {
        String output = "Top 10 High Scores:\n\n";
        for (Score score : board)   {
            output += score.name + " " + String.valueOf(score.score) + "\n";
        }
        JOptionPane.showMessageDialog(null, output);
    }

    /**
     * This method adds a high score to the scoreboard in its sorted order. Only the highest 10 scores
     * are kept in the scoreboard.
     */
    private void addHighScore(Score newScore) {
        Iterator<Score> list = board.iterator();

        while (list.hasNext())  {
            Score score = list.next();
            if (score.score < newScore.score)   {
                board.add(board.indexOf(score), newScore);
                break;
            }
        }
        if (board.get(board.size() - 1).score > newScore.score) {
            board.add(newScore);
        }
        if (board.size() > 10)  {
            board.remove(10);
        }
    }

    /**
     * This method saves the scoreboard to the file "scores.txt."
     */
    private void saveBoard() throws IOException    {
        Iterator<Score> list = board.iterator();
        BufferedWriter write = new BufferedWriter(new FileWriter("scores.txt"));
        while (list.hasNext())  {
            Score score = list.next();
            String output = score.name + " " + String.valueOf(score.score) + "\n";
            write.write(output);
        }
        write.close();
    }

    /**
     * This method adds new actors as the player's score increments. The bludgers and seekers also
     * become more difficult as the score increases.
     */
    public void nextLevel() {
        Player player = (Player)getObjects(Player.class).get(0);
        //Add Seeker
        if (player.getScore() == 2)   {
            addObject(new Seeker(3, 100), width(), height());
        }       
        //Add first Beater and Bludger
        if (player.getScore() == 5)  {
            addObject(new Beater(1.3, 150), width(), height());
            addObject(new Bludger(7), width(), height());
        }
        //Add second Beater and Bludger
        if (player.getScore() == 8)  {
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
        if (player.getScore() == 17 && getObjects(Voldemort.class).isEmpty()) {
            addObject(new Voldemort(), width(), height());
        }
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

    /**
     * This method updates the player's health and score displayed on the screen.
     */
    public void updateScoreboard()
    {
        GreenfootImage bg = new GreenfootImage("QuidditchPitch_Beginning.png");
        bg.setColor(Color.WHITE);
        bg.setFont(font);
        Player player = (Player)getObjects(Player.class).get(0);
        String playerHealth = "Health: ";
        int pH = player.getHealth();
        for (int i = 0; i < pH; i++) {
            playerHealth += "@";
        }
        bg.drawString(playerHealth, 50 , 30);
        String playerScore = "Score: " + player.getScore();
        bg.drawString(playerScore, 50 , 60);
        setBackground(bg);
    }
}
