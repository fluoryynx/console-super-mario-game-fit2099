package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Fire extends Item {

    private int remainingTurn = 3;

    public Fire() {
        // cannot extend WeaponItem because it is not portable
        super("Fire", 'v', false);
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
            if (!currentActor.hasCapability(Status.HIDE_IN_SHELL) && !currentActor.hasCapability(Status.DROP_KEY)){
                currentActor.hurt(20);
                if (!currentActor.isConscious()){
                    GameMap map = currentLocation.map();
                    map.removeActor(currentActor);
                }
            }
        }
    }
}