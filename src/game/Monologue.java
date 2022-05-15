package game;

import java.util.ArrayList;
import java.util.Random;

public class Monologue {

    private ArrayList<String> allMonologue= new ArrayList<>();

    private static Monologue instance;

    private final Random rand = new Random();

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

    public static Monologue getInstance(){
        if(instance == null){
            instance = new Monologue();
        }
        return instance;
    }

    public String getMonologue(int monologueIndexLowerBound, int monologueIndexUpperBound) {
        int currentIndex = monologueIndexLowerBound +
                rand.nextInt((monologueIndexUpperBound - monologueIndexLowerBound) + 1);
        String currentString = allMonologue.get(currentIndex);
        return currentString;
    }

}

