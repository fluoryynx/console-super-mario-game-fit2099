package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Speakable;
import game.Status;
import game.actions.VictoryAction;

/**
 * PrincessPeach is a class that represents the NPC PrincessPeach in the game map.
 * It is a class that extends from the Actor class.
 * PrincessPeach is a NPC that is friendly (0 hits point) and caught by Bowser.
 * It will have a conversation with the actor to end this game when the actor has capability (HAVE_KEY) and stand beside her.
 * Besides, PrincessPeach also speak every even turn automatically.
 *
 * @author Huang GuoYueYang
 */
public class PrincessPeach extends Actor implements Speakable {
    /**
     * Name of Princess Peach
     */
    private static final String ACTOR_NAME = "Princess Peach";

    /**
     * Character of Princess Peach
     */
    private static final char DISPLAY_CHAR = 'P';

    /**
     * HitPoint of Princess Peach
     */
    private static final int HIT_POINT = 0;

    /**
     * Current turn of this game
     */
    private int currentTurn;

    /**
     * The lower bound index for the monologue of PrincessPeach
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 0;

    /**
     * The upper bound index for the monologue of PrincessPeach
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 2;

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super(ACTOR_NAME, DISPLAY_CHAR, HIT_POINT);
    }

    /**
     * In each turn of this game, the current turn counter will increase by 1.
     * When the current turn is divisible by 2, PrincessPeach will speak automatically.
     * The monologue belongs to PrincessPeach will be print by using index.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed , which in this case is DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Generate monologue of PrincessPeach when even turn
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " + generateMonologue(MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND));
        }
        return new DoNothingAction();
    }
    /**
     * When the Player has capability HAVE_KEY, player is allowed to interact with PrincessPeach
     * with VictoryAction to end this game.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HAVE_KEY)){
            actions.add(new VictoryAction());
        }
        return actions;
    }
}
