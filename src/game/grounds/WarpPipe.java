package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;

public class WarpPipe extends HighGround {

    private int currentXCoordinate;
    private int currentYCoordinate;
    private boolean spawnedPiranhaPlant = false; // use this in tick method
    // to check if piranha plant has been spawned at that location
    // also can be use for the reset thing (i.e., set this to false again when reset action is called)

    public WarpPipe() {
        super('C',100,0,"Warp Pipe");
    }

    @Override
    public void tick(Location location) {
        // super.tick(location); // so it is not destroyable
        currentXCoordinate = location.x();
        currentYCoordinate = location.y();
    }



    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (!location.containsAnActor()) {
            return new ActionList(new JumpAction(location, direction, this));
        } else if (location.getActor().hasCapability(Status.TELEPORT)) {
            return new ActionList(new TeleportAction(this));
        }
        return new ActionList();
    }

    public int getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    public int getCurrentYCoordinate() {
        return currentYCoordinate;
    }

    public void setSpawnedPiranhaPlant(boolean spawnedPiranhaPlant) {
        this.spawnedPiranhaPlant = spawnedPiranhaPlant;
    }

}
