package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class SuperMushroom extends MagicalItem {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param droppable true if and only if the Item can be dropped
     */

    private static int EXTRA_HP=50;

    private static final String ITEM_NAME="Super Mushroom";
    private static final char ITEM_CHAR='^';
    private static final boolean ITEM_PORTABLE=true;
    private static final boolean ITEM_DROPPABLE=false;

    public SuperMushroom() {
        super(ITEM_NAME,ITEM_CHAR,ITEM_PORTABLE, ITEM_DROPPABLE);
    }

    /**
     *
     * @param actor
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.increaseMaxHp(EXTRA_HP);
        actor.addCapability(Status.TALL);
        actor.addCapability(Status.SUPER_MUSHROOM);
    }

    @Override
    public void currentStatus(Location location) {
        if (consumer.hasCapability(Status.ATTACKED_BY_ENEMY) || consumer.hasCapability(Status.RESET_CALLED)){
            consumer.removeCapability(Status.TALL);
            consumer.removeCapability(Status.ATTACKED_BY_ENEMY);
            consumer.removeCapability(Status.SUPER_MUSHROOM);
            this.isExpired = true;
        }
    }
}
