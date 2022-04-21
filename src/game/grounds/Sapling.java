package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Coin;

import java.util.Random;

public class Sapling extends Tree{

    private final Random rand = new Random();
    private static final char SAPLING_CHAR = 't';
    private static final int SAPLING_JUMP_RATE = 80;
    private static final int SAPLING_FALL_DAMAGE = 20;
    private static final String SAPLING_TYPE = "Sapling tree";
    private static final int REACHED_MATURE_AGE = 10;
    private static final int SPAWN_COIN_RATE = 10;

    public Sapling() {
        super(SAPLING_CHAR, SAPLING_JUMP_RATE, SAPLING_FALL_DAMAGE, SAPLING_TYPE);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        // change to mature when its age reached 10
        if (reachMatureAge()){
            changeToMature(location);
        }

        // 10 % to spawn coin at this location
        if ((rand.nextInt(100) <= SPAWN_COIN_RATE)){
            spawnCoin(location);
        }
    }

    public boolean reachMatureAge(){
        return this.getAge() == REACHED_MATURE_AGE;
    }

    public void changeToMature(Location currentLocation){
        currentLocation.setGround(new Mature());
    }

    public void spawnCoin(Location currentLocation){
        Coin coin = new Coin(20);
        currentLocation.addItem(coin);
    }
}
