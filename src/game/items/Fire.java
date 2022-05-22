package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents a fire in the game map
 * It will be dropped after a successful fire attack action by the actor
 * The fire will stay on the ground for three turns, and it will deal 20 damage per turn.
 *
 * @author Kuah Jia Chen
 */
public class Fire extends Item {

    /**
     * name of the fire
     */
    private static final String ITEM_NAME = "Fire";

    /**
     * display character of the fire
     */
    private static final char ITEM_CHAR = 'v';

    /**
     * fire is not portable
     */
    private static final boolean ITEM_PORTABLE = false;

    /**
     * the damage cause by fire per turn
     */
    private static final int DAMAGE_PER_TURN = 20;

    /**
     * the remaining turn where the fire still stay on the ground
     */
    private int remainingTurn = 3;

    /**
     * Constructor of Fire Class
     */
    public Fire() {
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE);
        this.addCapability(Status.FIRE_DAMAGE);
    }

    /**
     * remove the fire from the ground if the remainingTurn is equal to 0, else do 20 damage to the actor
     * that standing on it
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        // remove it if remainingTurn is 0
        if (remainingTurn == 0){
            currentLocation.removeItem(this);
        }
        remainingTurn --;

        // get the actor standing on this location and deal 20 damager
        Actor currentActor = currentLocation.getActor();
        if (currentActor != null){
            boolean canHideShell = currentActor.hasCapability(Status.HIDE_IN_SHELL);
            boolean canDropKey = currentActor.hasCapability(Status.DROP_KEY);

            if (!canHideShell && !canDropKey){
                currentActor.hurt(DAMAGE_PER_TURN);
                boolean isActorConscious = currentActor.isConscious();

                // if the actor is dead, remove it
                if (!isActorConscious){
                    GameMap map = currentLocation.map();
                    map.removeActor(currentActor);
                }
            }
        }
    }
}