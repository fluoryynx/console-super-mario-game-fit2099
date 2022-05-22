package game.waters;


import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * This class represents power water in the power fountain The effect of power water is to increase the base damage
 * to the consumer by 15
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
     * @param actor the actor that consumed the power water
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.addCapability(Status.POWER_WATER);
    }

}

