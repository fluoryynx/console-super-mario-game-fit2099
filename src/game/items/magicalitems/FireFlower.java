package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.magicalitems.MagicalItem;

public class FireFlower extends MagicalItem {

    private static final String MAGICAL_ITEM_NAME = "Fire Flower";
    private static final char MAGICAL_ITEM_CHAR = 'f';
    private static final boolean MAGICAL_ITEM_PORTABLE = true;
    private static final boolean MAGICAL_ITEM_DROPPABLE = false;
    private int remainingTurn = 20;

    public FireFlower() {
        super(MAGICAL_ITEM_NAME,MAGICAL_ITEM_CHAR,MAGICAL_ITEM_PORTABLE,MAGICAL_ITEM_DROPPABLE);
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.FIRE_ATTACK);
    }

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
