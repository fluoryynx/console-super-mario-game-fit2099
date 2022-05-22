package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.magicalitems.MagicalItem;

/**
 * This class is a subclass of MagicalItems.
 * The effect will last until it receives any damage (e.g., hit by the enemy).
 * Once the effect wears off, the display character returns to normal (lowercase), but the maximum HP stays.
 *
 * @author Lim Fluoryynx
 */
public class SuperMushroom extends MagicalItem {


    /**
     * extra hp to be added to player
     */
    private static final int EXTRA_HP=50;

    /**
     * name of super mushroom
     */
    private static final String ITEM_NAME="Super Mushroom";

    /**
     * display character of super mushroom
     */
    private static final char ITEM_CHAR='^';

    /**
     * super mushroom is portable
     */
    private static final boolean ITEM_PORTABLE=true;

    /**
     * super mushroom is not droppable
     */
    private static final boolean ITEM_DROPPABLE=false;

    /***
     * Constructor
     */
    public SuperMushroom() {
        super(ITEM_NAME,ITEM_CHAR,ITEM_PORTABLE, ITEM_DROPPABLE);
    }

    /**
     * add 200 hp to actor when super mushroom is consumed
     * add TALL capability to actor
     * @param actor the consumer who consumed this super mushroom
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.increaseMaxHp(EXTRA_HP);
        actor.addCapability(Status.TALL);
    }

    /**
     * update status of the super mushroom
     * remove effects of super mushroom (TALL capability) from actor after they receive damage
     * set isExpired to true
     * @param location the current location of the consumer
     * @throws IllegalArgumentException if location is null
     */
    @Override
    public void currentStatus(Location location) {

        if (location == null){
            throw new IllegalArgumentException("The input parameter (i.e., location) cannot be null");
        }

        if (consumer.hasCapability(Status.ATTACKED_BY_ENEMY) || consumer.hasCapability(Status.RESET_CALLED)){
            consumer.removeCapability(Status.ATTACKED_BY_ENEMY);
            this.isExpired = true;
        }
    }
}
