package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Koopa;

import java.util.Random;

/**
 * A class that represents the mature tree in the Game Map. It is a new class that extends Tree.
 *
 * @author Kuah Jia Chen
 */
public class Mature extends Tree{

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Character to display for mature tree on the map
     */
    private static final char MATURE_CHAR = 'T';

    /**
     * Success rate to jump on this mature tree
     */
    private static final int MATURE_JUMP_RATE = 70;

    /**
     * Fall damage done to the actor when the jump is not successful
     */
    private static final int MATURE_FALL_DAMAGE = 30;

    /**
     * The type of this tree
     */
    private static final String MATURE_TYPE = "Mature tree";

    /**
     * The rate to spawn koopa
     */
    private static final int SPAWN_KOOPA_RATE = 15;

    /**
     * Pointer that used to check if it is the right time to spawn sprout
     */
    private int everyFiveTurnPointer = 0;

    /**
     * The rate to wither
     */
    private static final int WITHER_RATE = 20;

    /**
     * Constructor
     */
    public Mature() {
        super(MATURE_CHAR, MATURE_JUMP_RATE, MATURE_FALL_DAMAGE, MATURE_TYPE);
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the mature tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        everyFiveTurnPointer++;

        // 15 % to spawn koopa if there is no actor at this location
        if ((rand.nextInt(100) <= SPAWN_KOOPA_RATE) && !location.containsAnActor()){
            spawnKoopa(location);
        }

        if (everyFiveTurnPointer == 5){
            int noFertileAvailable = numberOfFertile(location);
            if (noFertileAvailable > 0){
                // it means there is at least one fertile in the surrounding of this tree
                // therefore, we will call the following method to grow a sprout
                changeOneFertileToSprout(location);
            }
            // reset the pointer
            everyFiveTurnPointer = 0;
        }

        // 20% chance to wither every turn
        if ((rand.nextInt(100) <= WITHER_RATE)){
            changeToDirt(location);
        }
    }

    /**
     * Spawn koopa at this location
     * @param currentLocation current location of this mature tree instance
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void spawnKoopa(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        Koopa koopa = new Koopa();
        currentLocation.addActor(koopa);
    }

    /**
     * Check there is how many fertile ground in the surrounding of this mature tree
     * @param currentLocation current location of this mature tree instance
     * @return an integer represents the number of fertile grounds surrounding this mature tree
     * @throws IllegalArgumentException if currentLocation is null
     */
    public int numberOfFertile(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        int pointer = 0;
        for (Exit exit: currentLocation.getExits()){
            if (exit.getDestination().getGround().hasCapability(Status.IS_FERTILE)){
                pointer++;
            }
        }
        return pointer;
    }

    /**
     * Randomly change one fertile ground that is surrounding this mature tree to sprout
     * @param currentLocation current location of this mature tree instance
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void changeOneFertileToSprout(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        boolean changedToSprout = false;
        while (!changedToSprout){
            int amountOfExit = currentLocation.getExits().size();
            int index = rand.nextInt(amountOfExit);
            Exit exit = currentLocation.getExits().get(index);
            Ground currentGround = exit.getDestination().getGround();
            if (currentGround.hasCapability(Status.IS_FERTILE)){
                exit.getDestination().setGround(new Sprout());
                changedToSprout = true;
            }
        }
    }

    /**
     * Change the ground type of current location of mature tree to dirt
     * @param currentLocation current location of this mature tree instance
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void changeToDirt(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        Dirt dirt = new Dirt();
        currentLocation.setGround(dirt);
    }

}
