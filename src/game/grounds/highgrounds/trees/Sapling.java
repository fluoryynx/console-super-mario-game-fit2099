package game.grounds.highgrounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.highgrounds.trees.Mature;
import game.grounds.highgrounds.trees.Tree;
import game.items.Coin;
import game.items.magicalitems.FireFlower;

import java.util.Random;

/**
 * A class that represents the sapling tree in the Game Map. It is a new class that extends Tree.
 * It has a 10% chance to produce/spawn a coin ($20) on its location at every turn.
 * It takes another 10 turns to grow into a tall tree/Mature
 *
 * @author Kuah Jia Chen
 */
public class Sapling extends Tree {

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Character to display for sapling tree on the map
     */
    private static final char SAPLING_CHAR = 't';

    /**
     * Success rate to jump on this sapling tree
     */
    private static final int SAPLING_JUMP_RATE = 80;

    /**
     * Fall damage done to the actor when the jump is not successful
     */
    private static final int SAPLING_FALL_DAMAGE = 20;

    /**
     * The type of this tree
     */
    private static final String SAPLING_TYPE = "Sapling tree";

    /**
     * The age when it reached mature age
     */
    private static final int REACHED_MATURE_AGE = 10;

    /**
     * The rate to spawn coin
     */
    private static final int SPAWN_COIN_RATE = 10;

    /**
     * The value of the coin to spawn
     */
    private static final int SPAWN_COIN_VALUE = 20;


    /**
     * Constructor
     */
    public Sapling() {
        super(SAPLING_CHAR, SAPLING_JUMP_RATE, SAPLING_FALL_DAMAGE, SAPLING_TYPE);
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the sapling tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // change to mature when its age reached 10
        if (reachMatureAge()){
            changeToMature(location);
            if ((rand.nextInt(100) <= 50)){
                location.addItem(new FireFlower());
            }
        }

        // 10 % to spawn coin at this location
        if ((rand.nextInt(100) <= SPAWN_COIN_RATE)){
            spawnCoin(location);
        }
    }

    /**
     * Check if current age of sapling reached mature age
     * @return True if it reached mature age, else false
     */
    public boolean reachMatureAge(){
        return this.getAge() == REACHED_MATURE_AGE;
    }

    /**
     * Change the ground type of current location to mature
     * @param currentLocation The location of the sapling tree
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void changeToMature(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        currentLocation.setGround(new Mature());
    }

    /**
     * Spawn coin on this location
     * @param currentLocation The location of the sapling tree
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void spawnCoin(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        Coin coin = new Coin(SPAWN_COIN_VALUE);
        currentLocation.addItem(coin);
    }
}
