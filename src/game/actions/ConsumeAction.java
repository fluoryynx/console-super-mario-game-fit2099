package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.BuffManager;
import game.items.magicalitems.MagicalItem;

/**
 * This class is use to update player’s status and remove magical items
 * from players inventory whenever player consumes the item.
 *
 * @author Lim Fluoryynx
 */
public class ConsumeAction extends Action {
    /**
     * items player can consume
     */
    private MagicalItem magicalItem;

    /**
     * Constructor
     * @param magicalItem the magical item that player allows consuming
     */
    public ConsumeAction(MagicalItem magicalItem) {
        this.magicalItem = magicalItem;
    }

    /**
     * update status of actor depending on magical item consumed
     * Add the item to the actor's inventory.
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI. e.g " Player consumed the Super Mushroom"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        magicalItem.updateStatus(actor);
        actor.removeItemFromInventory(magicalItem);
        BuffManager.getInstance().appendMagicalItemInstance(magicalItem);
        return actor + " consumed the " + magicalItem;
    }

    /**
     * Describe the consume action in a format suitable for displaying in the menu.
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return  a string, e.g. "Mario consumes the Power Star"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + magicalItem;
    }
}
