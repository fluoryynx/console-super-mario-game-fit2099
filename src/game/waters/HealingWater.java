package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

public class HealingWater extends Water{

    private static final int HEALED_HP=50;
    private static final String WATER_NAME= "Healing Water";

    public HealingWater() {
        super(WATER_NAME);
    }

    @Override
    public void updateStatus(Actor actor) {
        super.updateStatus(actor);
        actor.heal(HEALED_HP);
    }
}
