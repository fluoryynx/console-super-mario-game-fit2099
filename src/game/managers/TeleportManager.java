package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.highgrounds.WarpPipe;

/**
 * TeleportManager is a global singleton manager responsible to teleport the actor to the desired location
 * It will only be called if the actor executes TeleportAction
 * The reason to have this TeleportManager class is that we need a way to keep track of the previous WarpPipe
 * and store the coordinates of the warp pipe in the second map.
 * This cannot be done in the TeleportAction class as it will violate the Single Responsibility Principle
 * since TeleportAction should not be responsible for storing this information.
 * Hence, TeleportManager is needed.
 *
 * @author Kuah Jia Chen
 */
public class TeleportManager {

    /**
     * The WarpPipe instance in the first map that used by the Player to teleport to second map
     */
    private WarpPipe firstMapWarpPipe;

    /**
     * The X coordinate of the WarpPipe's location in the second map
     */
    private static final int SECOND_MAP_WARP_PIPE_X = 0;

    /**
     * The Y coordinate of the WarpPipe's location in the second map
     */
    private static final int SECOND_MAP_WARP_PIPE_Y = 0;

    /**
     * A singleton teleport manager instance
     */
    private static TeleportManager instance;

    /**
     * Get the singleton instance of teleport manager
     * @return TeleportManager singleton instance
     */
    public static TeleportManager getInstance(){
        if(instance == null){
            instance = new TeleportManager();
        }
        return instance;
    }

    /**
     * Constructor of TeleportManager
     */
    private TeleportManager(){
    }

    /**
     * Setter for firstMapWarpPipe
     * @param firstMapWarpPipe the WarpPipe instance that needed to assigned to firstMapWarpPipe
     * @throws IllegalArgumentException if the input parameter (firstMapWarpPipe) is null
     */
    public void setFirstMapWarpPipe(WarpPipe firstMapWarpPipe) {
        if (firstMapWarpPipe == null){
            throw new IllegalArgumentException("The input parameter (i.e., firstMapWarpPipe) cannot be null");
        }

        this.firstMapWarpPipe = firstMapWarpPipe;
    }

    /**
     * It will perform the teleportation on the player to the correct location and map. If there is any actor
     * blocking player's destination, we will directly remove it
     * @param actor the actor that wants to teleport
     * @param targetMap the map that the actor wants to teleport to
     * @param onFirstMap a boolean that indicates whether the actor is currently on the first map or not
     * @throws IllegalArgumentException if the input parameter (actor or targetMap) is null
     */
    public void run(Actor actor, GameMap targetMap, boolean onFirstMap){
        if (actor == null || targetMap == null){
            throw new IllegalArgumentException("The input parameter (i.e., actor and targetMap) cannot be null");
        }

        Location targetLocation;

        if (onFirstMap){ // if the actor is now on first map

            targetLocation = targetMap.at(SECOND_MAP_WARP_PIPE_X,SECOND_MAP_WARP_PIPE_Y);

            // since now the actor is leaving first map and arriving to the second map
            actor.addCapability(Status.SECOND_MAP);
            actor.removeCapability(Status.FIRST_MAP);

        } else { // if the actor is now on second map

            // get the X and Y coordinate from firstMapWarpPipe
            targetLocation = targetMap.at(firstMapWarpPipe.getCurrentXCoordinate(),firstMapWarpPipe.getCurrentYCoordinate());

            // since now the actor is leaving second map and arriving to the first map
            actor.addCapability(Status.FIRST_MAP);
            actor.removeCapability(Status.SECOND_MAP);
        }
        // remove the actor if any actor is currently at the target location
        if (targetLocation.containsAnActor()){
            Actor targetActor = targetLocation.getActor();
            targetMap.removeActor(targetActor);
        }
        // move the actor to desired location
        targetMap.moveActor(actor,targetLocation);
    }

}
