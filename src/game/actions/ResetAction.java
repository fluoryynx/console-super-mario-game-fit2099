package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

/**
 * ResetAction is a class that allows the user to reset the game. It is a class
 * that will reset the game when the user selects the reset option on the menu.
 * It is a new class that extends Action class.
 *
 * @author Kuah Jia Chen
 */
public class ResetAction extends Action {

    /**
     * When the reset action is selected by the user, this execute method will be called.
     * It will get the instance of ResetManager and execute the run method in the
     * ResetManager class. Eventually, a message will be printed out to inform the user
     * the game is resetted.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String that inform the user the game has been resetted
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // get the instance of ResetManager and call the run method
        ResetManager.getInstance().run();
        return "The game is now reset";
    }

    /**
     * Return a description of this reset action to display in the menu.
     * @param actor The actor performing the action.
     * @return a String that describe the reset action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * Return's the hotkey of reset action
     * @return a String that indicating the hotkey of reset action
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
