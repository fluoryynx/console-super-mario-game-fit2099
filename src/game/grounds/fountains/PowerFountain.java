package game.grounds.fountains;

import game.Status;
import game.waters.PowerWater;
import game.waters.Water;

/**
 * class that represents power fountain in the game
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
