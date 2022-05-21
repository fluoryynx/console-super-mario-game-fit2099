package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FireAttackAction;

/**
 * A class that figures out a FireAttackAction that will attack the player automatically.
 * It implements Behaviour class.
 *
 * @author Huang GuoYueYang
 */
public class FireAttackBehaviour implements Behaviour{

    /**
     * For each exits in this game, if the player is around the enemy, enemy will attack the player by using FireAttackAction automatically
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
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
