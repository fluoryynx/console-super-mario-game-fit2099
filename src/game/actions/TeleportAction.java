package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Application;
import game.Status;
import game.grounds.highgrounds.WarpPipe;
import game.managers.TeleportManager;

/**
 * TeleportAction is an action that allows the actor to teleport to the desired location. If the player
 * is currently on the first map, and it performed TeleportAction, it will be teleported to the only warp
 * pipe in second map. If the player is currently on the second map, then the TeleportAction will bring
 * it back to the previous warp pipe that it came from.
 *
 * @author Kuah Jia Chen
 */
public class TeleportAction extends Action {

    /**
     * The current warp pipe the actor is standing on
     */
    private WarpPipe currentWarpPipe;

    /**
     * Constructor of TeleportAction
     * @param currentWarpPipe the current warp pipe the actor is standing on
     */
    public TeleportAction(WarpPipe currentWarpPipe) {
        this.currentWarpPipe = currentWarpPipe;
    }

    /**
     * When the TeleportAction is executed, it will first check whether the actor is currently on the
     * first map. If yes, it will store the current warp pipe instance in TeleportManager (so that we
     * can keep track the previous warp pipe once we wanted to teleport back to the first map from the
     * second map) and assign targetMap as the second map instance. Else, we will assign targetMap as the
     * first map instance. After that, we will call the run method to perform teleportation and return
     * a suitable string to notify user.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that describe the current teleport action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        GameMap targetMap;
        String result;
        boolean onFirstMap = actor.hasCapability(Status.FIRST_MAP);
        if (onFirstMap) {
            targetMap = Application.getSecondGameMap();
            TeleportManager.getInstance().setFirstMapWarpPipe(currentWarpPipe);
            result = "Teleported to Lava Zone";
        } else{ // actor is now on the second map
            targetMap = Application.getFirstMap();
            result = "Teleported back to the first map";
        }
        TeleportManager.getInstance().run(actor,targetMap,onFirstMap);
        return result;
    }

    /**
     * Returns a descriptive string to tells the user what is the current teleport action
     * @param actor The actor performing the action.
     * @return a descriptive string to tells the user what is the current teleport action
     */
    @Override
    public String menuDescription(Actor actor) {
        if(actor.hasCapability(Status.FIRST_MAP)){
            return "Teleport to Lava Zone";
        } else {
            return "Teleport back to the first map";
        }
    }
}
