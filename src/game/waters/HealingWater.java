package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * This class represents healing water in the healing fountain It will heal consumer by 50 hp upon consumption
 *
 * @author Lim Fluoryynx
 */
public class HealingWater extends Water{

    /**
     * extra hp to be given to consumer
     */
    private static final int HEALED_HP=50;

    /**
     * name of the water
     */
    private static final String WATER_NAME= "Healing Water";

    /**
     * constructor
     */
    public HealingWater() {
        super(WATER_NAME);
    }

    /**
     * heal consumer by 50 hp upon consumption
     * @param actor the actor that consumed the healing water
     */
    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.heal(HEALED_HP);
    }
}
