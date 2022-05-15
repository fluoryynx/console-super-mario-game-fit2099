package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.grounds.fountains.Fountain;
import game.items.Bottle;
import game.waters.Water;

public class TakeWaterAction extends Action {

    private Water water;
    private Fountain fountain;

    public TakeWaterAction(Water water,Fountain fountain){
        this.water=water;
        this.fountain=fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HAS_BOTTLE)) {
            Bottle.getInstance().addContent(water);
            fountain.minusContent();
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills " + water + " from " + fountain ;
    }
}
