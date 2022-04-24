package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends WeaponItem {

    private static final String ITEM_NAME="Wrench";
    private static final char ITEM_CHAR='w';
    private static final int HIT_RATE=0;
    private static final int DAMAGE=50;
    private static final String VERB="smashes";

    /***
     * Constructor.
     */
    public Wrench() {
        super(ITEM_NAME,ITEM_CHAR,DAMAGE,VERB,HIT_RATE);
        this.addCapability(Status.HAVE_WRENCH);
    }

}