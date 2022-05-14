package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class VictoryAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Victory!!!!!!!!!!!!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Do you want to end this game?";
    }
}