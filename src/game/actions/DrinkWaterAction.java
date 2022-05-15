package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.items.Bottle;

public class DrinkWaterAction extends Action {

    private static final int HEALED_HP=50;

    private Ground ground;

    public DrinkWaterAction(Ground ground){
        this.ground = ground;
    }

    public DrinkWaterAction(){

    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + Bottle.getInstance();
    }
}

