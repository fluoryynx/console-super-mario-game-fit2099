package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;

/**
 * A class that represents the warp  pipe in the game map. It will spawn PiranhaPlant on its location.
 * The player only can jump on it once the Piranha Plant is killed. However, if the reset action is
 * called, the warp pipe will respawn a new PiranhaPlant on its location. Resetting also will increase
 * alive/existing Piranha Plants hit points by an additional 50 hit points and heal to the maximum.
 *
 * @author Kuah Jia Chen, Huang GuoYueYang
 */
public class WarpPipe extends HighGround implements Resettable {

    /**
     * Character to display for warp pipe on the map
     */
    private static final char WARP_PIPE_CHAR = 'C';

    /**
     * Success rate to jump on this warp pipe
     */
    private static final int WARP_PIPE_JUMP_RATE = 100;

    /**
     * Fall damage done to the actor when the jump is not successful
     */
    private static final int WARP_PIPE_FALL_DAMAGE = 0;

    /**
     * The type of this high ground
     */
    private static final String WARP_PIPE_TYPE = "Warp Pipe";

    /**
     * The amount of hp heal to the PiranhaPlant
     */
    private static final int INCREASE_PLANT_HP = 50;

    /**
     * The X coordinate for the location the warp pipe is located on
     */
    private int currentXCoordinate;

    /**
     * The Y coordinate for the location the warp pipe is located on
     */
    private int currentYCoordinate;

    /**
     * A boolean indicating whether there is a PiranhaPlant on this WarpPipe's location
     */
    private boolean spawnedPiranhaPlant = false;

    /**
     * Constructor of WarpPipe class
     */
    public WarpPipe() {
        super(WARP_PIPE_CHAR,WARP_PIPE_JUMP_RATE,WARP_PIPE_FALL_DAMAGE,WARP_PIPE_TYPE);
        this.registerInstance();
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // super.tick(location); // so it is not destroyable

        // when reset is call, if the PiranhaPlant is still alive, increase 50 hp, else
        // set spawnedPiranhaPlant to false
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

        // get the X and Y coordinate from WarpPipe's location
        currentXCoordinate = location.x();
        currentYCoordinate = location.y();

        // if there is no actor currently standing on this location and spawnedPiranhaPlant
        // is false, spawn a new PiranhaPlant on this location and set spawnedPiranhaPlant to true
        if (!spawnedPiranhaPlant && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
            setSpawnedPiranhaPlant(true);
        }
    }

    /**
     * Return the action that can done by the actor on this instance
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return return an action list that consists all actions that can be done by the player on it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // if the actor currently standing on it has the capability of TELEPORT, then return TeleportAction
        if (location.containsAnActor() && location.getActor().hasCapability(Status.TELEPORT)) {
            return new ActionList(new TeleportAction(this));
        }
        // else call HighGround's allowableActions
        return super.allowableActions(actor,location,direction);
    }

    /**
     * Getter for currentXCoordinate
     * @return an integer value indicating the X coordinate of this WarpPipe's location
     */
    public int getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    /**
     * Getter for currentYCoordinate
     * @return an integer value indicating the Y coordinate of this WarpPipe's location
     */
    public int getCurrentYCoordinate() {
        return currentYCoordinate;
    }

    /**
     * Setter for spawnedPiranhaPlant
     * @param spawnedPiranhaPlant a boolean to assign spawnedPiranhaPlant
     */
    public void setSpawnedPiranhaPlant(boolean spawnedPiranhaPlant) {
        this.spawnedPiranhaPlant = spawnedPiranhaPlant;
    }

    /**
     * Add RESET_CALLED to its capability set
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }
}
