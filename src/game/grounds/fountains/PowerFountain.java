package game.grounds.fountains;

import game.Status;
import game.waters.PowerWater;
import game.waters.Water;

public class PowerFountain extends Fountain {

    private PowerWater powerWater;
    private static final String FOUNTAIN_NAME="Power Fountain";
    private static final char FOUNTAIN_CHARACTER='A';

    public PowerFountain() {
        super(FOUNTAIN_CHARACTER,FOUNTAIN_NAME);
        this.addCapability(Status.INCREASE_BASE_DAMAGE);
    }

    @Override
    public Water getWater() {
        powerWater = new PowerWater();
        return powerWater;
    }
}
