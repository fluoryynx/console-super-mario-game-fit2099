package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;

/**
 * PiranhaPlant class is a class represents the one kind of the enemies in this game.
 * It is a class that extends from the Enemy class.
 * Piranha Plant Y will spawn at the second turn of the game (note: the first turn is when you start the game for the first time).
 * This plant will attack Mario when he stands next to it. Piranha Plant cannot move around. It will not follow anyone when it is engaged in a fight.
 * It stats with 150 hit points, "chomps" attack with 50% hit rate and 90 damage.
 * Once the player kills it, the corresponding WarpPipe will not spawn Piranha Plant again until the player resets the game (r command).
 * Resetting will increase alive/existing Piranha Plants hit points by an additional 50 hit points and heal to the maximum.
 * Besides, PiranhaPlant also speak every even turn automatically.
 *
 * @author Huang GuoYueYang
 */
public class PiranhaPlant extends Enemy {
    /**
     * Name of PiranhaPlant
     */
    private static final String PIRANHAPLANT_NAME = "PiranhaPlant";

    /**
     * Character of PiranhaPlant
     */
    private static final char PIRANHAPLANT_CHAR = 'Y';

    /**
     * HitPoint of PiranhaPlant
     */
    private static final int HIT_POINT = 150;

    /**
     * HitRate of PiranhaPlant
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of PiranhaPlant
     */
    private static final int DAMAGE = 90;

    /**
     * Hit verb of PiranhaPlant
     */
    private static final String HIT_VERB = "chomps";

    /**
     * The lower bound index for the monologue of PiranhaPlant
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 17;

    /**
     * The upper bound index for the monologue of PiranhaPlant
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 18;

    /**
     * Constructor.
     * The monologue belongs to PiranhaPlant will be print by using index.
     */
    public PiranhaPlant() {
        super(PIRANHAPLANT_NAME,PIRANHAPLANT_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE,MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND);
        this.behaviours.remove(FOURTH_PRIORITY);
    }

    /**
     * Select and return an action to perform on the current turn.
     * In each turn of this game, the current turn counter will increase by 1.
     * When the current turn is divisible by 2, PiranhaPlant will speak automatically.
     * The monologue belongs to PiranhaPlant will be print by using index.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Generate monologue of PiranhaPlant when even turn
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " +
                    generateMonologue(monologueIndexLowerBound,monologueIndexUpperBound));
        }
        this.behaviours.put(FIRST_PRIORITY,new AttackBehaviour());
        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
