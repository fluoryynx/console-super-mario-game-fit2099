package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.grounds.fountains.Fountain;
import game.items.Bottle;
import game.waters.Water;

/**
 * This class is used to enable player to refil water from fountains into water bottle
 * Hence, whenever the user perform this action, it will add that particular water instance
 * on player's bottle
 *
 * @author Lim Fluoryynx
 */
public class TakeWaterAction extends Action {

    /**
     * The water instance that will be added to player's bottle
     */
    private Water water;

    /**
     * The fountain that provide the water
     */
    private Fountain fountain;

    /**
     * constructor
     * @param water - water of the fountain
     * @param fountain - fountain in the game map
     */
    public TakeWaterAction(Water water,Fountain fountain){
        this.water=water;
        this.fountain=fountain;
    }

    /**
     * if player has a bottle, add one slot of water into player's bottle and minus one slot of water from the fountain
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string. For example: Mario refills Healing water from Health fountain
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HAS_BOTTLE)) {
            Bottle.getInstance().addContent(water);
            fountain.minusContent();
        }
        return menuDescription(actor);
    }

    /**
     * Describe the take water action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return a string. For example: Mario refills Healing water from Health fountain
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills " + water + " from " + fountain ;
    }
}
