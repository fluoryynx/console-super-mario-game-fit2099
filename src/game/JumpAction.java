package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class JumpAction extends Action {
    /**
     * Target location
     */
    protected Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    protected String direction;

    private final Random rand = new Random();

    private Jumpable jumpableGround;

    public JumpAction(Location moveToLocation, String direction, Jumpable jumpableGround) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.jumpableGround = jumpableGround;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // TODO: create variables to hold the values for each return of methods
        String jumpableType = jumpableGround.getJumpableType();
        int fallDamage = jumpableGround.getFallDamage();
        int jumpRate = jumpableGround.getJumpRate();
        boolean consumedSuperMushroom = actor.hasCapability(Status.SUPER_MUSHROOM);

        if (!consumedSuperMushroom && !(rand.nextInt(100) <= jumpRate)) {
            actor.hurt(fallDamage);
            return actor + " failed to jump to the " + jumpableType
                    + " and receive " + fallDamage + " damage";
        } else {
            actor.addCapability(Status.CAN_JUMP);
            map.moveActor(actor, moveToLocation);
            actor.removeCapability(Status.CAN_JUMP);
            return actor + " jumps to the " + jumpableType; // message
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + jumpableGround.getJumpableType() + "(" +moveToLocation.x() + ", "
                + moveToLocation.y() + ")" + " at " + direction; // command list
    }
}
