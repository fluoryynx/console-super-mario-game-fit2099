package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.MagicalItem;

import java.util.ArrayList;
import java.util.List;

public class BuffManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<MagicalItem> magicalItemList;

    /**
     * A singleton reset manager instance
     */
    private static BuffManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static BuffManager getInstance(){
        if(instance == null){
            instance = new BuffManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private BuffManager(){
        magicalItemList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(Location currentLocation){
        for (MagicalItem magicalItem: magicalItemList){
            magicalItem.currentStatus(currentLocation);
        }
        List<MagicalItem> temp = new ArrayList<>();
        for (MagicalItem magicalItem: magicalItemList){
            if (!magicalItem.getIsExpired()){
                temp.add(magicalItem);
            }
        }
        magicalItemList = temp;
        // debug purpose
//        String result = "";
//        for (MagicalItem magicalItem: magicalItemListList){
//            result += magicalItem.toString();
//        }
//        return result;
    }

    /**
     * Add the Resettable instance to the list
     * FIXME: it does nothing, you need to implement it :)
     */
    public void appendResetInstance(MagicalItem magicalItem){
        magicalItemList.add(magicalItem);
    }
}
