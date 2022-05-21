package game.grounds.fountains;

import game.Status;
import game.waters.PowerWater;
import game.waters.Water;

/**
 * Class that represents power fountain in the game
 * When the water from this fountain is consumed, it increases the drinker's base/intrinsic attack damage by 15.
 * @author Lim Fluoryynx
 */
public class PowerFountain extends Fountain {

    /**
     * name of the fountain
     */
    private static final String FOUNTAIN_NAME="Power Fountain";
    /**
     * display character of the fountain
     */
    private static final char FOUNTAIN_CHARACTER='A';

    /**
     * contrsuctor
     */
    public PowerFountain() {
        super(FOUNTAIN_CHARACTER,FOUNTAIN_NAME);
        this.addCapability(Status.INCREASE_BASE_DAMAGE);
    }

    /**
     * return water of power fountain
     * @return powerWater
     */
    @Override
    public Water getWater() {
        PowerWater powerWater = new PowerWater();
        return powerWater;
    }
}
