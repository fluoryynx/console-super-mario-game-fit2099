package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Destroyable;
import game.Status;
import game.actions.JumpAction;

public abstract class HighGround extends Ground implements Destroyable {

    private int jumpRate;
    private int fallDamage;
    private String groundType;
    private static final int COIN_VALUE_WHEN_DESTROYED = 5;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar, int jumpRate, int fallDamage, String groundType) {
        super(displayChar);
        this.jumpRate = jumpRate;
        this.fallDamage = fallDamage;
        this.groundType = groundType;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null){
            if (actor.hasCapability(Status.INVINCIBLE)){
                breakToDirt(location);
                convertCoin(location,COIN_VALUE_WHEN_DESTROYED);
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_JUMP) || actor.hasCapability(Status.INVINCIBLE) ;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (!location.containsAnActor() && !actor.hasCapability(Status.INVINCIBLE)) {
            return new ActionList(new JumpAction(location, direction, this));
        }
        return new ActionList();
    }

    public int getJumpRate() {
        return jumpRate;
    }

    public int getFallDamage() {
        return fallDamage;
    }

    public String getGroundType() {
        return groundType;
    }
}

