package game;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;
import game.items.Coin;

public interface Destroyable {

    default void breakToDirt(Location currentLocation){
        currentLocation.setGround(new Dirt());
    }

    default void convertCoin(Location currentLocation, int coinValue){
        currentLocation.addItem(new Coin(coinValue));
    }
}
