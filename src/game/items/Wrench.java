package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * This class is a subclass of WeaponItem.
 * It represents the weapon players use in the game to defeat enemies, such as the Koopa’s shell.
 * It has 80% hit rate and 50 damage.
 *
 * @author Lim Fluoryynx
 */
public class Wrench extends WeaponItem {

    /**
     * name of the wrench
     */
    private static final String ITEM_NAME="Wrench";

    /**
     * display character of wrench
     */
    private static final char ITEM_CHAR='w';

    /**
     * hit rate of the wrench
     */
    private static final int HIT_RATE=80;

    /**
     * damage of the wrench
     */
    private static final int DAMAGE=50;

    /**
     * verb of the wrench
     */
    private static final String VERB="smashes";

    /***
     * Constructor.
     */
    public Wrench() {
        super(ITEM_NAME,ITEM_CHAR,DAMAGE,VERB,HIT_RATE);
        this.addCapability(Status.HAVE_WRENCH);
    }

}