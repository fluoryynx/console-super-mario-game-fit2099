package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building
 * It extends from Ground class It only can be entered by actors that are not
 * hostile to enemy, enemies are not allowed to enter
 *
 * @author Huang GuoYueYang
 */
public class Floor extends Ground {

    /**
     * Character to display for floor on the map
     */
    private static final char FLOOR_CHAR = '_';

    /**
     * Constructor
     */
    public Floor() {
        super(FLOOR_CHAR);
    }

    /**
     * A boolean indicating whether it can be entered a particular actor
     */
    private boolean canEnter = false;


    /**
     * Returns true if an Actor can enter this location.
     * Actors can enter a location if the terrain is passable and there isn't an Actor there already.
     * Game rule. One actor per location.
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // Player can enter floor
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            canEnter = true;
        }
        // Enemy cannot enter floor
        else if (actor.hasCapability(Status.IS_ENEMY)){
            canEnter = false;
        }
        return canEnter;
    }
}