package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickCoinAction;
import game.Resettable;
import game.Status;

/**
 * class to represent coin in the game map.
 *  It is currency the player uses to trade for items
 */
public class Coin extends Item implements Resettable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */

    private int value;

    private static final String ITEM_NAME="Coin";
    private static final char ITEM_CHAR='$';
    private static final boolean ITEM_PORTABLE=false;

    public Coin(int value) {
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE);
        this.value=value;
        this.addAction(new PickCoinAction(this));
        this.registerInstance();
    }

    /**
     * to remove coin from the map when the game is reset
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (this.hasCapability(Status.RESET_CALLED)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * to get value of the coin
     * @return value of the coin
     */
    public int getValue(){
        return this.value;
    }

    /**
     * add RESET_CALLED capability to the coin if player resets the game
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }

}

