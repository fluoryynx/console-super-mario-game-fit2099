package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FireAttackAction;

public class FireAttackBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor()!= null){
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new FireAttackAction(destination.getActor(),exit.getName());
                }
            }
        }
        return null;
    }
}
