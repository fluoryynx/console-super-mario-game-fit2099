package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;
import game.items.Key;

import java.util.Random;

/**
 * An action that allows the actor to attack with fire (i.e., dropping a fire on target's location)
 * It will be available to the user on the console once the Player has the capability of FIRE_ATTACK
 * This capability can be gain by consuming a fire flower
 *
 * @author Kuah Jia Chen
 */
public class FireAttackAction extends Action {

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
     * Constructor of FireAttackAction
     * @param target the Actor that is to be attacked
     * @param direction the direction of incoming attack.
     */
    public FireAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Once the fire attack action is selected by the user, the execute method will be executed.
     * It will drop a fire instance on target's location. If the actor has invincible capability,
     * it will directly remove the actor from the map (i.e., instant kill). Besides that, it also
     * can be executed by enemy due to FireAttackBehaviour. It will drop a fire and punch the target
     * for a successful attack.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of this action to indicate user that this action has been performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(Status.IS_ENEMY)){

            // drop a fire instance on target's location
            Location currentLocationOfTarget = map.locationOf(target);
            currentLocationOfTarget.addItem(new Fire());

            // instantly kill enemy
            if (actor.hasCapability(Status.INVINCIBLE)){
                map.removeActor(target);
                if (target.hasCapability(Status.DROP_KEY)){
                    currentLocationOfTarget.addItem(new Key());
                }
                return target + " is killed.";
            }

            String result = actor + " attacked " + target + " with fire!";
            return result;
        }

        Weapon weapon = actor.getWeapon();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();

        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage and drop a fire.";
        target.hurt(damage);

        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if(target.hasCapability(Status.TALL)){
                target.removeCapability(Status.TALL);
            }
        }

        // if the target is dead
        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            if(!target.hasCapability(Status.HIDE_IN_SHELL)){
                map.removeActor(target);
            }
            result += System.lineSeparator() + target + " is killed.";
        }
        // drop a fire on target's location
        Location currentLocationOfTarget = map.locationOf(target);
        currentLocationOfTarget.addItem(new Fire());
        return result;
    }

    /**
     * Returns a descriptive string: Actor attacks target at certain direction with fire!
     * @param actor The actor performing the action.
     * @return the text that displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire! ";
    }
}