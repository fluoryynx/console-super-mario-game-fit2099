package game.grounds.fountains;

import game.Status;
import game.grounds.fountains.Fountain;
import game.waters.HealingWater;
import game.waters.Water;

public class HealthFountain extends Fountain {

    private HealingWater healingWater;
    private static final String FOUNTAIN_NAME="Health Fountain";
    private static final char FOUNTAIN_CHARACTER='H';

    public HealthFountain() {
        super( FOUNTAIN_CHARACTER, FOUNTAIN_NAME);
        this.addCapability(Status.INCREASE_HEALTH);
    }

    @Override
    public Water getWater() {
        healingWater = new HealingWater();
        return healingWater;
    }
}
