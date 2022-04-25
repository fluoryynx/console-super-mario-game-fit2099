package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickCoinAction;
import game.Resettable;
import game.Status;

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

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (this.hasCapability(Status.RESET_CALLED)){
            currentLocation.removeItem(this);
        }
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }

}

