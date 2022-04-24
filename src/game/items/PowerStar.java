package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;
import game.Status;
import game.grounds.Dirt;

public class PowerStar extends MagicalItem{

    private int turn;
    private static int HEALED_HP=200;
    private static final String ITEM_NAME="Power Star";
    private static final char ITEM_CHAR='*';
    private static final boolean ITEM_PORTABLE=true;
    private static final boolean ITEM_DROPPABLE=false;
    private static final int ONE_TURN=1;
    private static final int ZERO_TURN=0;
    private static final int INITIAL_TURN=10;

    public PowerStar() {
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE, ITEM_DROPPABLE);
        this.turn = INITIAL_TURN;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turn--;

        if (turn == ZERO_TURN){
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        turn--;
        if (turn == ZERO_TURN){
            actor.removeItemFromInventory(this);
            actor.removeCapability(Status.INVINCIBLE);
        }

    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        turn = INITIAL_TURN;
        actor.heal(HEALED_HP);
        actor.addCapability(Status.INVINCIBLE);
    }

    @Override
    public void currentStatus(Location location) {
        turn --;
        if (turn == ZERO_TURN){
            consumer.removeCapability(Status.INVINCIBLE);
            this.isExpired = true;
        }
        else{
            consumer.addCapability(Status.INVINCIBLE);
        }
    }

    @Override
    public String toString() {
        if (turn == ONE_TURN){
            return "PowerStar - " + turn + " turn remaining";
        }
        return "PowerStar - " + turn + " turns remaining";
    }
}
