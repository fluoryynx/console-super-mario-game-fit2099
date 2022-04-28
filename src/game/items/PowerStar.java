package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;
import game.Status;
import game.grounds.Dirt;

public class PowerStar extends MagicalItem{

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param droppable true if and only if the Item can be dropped
     */

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

    /**
     * keep track of number of turns the power star is on the ground
     * decrement the turn value by 1 after every turn
     * remove power star from the map once value of turn reaches 0
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turn--;
        if (turn == ZERO_TURN){
            currentLocation.removeItem(this);
        }
    }

    /**
     * keep track of number of turns the power star is in player's inventory
     * decrement the turn value by 1 after every turn
     * remove power star from the inventory once value of turn reaches 0
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        turn--;
        if (turn == ZERO_TURN){
            actor.removeItemFromInventory(this);
            actor.removeCapability(Status.INVINCIBLE);
        }
    }

    /**
     * add INVINCIBLE capability to actor upon consumption of power star
     * reset value of turn to INITIAL_TURN, which is 10
     * heal actor by 200 hp
     * @param actor
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        turn = INITIAL_TURN;
        actor.heal(HEALED_HP);
        actor.addCapability(Status.INVINCIBLE);
    }

    /**
     * remove INVINCIBLE capablity when the power star effect wears off ( turn = 0) or when game is reset
     * @param location
     */
    @Override
    public void currentStatus(Location location) {
        turn --;
        if (turn == ZERO_TURN || consumer.hasCapability(Status.RESET_CALLED)){
            consumer.removeCapability(Status.INVINCIBLE);
            this.setIsExpired(true);
        }
        else{
            consumer.addCapability(Status.INVINCIBLE);
        }
    }

    /**
     * returns string to indicate number of remaining turn before power star disappear from the ground or inventory
     * @return
     */
    @Override
    public String toString() {
        if (turn == ONE_TURN){
            return "PowerStar - " + turn + " turn remaining";
        }
        return "PowerStar - " + turn + " turns remaining";
    }
}
