package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


public class AttackBehaviour implements Behaviour {

    public AttackBehaviour (){
    }

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor()!= null){
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(destination.getActor(),exit.getName());
                }
            }
        }
        return null;
    }
}
