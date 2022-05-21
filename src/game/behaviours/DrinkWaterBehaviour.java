package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.actions.DrinkWaterAction;

/**
 * class that allows enemies to drink water from the fountain It will only be available if the fountain
 * is not empty
 *
 * @author Lim Fluoryynx
 */
public class DrinkWaterBehaviour implements Behaviour{

    /**
     * empty constructor
     */
    public DrinkWaterBehaviour() {;
    }

    /**
     * return DrinkWaterAction if enemies stand on the fountain
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return DrinkWaterAction instance if the ground is fountain, else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Ground currentGround = map.locationOf(actor).getGround();
        if (currentGround.hasCapability(Status.IS_FOUNTAIN)){
            return new DrinkWaterAction(currentGround);
        }
        return null;
    }
}
