package game;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;

public interface Destroyable {

    default void breakToDirt(Location currentLocation){
        currentLocation.setGround(new Dirt());
    };

    default void convertCoin(Location currentLocation){
        currentLocation.addItem(new Coin(5));
    };
}
