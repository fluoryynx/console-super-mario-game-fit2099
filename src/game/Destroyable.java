package game;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;
import game.items.Coin;

/**
 * It is an interface that will be implemented by those class that are destroyable when the Player
 * is invincible. The reason to create this interface instead of just directly putting the following
 * two methods in Tree class is because this kind of design allow for extension. What I meant is that what
 * if there are more types of ground (regardless of the ground type) that are destroyable in the future, and
 * they perform the same operations after it is being destroyed (i.e., also change to dirt and drop coins).
 * Therefore, if there is such situation happen in the future, we can just let that particular class to
 * implements this interface. Hence, this approach is better.
 *
 * @author Kuah Jia Chen
 */
public interface Destroyable {

    /**
     * A default interface method that change the ground type of current location to dirt
     * @param currentLocation the current location of the destroyable instance
     * @throws IllegalArgumentException if currentLocation is null
     */
    default void breakToDirt(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        currentLocation.setGround(new Dirt());
    }

    /**
     * A default interface method that create a coin instance on that location
     * @param currentLocation the current location of the destroyable instance
     * @param coinValue the value of coin that drop at that location
     * @throws IllegalArgumentException if currentLocation is null
     * @throws IllegalArgumentException if coinValue is not 5
     */
    default void convertCoin(Location currentLocation, int coinValue){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        // the reason to put this exception is that currently when the high ground is destroyed, the
        // value of coin should always be 5, hence this is to check if coinValue ever not equal to 5.
        // This exception will be removed in future if the value of coin to drop at that location can be
        // different
        if (coinValue != 5){
            throw new IllegalArgumentException("The input parameter (i.e., coinValue) is not valid");
        }

        currentLocation.addItem(new Coin(coinValue));
    }
}
