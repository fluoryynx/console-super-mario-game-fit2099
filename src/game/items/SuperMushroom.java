package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class SuperMushroom extends MagicalItem {

    private static int EXTRA_HP=50;

    private static final String ITEM_NAME="Super Mushroom";
    private static final char ITEM_CHAR='^';
    private static final boolean ITEM_PORTABLE=true;
    private static final boolean ITEM_DROPPABLE=false;

    /***
     * Constructor.
     */
    public SuperMushroom() {
        super(ITEM_NAME,ITEM_CHAR,ITEM_PORTABLE, ITEM_DROPPABLE);
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.increaseMaxHp(EXTRA_HP);
        actor.addCapability(Status.TALL);
        actor.addCapability(Status.SUPER_MUSHROOM);
    }

    @Override
    public void currentStatus(Location location) {
        if (consumer.hasCapability(Status.ATTACKED_BY_ENEMY)){
            consumer.removeCapability(Status.TALL);
            consumer.removeCapability(Status.ATTACKED_BY_ENEMY);
            consumer.removeCapability(Status.SUPER_MUSHROOM);
            this.isExpired = true;
        }
    }
}
