package game.grounds.fountains;

import game.Status;
import game.waters.HealingWater;
import game.waters.Water;

/**
 * Class that represents health fountain in the game.
 * Drinking the water from this fountain will increase the drinker's hit points/healing by 50 hit points.
 * @author Lim Fluoryynx
 */
public class HealthFountain extends Fountain {

    /**
     * name of the fountain
     */
    private static final String FOUNTAIN_NAME="Health Fountain";

    /**
     * display character of the fountain
     */
    private static final char FOUNTAIN_CHARACTER='H';

    /**
     * constructor
     */
    public HealthFountain() {
        super( FOUNTAIN_CHARACTER, FOUNTAIN_NAME);
        this.addCapability(Status.INCREASE_HEALTH);
    }

    /**
     * return water of health fountain
     * @return healingWater
     */
    @Override
    public Water getWater() {
        HealingWater healingWater = new HealingWater();
        return healingWater;
    }
}
