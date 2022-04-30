package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.*;

import java.util.Random;

/**
 * A class that represents the trees in this game. It is an abstract class that extends HighGround
 * and implements Resettable since it have 50% to convert to dirt when reset action is selected.
 *
 * @author Kuah Jia Chen
 */
public abstract class Tree extends HighGround implements Resettable {

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * The age of this tree instance
     */
    private int age;

    /**
     * The rate to convert the ground type of the location of this tree instance to dirt
     */
    private static final int CONVERT_DIRT_RATE = 50;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, int jumpRate, int fallDamage, String treeType) {
        super(displayChar, jumpRate, fallDamage, treeType);
        this.age = 0;
        this.registerInstance(); // add to resetManager
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;

        // when the tree instance has this capability, it means reset action is selected, hence
        // it has 50% chance to convert to dirt
        if (this.hasCapability(Status.RESET_CALLED)){
            if ((rand.nextInt(100) <= CONVERT_DIRT_RATE)){
                convertToDirt(location);
            }
        }

    }

    /**
     * Return the age of this tree instance
     * @return an integer indicating the age of this tree instance
     */
    public int getAge() {
        return age;
    }

    /**
     * Add RESET_CALLED to its capability set
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }

    /**
     * Convert the ground type of current location to dirt
     * @param currentLocation The location of the tree
     */
    public void convertToDirt(Location currentLocation){
        Dirt dirt = new Dirt();
        currentLocation.setGround(dirt);
    }
}
