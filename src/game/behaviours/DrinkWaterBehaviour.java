package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.actions.DrinkWaterAction;

public class DrinkWaterBehaviour implements Behaviour{

    public DrinkWaterBehaviour() {;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Ground currentGround = map.locationOf(actor).getGround();
        if (currentGround.hasCapability(Status.IS_FOUNTAIN)){
            return new DrinkWaterAction(currentGround);
        }
        return null;
    }
}
