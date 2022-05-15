package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.highgrounds.HighGround;

import java.util.Random;

/**
 * JumpAction is a class that allows the actor to jump to the jumpable ground.
 * It is a new class that extends Action class. When there is a high ground nearby
 * player (i.e., one of the eight exits) , the jump action will be displayed on the
 * console and allow the player to jump.
 *
 * @author Kuah Jia Chen
 */
public class JumpAction extends Action {

    /**
     * Target location
     */
    private Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    private String direction;

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Target high ground
     */
    private HighGround highGround;

    /**
     * Constructor of JumpAction class
     * @param moveToLocation Target location
     * @param direction the direction of the move (to be displayed in menu)
     * @param highGround the target high ground
     */
    public JumpAction(Location moveToLocation, String direction, HighGround highGround) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.highGround = highGround;
    }

    /**
     * When the jump action is selected by the user, this execute method will be called.
     * It will first check if the actor consumed super mushrooms or not. If the actor does
     * not consume a super mushroom and the random number generated is greater than the
     * success rate, the actor will fail to jump to the target high ground. Otherwise, the
     * jump action will be successful. The corresponding message also will be return
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the jump action to inform the user whether a success or fail jump is performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // TODO: create variables to hold the values for each return of methods
        boolean consumedSuperMushroom = actor.hasCapability(Status.TALL);
        int jumpRate = highGround.getJumpRate();
        String highGroundType = highGround.getGroundType();
        int fallDamage = highGround.getFallDamage();

        // if a player does not consume super mushroom and the random number generated is greater than
        // jumpRate, then the jump action will not be successful
        if (!consumedSuperMushroom && !(rand.nextInt(100) <= jumpRate)) {
            actor.hurt(fallDamage);
            return actor + " failed to jump to the " + highGroundType
                    + " and receive " + fallDamage + " damage";
        } else { // else, a successful jump
            actor.addCapability(Status.CAN_JUMP);
            map.moveActor(actor, moveToLocation);
            actor.removeCapability(Status.CAN_JUMP);
            return actor + " jumps to the " + highGroundType; // message
        }
    }

    /**
     * Return a description of this jump action to display in the menu.
     * Besides that, the coordinate of the target high ground also will be printed out.
     * @param actor The actor performing the action.
     * @return a String that describe the current jump action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + highGround.getGroundType() + "(" +moveToLocation.x() + ", "
                + moveToLocation.y() + ")" + " at " + direction; // command list
    }
}
