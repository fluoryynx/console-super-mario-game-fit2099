package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class SuperMushroom extends MagicalItem {


    /**
     * extra hp to be added to player
     */
    private static int EXTRA_HP=50;

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
     * @param actor
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
     * @param location
     */
    @Override
    public void currentStatus(Location location) {
        if (consumer.hasCapability(Status.ATTACKED_BY_ENEMY) || consumer.hasCapability(Status.RESET_CALLED)){
          //  consumer.removeCapability(Status.TALL);
            consumer.removeCapability(Status.ATTACKED_BY_ENEMY);
            this.isExpired = true;
        }
    }
}
