package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

public class Key extends Item {

    private static final String ITEM_NAME = "Key";
    private static final char ITEM_CHAR = 'k';
    private static final boolean ITEM_PORTABLE = true;

    /***
     * Constructor
     */
    public Key() {
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE);
        this.addCapability(Status.HAVE_KEY);
    }
}
