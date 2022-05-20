package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Fire extends Item {

    private static final String ITEM_NAME = "Fire";
    private static final char ITEM_CHAR = 'v';
    private static final boolean ITEM_PORTABLE = false;
    private static final int DAMAGE_PER_TURN = 20;

    private int remainingTurn = 3;

    public Fire() {
        // cannot extend WeaponItem because it is not portable
        super(ITEM_NAME, ITEM_CHAR, ITEM_PORTABLE);
        this.addCapability(Status.FIRE_DAMAGE);
    }

    @Override
    public void tick(Location currentLocation) { // turn 1
        if (remainingTurn == 0){
            currentLocation.removeItem(this);
        }
        remainingTurn --;
        Actor currentActor = currentLocation.getActor();
        if (currentActor != null){
            boolean canHideShell = currentActor.hasCapability(Status.HIDE_IN_SHELL);
            boolean canDropKey = currentActor.hasCapability(Status.DROP_KEY);
            if (!canHideShell && !canDropKey){
                currentActor.hurt(DAMAGE_PER_TURN);
                boolean isActorConscious = currentActor.isConscious();
                if (!isActorConscious){
                    GameMap map = currentLocation.map();
                    map.removeActor(currentActor);
                }
            }
        }
    }
}