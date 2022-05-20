package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Application;
import game.Status;
import game.grounds.highgrounds.WarpPipe;
import game.managers.TeleportManager;

public class TeleportAction extends Action {

    private WarpPipe currentWarpPipe;

    public TeleportAction(WarpPipe currentWarpPipe) {
        this.currentWarpPipe = currentWarpPipe;
    }

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
        TeleportManager.getInstance().run(actor,targetMap);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        if(actor.hasCapability(Status.FIRST_MAP)){
            return "Teleport to Lava Zone";
        } else {
            return "Teleport back to the first map";
        }
    }
}
