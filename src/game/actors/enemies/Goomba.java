package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Random;

/**
 * Goomba class is a class represents the one kind of the enemies in this game.
 * It is a class that extends from the Enemy class.
 * Goomba will move around in the game map if no player is beside it but it cannot enter floor.
 * Once Goomba is engaged in a fight (the Player attacks the enemy or the enemy attacks player --
 * when the player stands in the enemy's surroundings), it will follow the Player.
 * It starts with 20 hit points.
 * It causes 10 damages to player with 50% hit rate.
 * In every turn, it has a 10% chance to be removed from the map (suicide). The main purpose is to clean up the map.
 * Besides, Goomba also speak every even turn automatically.
 *
 * @author Huang GuoYueYang
 */
public class Goomba extends Enemy {
    /**
     * Name of Goomba
     */
    private static final String GOOMBA_NAME = "Goomba";

    /**
     * Character of Goomba
     */
    private static final char GOOMBA_CHAR = 'g';

    /**
     * HitPoint of Goomba
     */
    private static final int HIT_POINT = 20;

    /**
     * HitRate of Goomba
     */
    private static final int HIT_RATE = 50;

    /**
     * Suicide rate of Goomba
     */
    private static final int SUICIDE_RATE = 10;

    /**
     * Damage of Goomba
     */
    private static final int DAMAGE = 10;

    /**
     * Hit verb of Goomba
     */
    private static final String HIT_VERB = "kick";

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * The lower bound index for the monologue of Goomba
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 11;

    /**
     * The upper bound index for the monologue of Goomba
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 13;

    /**
     * Constructor.
     * The monologue belongs to Goomba will be print by using index.
     */
    public Goomba() {
        super(GOOMBA_NAME,GOOMBA_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE,MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND);
    }

    /**
     * Select and return an action to perform on the current turn.
     * Goomba has 10% suicide rate in each turn of this game.
     * Once Goomba suicide, it will be remove from the map automatically.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (rand.nextInt(100)<=SUICIDE_RATE){
            // goomba suicide
            map.removeActor(this);
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
