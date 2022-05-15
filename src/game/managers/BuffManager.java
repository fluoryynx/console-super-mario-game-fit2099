package game.managers;

import edu.monash.fit2099.engine.positions.Location;
import game.items.magicalitems.MagicalItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The BuffManager class has dependency with the MagicalItem class
 * because we need a list consisting of instance from MagicalItem class and for each unexpired
 * magical items, it will give corresponding buff to the Player. Besides, this
 * class also will remove all expired magical items consumed by player from the list.
 *
 * @author Lim Fluoryynx
 */
public class BuffManager {
    /**
     * A list of magicalItem instances
     */
    private List<MagicalItem> magicalItemList;

    /**
     * A singleton buff manager instance
     */
    private static BuffManager instance;

    /**
     * Get the singleton instance of buff manager
     * @return BuffManager singleton instance
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
     * Remove expired magicalItems by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     * @param currentLocation the current location of the player
     * @throws IllegalArgumentException if currentLocation is null
     */
    public void run(Location currentLocation){

        if (currentLocation == null){
            throw new IllegalArgumentException("The input parameter (i.e., currentLocation) cannot be null");
        }

        for (MagicalItem magicalItem: magicalItemList){
            magicalItem.currentStatus(currentLocation);
        }

        // remove all expired item by using a temporary list to store unexpired items
        List<MagicalItem> temp = new ArrayList<>();
        for (MagicalItem magicalItem: magicalItemList){
            if (!magicalItem.getIsExpired()){
                temp.add(magicalItem);
            }
        }
        // assign magicalItemList to temp, hence now there is no expired item in magicalItemList
        magicalItemList = temp;

        // debug purpose
//        String result = "";
//        for (MagicalItem magicalItem: magicalItemListList){
//            result += magicalItem.toString();
//        }
//        return result;
    }

    /**
     * Add the MagicalItem instance to the list
     * @param magicalItem the magical item that consumed by the player
     * @throws IllegalArgumentException if magicalItem is null
     */
    public void appendMagicalItemInstance(MagicalItem magicalItem){

        if (magicalItem == null){
            throw new IllegalArgumentException("The input parameter (i.e., magicalItem) cannot be null");
        }

        magicalItemList.add(magicalItem);
    }
}
