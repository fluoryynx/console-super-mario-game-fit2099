package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents the lava in the Game Map, Besides that,
 * it will inflict 15 damage per turn when the player steps on them and
 * enemies cannot step on this lava.
 *
 * @author Kuah Jia Chen
 */
public class Lava extends Ground {

    /**
     * Character to display for lava on the map
     */
    private static final char LAVA_CHAR = 'L';

    /**
     * The damage cause by lava per turn
     */
    private static final char DAMAGE_PER_TURN = 15;

    /**
     * Constructor of Lava class
     */
    public Lava() {
        super(LAVA_CHAR);
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // get the actor current standing on this location and hurt 15 damage
        Actor currentActor = location.getActor();
        if (currentActor != null){
            currentActor.hurt(DAMAGE_PER_TURN);
            if (currentActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                if(currentActor.hasCapability(Status.TALL)){
                    currentActor.removeCapability(Status.TALL);
                }
            }
            // if the actor is dead, remove it
            if (!currentActor.isConscious()){
                GameMap map = location.map();
                map.removeActor(currentActor);
            }
        }
    }

    /**
     * Returns true if the actor is not enemy
     * @param actor the Actor to check
     * @return true if the actor is not enemy, else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.IS_ENEMY);
    }

}
