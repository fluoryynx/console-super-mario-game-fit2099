package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * Class to represent key in the game map.
 * It is drop by Bowser after Bowser is killed.
 *
 *  @author Huang GuoYueYang
 */
public class Key extends Item {

    /**
     * Name of Key
     */
    private static final String ITEM_NAME = "Key";

    /**
     * Char of Key
     */
    private static final char ITEM_CHAR = 'k';

    /**
     * Portable of Key
     */
    private static final boolean ITEM_PORTABLE = true;

    /***
     * Constructor
     */
    public Key() {
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE);
        this.addCapability(Status.HAVE_KEY);
    }
}
