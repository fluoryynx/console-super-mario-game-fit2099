package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

import java.util.Random;

public class Sprout extends Tree{

    private final Random rand = new Random();
    private static final char SPROUT_CHAR = '+';
    private static final int SPROUT_JUMP_RATE = 90;
    private static final int SPROUT_FALL_DAMAGE = 10;
    private static final String SPROUT_TYPE = "Sprout tree";
    private static final int REACHED_SAPLING_AGE = 10;
    private static final int SPAWN_GOOMBA_RATE = 10;

    public Sprout() {
        super(SPROUT_CHAR, SPROUT_JUMP_RATE, SPROUT_FALL_DAMAGE, SPROUT_TYPE);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        // change to sapling when its age reached 10
        if (reachSaplingAge()){
            changeToSapling(location);
        }

        // 10 % to spawn goomba if there is no actor at this location
        if ((rand.nextInt(100) <= SPAWN_GOOMBA_RATE) && !location.containsAnActor()){
            spawnGoomba(location);
        }
    }

    public boolean reachSaplingAge(){
        return this.getAge() == REACHED_SAPLING_AGE;
    }

    public void changeToSapling(Location currentLocation){
        currentLocation.setGround(new Sapling());
    }

    public void spawnGoomba(Location currentLocation){
        Goomba goomba = new Goomba();
        currentLocation.addActor(goomba);
    }
}
