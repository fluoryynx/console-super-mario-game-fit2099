package game.grounds.highgrounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Goomba;
import game.items.magicalitems.FireFlower;

import java.util.Random;

/**
 * A class that represents the sprout tree in the Game Map. It is a new class that extends Tree.
 * It has a 10% chance to spawn Goomba on its position in every turn.
 * In addition, it takes 10 turns to grow into a small tree/Sapling
 *
 * @author Kuah Jia Chen
 */
public class Sprout extends Tree {

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Character to display for sprout tree on the map
     */
    private static final char SPROUT_CHAR = '+';

    /**
     * Success rate to jump on this sprout tree
     */
    private static final int SPROUT_JUMP_RATE = 90;

    /**
     * Fall damage done to the actor when the jump is not successful
     */
    private static final int SPROUT_FALL_DAMAGE = 10;

    /**
     * The type of this tree
     */
    private static final String SPROUT_TYPE = "Sprout tree";

    /**
     * The age when it reached sapling age
     */
    private static final int REACHED_SAPLING_AGE = 10;

    /**
     * The rate to spawn goomba
     */
    private static final int SPAWN_GOOMBA_RATE = 10;

    /**
     * Constructor
     */
    public Sprout() {
        super(SPROUT_CHAR, SPROUT_JUMP_RATE, SPROUT_FALL_DAMAGE, SPROUT_TYPE);
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the sprout tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // change to sapling when its age reached 10
        if (reachSaplingAge()){
            changeToSapling(location);
            if ((rand.nextInt(100) <= 50)){
                location.addItem(new FireFlower());
            }
        }

        // 10 % to spawn goomba if there is no actor at this location
        if ((rand.nextInt(100) <= SPAWN_GOOMBA_RATE) && !location.containsAnActor()){
            spawnGoomba(location);
        }
    }

    /**
     * Check if current age of sprout reached sapling age
     * @return True if it reached sapling age, else false
     */
    public boolean reachSaplingAge(){
        return this.getAge() == REACHED_SAPLING_AGE;
    }

    /**
     * Change the ground type of current location to sprout
     * @param currentLocation The location of the sprout tree
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void changeToSapling(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        currentLocation.setGround(new Sapling());
    }

    /**
     * Spawn goomba at this current location
     * @param currentLocation The location of the sprout tree
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void spawnGoomba(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        Goomba goomba = new Goomba();
        currentLocation.addActor(goomba);
    }
}
