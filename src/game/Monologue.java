package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to contains all the monologue of actors.
 * Each listed character will talk at every "even" or second turn (alternating).
 * It means, they don't talk all the time. They will pick random sentences and a list of monologues by actors.
 *
 *  @author Huang GuoYueYang
 */
public class Monologue {

    /**
     * ArrayList to store all the monologues of actors
     */
    private ArrayList<String> allMonologue= new ArrayList<>();

    /**
     * Instance of Monologue
     */
    private static Monologue instance;

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Add all monologues into arraylist
     */
    public Monologue(){
        allMonologue.add("Dear Mario, I'll be waiting for you...");
        allMonologue.add("Never gonna give you up!");
        allMonologue.add("Release me, or I will kick you!");
        allMonologue.add("You might need a wrench to smash Koopa's hard shells.");
        allMonologue.add("The Princess is depending on you! You are our only hope.");
        allMonologue.add("Being imprisoned in these walls can drive a fungus crazy :(");
        allMonologue.add("You better get back to finding the Power Stars.");
        allMonologue.add("What was that sound? Oh, just a fire.");
        allMonologue.add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        allMonologue.add("Never gonna let you down!");
        allMonologue.add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
        allMonologue.add("Mugga mugga!");
        allMonologue.add("Ugha ugha... (Never gonna run around and desert you...)");
        allMonologue.add("Ooga-Chaka Ooga-Ooga!");
        allMonologue.add("Never gonna make you cry!");
        allMonologue.add("Koopi koopi koopii~!");
        allMonologue.add("Pam pam pam!");
        allMonologue.add("Slsstssthshs~! (Never gonna say goodbye~)");
        allMonologue.add("Ohmnom nom nom nom.");
    }

    /**
     * Create instance of Monologue
     * @return Return the instance of Monologue
     */
    public static Monologue getInstance(){
        if(instance == null){
            instance = new Monologue();
        }
        return instance;
    }

    /**
     * Get corresponding monologue(according to actors) by using the boundary index of monologue.
     * @param monologueIndexLowerBound Lower boundary of index
     * @param monologueIndexUpperBound Upper boundary of index
     * @return Return the corresponding monologue of specific actor
     * @throws IllegalArgumentException if the value of upper bound or lower bound is not a valid index
     */
    public String getMonologue(int monologueIndexLowerBound, int monologueIndexUpperBound) {
        if (monologueIndexLowerBound < 0 || monologueIndexUpperBound >= allMonologue.size()){
            throw new IllegalArgumentException("The value of upper bound and lower bound must be a valid index to retrieve string from allMonologue");
        }

        int currentIndex = monologueIndexLowerBound +
                rand.nextInt((monologueIndexUpperBound - monologueIndexLowerBound) + 1);
        return allMonologue.get(currentIndex);
    }
}

