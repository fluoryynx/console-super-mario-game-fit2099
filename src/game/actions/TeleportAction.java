package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Application;
import game.Status;
import game.grounds.WarpPipe;
import game.managers.TeleportManager;

public class TeleportAction extends Action {

    private WarpPipe currentWarpPipe;

    public TeleportAction(WarpPipe currentWarpPipe) {
        this.currentWarpPipe = currentWarpPipe;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.FIRST_MAP)) {
            GameMap secondMap = Application.getSecondGameMap();
            TeleportManager.getInstance().setFirstMapWarpPipe(currentWarpPipe);
            TeleportManager.getInstance().run(actor,secondMap);
            return "Teleported to Lava Zone";
        } else{ // actor is now on the second map
            GameMap firstMap = Application.getFirstMap();
            TeleportManager.getInstance().run(actor,firstMap);
            return "Teleported back to the first map";
        }
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
