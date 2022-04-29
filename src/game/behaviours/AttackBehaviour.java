package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.Behaviour;

/**
 * A class that figures out a AttackAction that will attack the player automatically
 *
 * @author Huang GuoYueYang
 */
public class AttackBehaviour implements Behaviour {

    public AttackBehaviour (){
    }

    /**
     * For each exits in this game, if the player is around the enemy, enemy will attack the player automatically
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
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
