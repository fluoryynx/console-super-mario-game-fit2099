package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.magicalitems.SuperMushroom;
import java.util.Random;

/**
 * AttackShellAction is a class which used to smash Koopa’s shell when Koopa is defeated.
 * It extends from its parent class Action.
 * When Koopa is defeated and Player has capability HAVE_WRENCH,
 * player can use and only this action to smash Koopa’s shell in order to get a super mushroom.
 *
 * @author Huang GuoYueYang
 */
public class AttackShellAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor.
     * @param target the Actor to attack
     * @param direction the direction of incoming attack
     */
    public AttackShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     * This method is used to attack targets when Koopa is defeated(hide in shell).
     * When actor has certain capability(HAVE_WRENCH), actor can attack the target with corresponding hit rate and
     * the Koopa shell will become a super mushroom.
     * Return a string: "Koopa is gone, pick up the Super Mushroom!"
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(target);
        map.removeActor(target);
        currentLocation.addItem(new SuperMushroom());
        return "Koopa is gone, pick up the Super Mushroom!";
    }

    /**
     * Returns a descriptive string: Actor smashes Koopa's shell at certain direction.
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " smashes " + target + " 's shell at " + direction;
    }
}
