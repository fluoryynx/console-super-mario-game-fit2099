package game.waters;


import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * represents power water in the power fountain
 *
 * @author Lim Fluoryynx
 */
public class PowerWater extends Water{

    /**
     * name of the water
     */
    private static final String WATER_NAME= "Power Water";

    /**
     * constructor
     */
    public PowerWater() {
        super(WATER_NAME);
    }

    /**
     * give consumer POWER_WATER capability upon consumption
     * @param actor
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.POWER_WATER);
    }

}

