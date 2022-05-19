package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;

public class WarpPipe extends HighGround implements Resettable {

    private static final char WARP_PIPE_CHAR = 'C';

    private static final int WARP_PIPE_JUMP_RATE = 100;

    private static final int WARP_PIPE_FALL_DAMAGE = 0;

    private static final String WARP_PIPE_TYPE = "Warp Pipe";

    private static final int INCREASE_PLANT_HP = 50;

    private int currentXCoordinate;
    private int currentYCoordinate;
    private boolean spawnedPiranhaPlant = false; // use this in tick method
    // to check if piranha plant has been spawned at that location
    // also can be use for the reset thing (i.e., set this to false again when reset action is called)

    public WarpPipe() {
        super(WARP_PIPE_CHAR,WARP_PIPE_JUMP_RATE,WARP_PIPE_FALL_DAMAGE,WARP_PIPE_TYPE);
        this.registerInstance();
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET_CALLED)){
            if (location.containsAnActor()){
                if (location.getActor().hasCapability(Status.IS_ENEMY)){
                    location.getActor().increaseMaxHp(INCREASE_PLANT_HP);
                }
            }else {
                setSpawnedPiranhaPlant(false);
            }
            this.removeCapability(Status.RESET_CALLED);
        }
        // setSpawnedPiranhaPlant(false);
        // super.tick(location); // so it is not destroyable
        currentXCoordinate = location.x();
        currentYCoordinate = location.y();
        if (!spawnedPiranhaPlant){
            location.addActor(new PiranhaPlant());
            setSpawnedPiranhaPlant(true);
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.TELEPORT)) {
            return new ActionList(new TeleportAction(this));
        }
        return super.allowableActions(actor,location,direction);
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

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }
}
