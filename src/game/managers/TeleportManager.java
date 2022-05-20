package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.highgrounds.WarpPipe;

public class TeleportManager {

    private WarpPipe firstMapWarpPipe;

    private static final int SECOND_MAP_WARP_PIPE_X = 0;
    private static final int SECOND_MAP_WARP_PIPE_Y = 0;

    private static TeleportManager instance;

    public static TeleportManager getInstance(){
        if(instance == null){
            instance = new TeleportManager();
        }
        return instance;
    }

    private TeleportManager(){
    }

    public void setFirstMapWarpPipe(WarpPipe firstMapWarpPipe) {
        this.firstMapWarpPipe = firstMapWarpPipe;
    }

    public void run(Actor actor, GameMap targetMap, boolean onFirstMap){
        Location targetLocation;
        if (onFirstMap){
            targetLocation = targetMap.at(SECOND_MAP_WARP_PIPE_X,SECOND_MAP_WARP_PIPE_Y);
            actor.addCapability(Status.SECOND_MAP);
            actor.removeCapability(Status.FIRST_MAP);
        } else { // if (actor.hasCapability(Status.SECOND_MAP))
            targetLocation = targetMap.at(firstMapWarpPipe.getCurrentXCoordinate(),firstMapWarpPipe.getCurrentYCoordinate());
            actor.addCapability(Status.FIRST_MAP);
            actor.removeCapability(Status.SECOND_MAP);
        }
        // remove the actor if any actor is currently at the target location
        if (targetLocation.containsAnActor()){
            Actor targetActor = targetLocation.getActor();
            targetMap.removeActor(targetActor);
        }
        targetMap.moveActor(actor,targetLocation);
    }

}
