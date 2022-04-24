package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.PickCoinAction;

public class Coin extends Item {
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
    }

    public int getValue(){
        return this.value;
    }

}

