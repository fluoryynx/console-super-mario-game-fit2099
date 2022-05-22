package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Destroyable;
import game.Status;
import game.actions.JumpAction;

/**
 * A class that represents the high ground in this game. It is a new abstract class that extends
 * Ground and implements Destroyable since it can be destroyed when player is invincible and standing
 * at this location.
 *
 * @author Kuah Jia Chen, Huang GuoYueYang
 */
public abstract class HighGround extends Ground implements Destroyable {

    /**
     * Success rate to jump on this high ground
     */
    private int jumpRate;

    /**
     * Fall damage done to the actor when the jump is not successful
     */
    private int fallDamage;

    /**
     * The type of the high ground
     */
    private String groundType;

    /**
     * The amount of coin will be spawn on the location of this instance when it is being destroyed
     */
    private static final int COIN_VALUE_WHEN_DESTROYED = 5;

    /**
     * Constructor of HighGround
     * @param displayChar character to display for this type of high ground
     * @param jumpRate Success rate to jump on this high ground
     * @param fallDamage Fall damage done to the actor when the jump is not successful
     * @param groundType The type of the high ground
     */
    public HighGround(char displayChar, int jumpRate, int fallDamage, String groundType) {
        super(displayChar);
        this.jumpRate = jumpRate;
        this.fallDamage = fallDamage;
        this.groundType = groundType;
    }

    /**
     * Called once per turn, so that Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // destroyed if current actor standing on it has the capability invincible
        Actor actor = location.getActor();
        if (actor != null){
            if (actor.hasCapability(Status.INVINCIBLE)){
                breakToDirt(location);
                convertCoin(location,COIN_VALUE_WHEN_DESTROYED);
            }
        }
    }

    /**
     * Return true if the actor fulfil the precondition to enter this high ground, else return false
     * @param actor the Actor to check
     * @return True if actor can enter, else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_JUMP) || actor.hasCapability(Status.INVINCIBLE) || actor.hasCapability(Status.CAN_FLY) ;
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
        // if there is no actor currently standing on it and the actor is not invincible, return JumpAction
        if (!location.containsAnActor() && !actor.hasCapability(Status.INVINCIBLE)) {
            return new ActionList(new JumpAction(location, direction, this));
        }
        return new ActionList();
    }

    /**
     * Getter for jumpRate
     * @return an integer indicating the success rate to jump on this high ground
     */
    public int getJumpRate() {
        return jumpRate;
    }

    /**
     * Getter for fallDamage
     * @return an integer indicating the fall damage done to the actor when the jump is not successful
     */
    public int getFallDamage() {
        return fallDamage;
    }

    /**
     * Getter for groundType
     * @return a String that indicating the type of the high ground
     */
    public String getGroundType() {
        return groundType;
    }
}

