package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends WeaponItem {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param droppable true if and only if the Item can be dropped
     */
    private static final String ITEM_NAME="Wrench";
    private static final char ITEM_CHAR='w';
    private static final int HIT_RATE=80;
    private static final int DAMAGE=50;
    private static final String VERB="smashes";

    public Wrench() {
        super(ITEM_NAME,ITEM_CHAR,DAMAGE,VERB,HIT_RATE);
        this.addCapability(Status.HAVE_WRENCH);
    }

}