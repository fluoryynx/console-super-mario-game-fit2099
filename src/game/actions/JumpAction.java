package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.HighGround;

import java.util.Random;

public class JumpAction extends Action {
    /**
     * Target location
     */
    private Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    private String direction;

    private final Random rand = new Random();

    private HighGround highGround;

    public JumpAction(Location moveToLocation, String direction, HighGround highGround) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.highGround = highGround;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // TODO: create variables to hold the values for each return of methods
        String highGroundType = highGround.getGroundType();
        int fallDamage = highGround.getFallDamage();
        int jumpRate = highGround.getJumpRate();
        boolean consumedSuperMushroom = actor.hasCapability(Status.TALL);

        if (!consumedSuperMushroom && !(rand.nextInt(100) <= jumpRate)) {
            actor.hurt(fallDamage);
            return actor + " failed to jump to the " + highGroundType
                    + " and receive " + fallDamage + " damage";
        } else {
            actor.addCapability(Status.CAN_JUMP);
            map.moveActor(actor, moveToLocation);
            actor.removeCapability(Status.CAN_JUMP);
            return actor + " jumps to the " + highGroundType; // message
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + highGround.getGroundType() + "(" +moveToLocation.x() + ", "
                + moveToLocation.y() + ")" + " at " + direction; // command list
    }
}
