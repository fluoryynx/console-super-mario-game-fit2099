package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {

    private static final char LAVA_CHAR = 'L';

    private static final char DAMAGE_PER_TURN = 15;

    public Lava() {
        super(LAVA_CHAR);
    }

    @Override
    public void tick(Location location) {
        Actor currentActor = location.getActor();
        if (currentActor != null){
            currentActor.hurt(DAMAGE_PER_TURN);
            if (currentActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                if(currentActor.hasCapability(Status.TALL)){
                    currentActor.removeCapability(Status.TALL);
                }
            }
            if (!currentActor.isConscious()){
                GameMap map = location.map();
                map.removeActor(currentActor);
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.IS_ENEMY);
    }

}
