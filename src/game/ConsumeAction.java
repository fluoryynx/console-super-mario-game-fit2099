package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.MagicalItem;

public class ConsumeAction extends Action {

    private MagicalItem magicalItem;

    public ConsumeAction(MagicalItem magicalItem) {
        this.magicalItem = magicalItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        magicalItem.updateStatus(actor);
        actor.removeItemFromInventory(magicalItem);
        BuffManager.getInstance().appendResetInstance(magicalItem);
        return actor + " consumed the " + magicalItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + magicalItem;
    }
}
