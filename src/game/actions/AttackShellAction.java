package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.SuperMushroom;

import java.util.Random;

public class AttackShellAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(target);
        map.removeActor(target);
        currentLocation.addItem(new SuperMushroom());
        return "Koopa is gone, pick up the Super Mushroom!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " smashes " + target + " 's shell at " + direction;
    }
}
