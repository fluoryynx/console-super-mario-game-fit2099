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

public class FireAttackAction extends Action {

    protected Actor target;

    protected String direction;

    protected Random rand = new Random();

    public FireAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(Status.IS_ENEMY)){

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
        Location currentLocationOfTarget = map.locationOf(target);
        currentLocationOfTarget.addItem(new Fire());
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire! ";
    }
}