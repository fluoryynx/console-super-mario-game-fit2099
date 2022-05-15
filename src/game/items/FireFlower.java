package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class FireFlower extends MagicalItem{

    private int remainingTurn = 20;

    public FireFlower() {
        super("Fire Flower",'f',true,false);
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.FIRE_ATTACK);
    }

    @Override
    public void currentStatus(Location location) {
        remainingTurn--;
        if (remainingTurn == 0 || consumer.hasCapability(Status.RESET_CALLED)){
            consumer.removeCapability(Status.FIRE_ATTACK);
            this.setIsExpired(true);
        }
        else{
            consumer.addCapability(Status.FIRE_ATTACK);
        }
    }
}
