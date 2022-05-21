package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * VictoryAction class is a class when Player kill Bowser(obtain a key form it), player can interact with Princess Peach
 * to end this game.
 * After game is end, a descriptive ending message will print.
 * It is extends from its parent class Action.
 *
 * @author Huang GuoYueYang
 */
public class VictoryAction extends Action {

    /**
     * Perform the Action.
     * When player end this game, the actor will be removed from this map.
     * A descriptive ending message is displayed.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "From then on, Mario and the princess lived a happy life ~!!";
    }

    /**
     * Returns a descriptive string to ask Player whether we want to end this game or not.
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Dear Mario! you finally came!!! Let's escape from here! <END GAME> ";
    }
}