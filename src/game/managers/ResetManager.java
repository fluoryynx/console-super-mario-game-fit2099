package game.managers;

import game.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 * @author Kuah Jia Chen
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
        for (Resettable resettable: resettableList){
            resettable.resetInstance();
        }
        this.cleanUp();
    }

    /**
     * Add the Resettable instance to the list
     * @param reset the resettable instance that ready to be append to the resettableList
     */
    public void appendResetInstance(Resettable reset){
        this.resettableList.add(reset);
    }


    /**
     * Clear the resettableList
     */
    public void cleanUp(){
        this.resettableList.clear();
    }
}
