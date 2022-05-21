package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

/**
 * this class is use to enable player to obtain bottle from the toad
 * add bottle into player's inventory
 *
 * @author Lim Fluoryynx
 */
public class GetBottleAction extends Action {

    private Bottle bottle;

    /**
     * constructor
     */
    public  GetBottleAction() {
        this.bottle = Bottle.getInstance();
    }

    /**
     * add bottle into player's inventory after player obtains bottle from the toad
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string. For example: Mario gets bottle
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(bottle);
        return menuDescription(actor);
    }

    /**
     * Describe the get bottle action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return a string. For example: Mario gets bottle
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets " + bottle;
    }
}
