package game.actions;

import java.util.Random;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Key;

/**
 * AttackAction is a class which used by player to attack the enemies, it extends from its parent class Action class.
 * In this class, player can attack enemies with weapon with a corresponding hit rate.
 * If the target(enemies) no longer conscious, all the item of that target will drop and it will be removed from the map(except Koopa).
 * In the end, there is a string will be printed to show how much you hurt the enemies.
 *
 * @author Huang GuoYueYang
 */
public class AttackAction extends Action {

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
	 * @param target the Actor to attack
	 * @param direction the direction of incoming attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Action.
	 * This method is used to attack targets under different hit rate depends on different weapons.
	 * When actor has certain capability(INVINCIBLE), actor can attack any target with full hit rate and
	 * the damage caused by the target will become zero.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		// instantly kill enemy
		if (actor.hasCapability(Status.INVINCIBLE)){
			if (target.hasCapability(Status.DROP_KEY)){
				Location currentLocationOfTarget = map.locationOf(target);
				currentLocationOfTarget.addItem(new Key());
			}
			map.removeActor(target);
			return target + " is killed.";
		}

		// enemy attack become useless
		int damage;
		if (target.hasCapability(Status.INVINCIBLE)){
			damage = 0;
		} else{
			damage = weapon.damage();
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
			if(target.hasCapability(Status.TALL)){
				target.removeCapability(Status.TALL);
			}
		}
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			if(!target.hasCapability(Status.HIDE_IN_SHELL) && !target.hasCapability(Status.DROP_KEY)){
				map.removeActor(target);
			}
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Returns a descriptive string: Actor attacks target at certain direction.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
