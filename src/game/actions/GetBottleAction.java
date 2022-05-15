package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

public class GetBottleAction extends Action {

    private Bottle bottle;

    public  GetBottleAction() {
        this.bottle = Bottle.getInstance();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(bottle);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets " + bottle;
    }
}
