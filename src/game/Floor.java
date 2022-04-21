package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }
    private boolean canEnter = false;

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            canEnter = true;
        }
        else if (actor.hasCapability(Status.IS_ENEMY)){
            canEnter = false;
        }
        return canEnter;
    }
}