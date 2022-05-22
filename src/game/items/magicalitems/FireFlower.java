package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * This class is a subclass of MagicalItems, after consuming the fire flower, the actor can use fire attack
 * action to target (i.e., drop a fire on target's location) This "fire attack" effect will last for 20
 * turns
 *
 * @author Kuah Jia Chen
 */
public class FireFlower extends MagicalItem {

    /**
     * name of the fire flower
     */
    private static final String MAGICAL_ITEM_NAME = "Fire Flower";

    /**
     * display character of the fire flower
     */
    private static final char MAGICAL_ITEM_CHAR = 'f';

    /**
     * fire flower is portable
     */
    private static final boolean MAGICAL_ITEM_PORTABLE = true;

    /**
     * fire flower is not droppable
     */
    private static final boolean MAGICAL_ITEM_DROPPABLE = false;

    /**
     * number of remaining turn before the effect fade away
     */
    private int remainingTurn = 20;

    /**
     * Constructor of FireFlower class
     */
    public FireFlower() {
        super(MAGICAL_ITEM_NAME,MAGICAL_ITEM_CHAR,MAGICAL_ITEM_PORTABLE,MAGICAL_ITEM_DROPPABLE);
    }

    /**
     * add FIRE_ATTACK capability to actor upon consumption of fire flower
     * @param actor the consumer who consumed this power star
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.FIRE_ATTACK);
    }

    /**
     * remove FIRE_ATTACK capability when the fire flower effect wears off ( remainingTurn = 0)
     * or when game is reset
     * @param location the current location of the consumer
     */
    @Override
    public void currentStatus(Location location) {

        remainingTurn--;
        if (remainingTurn == 0 || consumer.hasCapability(Status.RESET_CALLED)){
            consumer.removeCapability(Status.FIRE_ATTACK);
            this.setIsExpired(true);
        }
        else{
            consumer.addCapability(Status.FIRE_ATTACK);
        }
    }
}
