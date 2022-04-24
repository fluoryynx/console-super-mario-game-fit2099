package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;
import game.Status;
import game.grounds.Dirt;

public class PowerStar extends MagicalItem{

    private int turn;
    private static int HEALED_HP=200;

    public PowerStar() {
        super("Power Star", '*', true, false);
        this.addCapability(Status.INVINCIBLE);
        this.turn = 10;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turn--;

        if (turn == 0){
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        turn--;
        this.toString();
        if (turn == 0){
            actor.removeItemFromInventory(this);
            actor.removeCapability(Status.INVINCIBLE);
        }
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        turn = 10;
        actor.heal(HEALED_HP);
        actor.addCapability(Status.INVINCIBLE);
    }

    @Override
    public void currentStatus(Location location) {
        turn --;
        if (turn == 0){
            consumer.removeCapability(Status.INVINCIBLE);
            this.setIsExpired(true);
        }
    }

    @Override
    public String toString() {
        if (turn == 1){
            return "PowerStar - " + turn + " turn remaining";
        }
        return "PowerStar - " + turn + " turns remaining";
    }
}
