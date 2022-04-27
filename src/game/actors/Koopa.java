package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackShellAction;
import game.actors.Enemy;

/**
 * Koopa class is a class represents the enemies in this game. It is a class that extends from the Enemy class.
 * Koopa can move around in the game map but cannot enter floor.
 * Once koopa is engaged in a fight (the Player attacks the enemy or the enemy attacks player
 * -- when the player stands in the enemy's surroundings), it will follow the Player.
 * It causes 30 damages to player with 50% hit rate and koopa has the same behaviors with goomba.
 * When koopa is not conscious(means it is defeated), it will hide inside its shell, and its
 * character will change from ’K’ to ‘D’.
 * Player cannot attack it anymore, and all the behaviors will removed from koopa(attack/follow/wander).
 */
public class Koopa extends Enemy {
    /**
     * State of Koopa
     */
    private boolean isDefeated = false;

    /**
     * Name of Koopa
     */
    private static final String KOOPA_NAME = "Koopa";

    /**
     * Character of Koopa
     */
    private static final char KOOPA_CHAR = 'K';

    /**
     * Character of Koopa's shell
     */
    private static final char SHELL_CHAR = 'D';

    /**
     * HitPoint of Koopa
     */
    private static final int HIT_POINT = 100;

    /**
     * HitRate of Koopa
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of Koopa
     */
    private static final int DAMAGE = 30;

    /**
     * Hit verb of Koopa
     */
    private static final String HIT_VERB = "punch";

    /**
     * Constructor.
     */
    public Koopa() {
        super(KOOPA_NAME,KOOPA_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE);
        this.addCapability(Status.HIDE_IN_SHELL);
    }

    /**
     * Make Koopa can be attacked by Player.
     * When Koopa is not conscious(defeated), it will hide in shell and only the actor with capability HOSTILE_TO_ENEMY && HAVE_WRENCH
     * is allowed smash its shell.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions = super.allowableActions(otherActor, direction, map);
        // only the player with wrench can smash its shell when Koopa is defeated
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && isDefeated && otherActor.hasCapability(Status.HAVE_WRENCH)){
            actions.add(new AttackShellAction(this,direction));
        }
        return actions;
    }

    /**
     * Setter of defeated
     * @param defeated
     */
    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    /**
     * Select and return an action to perform on the current turn.
     * If Koopa is not conscious anymore, it is defeated.
     * At this time, it will turn into shell states and all the behaviors will be removed.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            setDefeated(true);
            this.setDisplayChar(SHELL_CHAR);
            behaviours.remove(FIRST_PRIORITY);
            behaviours.remove(SECOND_PRIORITY);
            behaviours.remove(THIRD_PRIORITY);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
