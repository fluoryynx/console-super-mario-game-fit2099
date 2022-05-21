package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.items.Bottle;

/**
 * this class is to update actor's status upon drinking water 
 * enemies drink water directly from the fountain while player drinks water from bottle
 *
 * @author Lim Fluoryynx
 */
public class DrinkWaterAction extends Action {

    /**
     * extra hp consumer gain by drinking healing water
     */
    private static final int HEALED_HP=50;

    private Ground ground;

    /**
     * constructor
     * @param ground - place where actor is standing
     */
    public DrinkWaterAction(Ground ground){
        this.ground = ground;
    }

    /**
     * empty constructor
     */
    public DrinkWaterAction(){

    }

    /**
     * give consumer effect straight away if consumer is enemy
     * minus one water slot from player's bottle if drank by player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string. For example: Mario drank Power water
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result= "";
        if (actor.hasCapability(Status.IS_ENEMY)){
            if (ground.hasCapability(Status.INCREASE_HEALTH)) {
                actor.heal(HEALED_HP);
                result = actor + " drank Healing water";
            } else if (ground.hasCapability(Status.INCREASE_BASE_DAMAGE)) {
                actor.addCapability(Status.POWER_WATER);
                result = actor + " drank Power water";
            }
            ground.addCapability(Status.DRANK_BY_ENEMY);

        } else {
            result = actor + " drank " + Bottle.getInstance().getLast();
            Bottle.getInstance().minusContent(actor);
        }
        return result;
    }

    /**
     * Describe the drink water action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return a string. For example: Mario drank Power water
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + Bottle.getInstance();
    }
}

