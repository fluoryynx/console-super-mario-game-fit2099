package game.waters;


import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class PowerWater extends Water{

    private static final String WATER_NAME= "Power Water";

    public PowerWater() {
        super(WATER_NAME);
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.POWER_WATER);
    }

}

