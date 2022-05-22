package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Speakable;
import game.Status;
import game.actions.BuyAction;
import game.actions.GetBottleAction;
import game.actions.SpeakAction;
import game.items.magicalitems.PowerStar;
import game.items.magicalitems.SuperMushroom;
import game.items.Wrench;
import java.util.HashMap;
import java.util.Random;

/**
 * Toad is a class that represents the NPC Toad in the game map.
 * It is a class that extends from the Actor class.
 * Toad is a NPC is friendly (0 hits point) and it will have a conversation with the actor when the actor is beside it
 * and interact with it.
 * Besides, toad also speak every even turn automatically.
 *
 * @author Huang GuoYueYang, Lim Fluoryynx
 */
public class Toad extends Actor implements Speakable {

    /**
     * Hashmap to store sale items and their price
     */
    private HashMap<Item,Integer> saleItem=new HashMap<>();

    /**
     * price of super mushroom
     */
    private static final int SUPER_MUSHROOM_PRICE=400;

    /**
     * price of power star
     */
    private static final int POWER_STAR_PRICE=600;

    /**
     * price of wrench
     */
    private static final int WRENCH_PRICE=200;

    /**
     * Name of toad
     */
    private static final String ACTOR_NAME = "Toad";

    /**
     * Character of toad
     */
    private static final char DISPLAY_CHAR = 'O';

    /**
     * HitPoint of toad
     */
    private static final int HIT_POINT = 0;

    /**
     * Current turn of this game
     */
    private int currentTurn;

    /**
     * The lower bound index for the monologue of Toad when random talk
     */
    private static final int RANDOM_TALK_LOWER_BOUND = 3;

    /**
     * The upper bound index for the monologue of Toad when random talk
     */
    private static final int RANDOM_TALK_UPPER_BOUND = 6;

    /**
     * The lower bound index for the monologue of Toad when no power star talk
     */
    private static final int NO_POWER_STAR_LOWER_BOUND = 3;

    /**
     * The upper bound index for the monologue of Toad when no power star talk
     */
    private static final int NO_POWER_STAR_UPPER_BOUND = 5;

    /**
     * The lower bound index for the monologue of Toad when no wrench talk
     */
    private static final int NO_WRENCH_LOWER_BOUND = 4;

    /**
     * The upper bound index for the monologue of Toad when no wrench talk
     */
    private static final int NO_WRENCH_UPPER_BOUND = 6;

    /**
     * The lower bound index for the monologue of Toad when no wrench and power star talk
     */
    private static final int NO_POWER_STAR_WRENCH_LOWER_BOUND = 4;

    /**
     * The upper bound index for the monologue of Toad when no wrench and power star talk
     */
    private static final int NO_POWER_STAR_WRENCH_UPPER_BOUND = 5;




    /**
     * Constructor.
     */
    public Toad() {
        super(ACTOR_NAME, DISPLAY_CHAR, HIT_POINT); }

    /**
     * Give any sentences from the arraylist when Player interact with toad
     * The sentences is generate by index of Monologue
     * @return a monologue string retrieve from generateMonologue method
     */
    public String giveRandomTalk() {
        return generateMonologue(RANDOM_TALK_LOWER_BOUND,RANDOM_TALK_UPPER_BOUND);
    }

    /**
     * Give any sentences from the arraylist except the sentence about the power star when Player interact with toad
     * The sentences is generate by index of Monologue
     * @return a monologue string retrieve from generateMonologue method
     */
    public String noTalkPowerStar() {
        return generateMonologue(NO_POWER_STAR_LOWER_BOUND,NO_POWER_STAR_UPPER_BOUND);
    }

    /**
     * Give any sentences from the arraylist except the sentence about the wrench when Player interact with toad
     * The sentences is generate by index of Monologue
     * @return a monologue string retrieve from generateMonologue method
     */
    public String noTalkWrench() {
        return generateMonologue(NO_WRENCH_LOWER_BOUND,NO_WRENCH_UPPER_BOUND);
    }

    /**
     * Give any sentences from the arraylist except the sentence about the wrench and power star when Player interact with toad
     * The sentences is generate by index of Monologue
     * @return a monologue string retrieve from generateMonologue method
     */
    public String noTalkPowerStarAndWrench() {
        return generateMonologue(NO_POWER_STAR_WRENCH_LOWER_BOUND,NO_POWER_STAR_WRENCH_UPPER_BOUND);
    }


    /**
     * In each turn of this game, the current turn counter will increase by 1.
     * When the current turn is divisible by 2, Toad will speak automatically.
     * The monologue belongs to Toad will be print by using index.
     * Add items into the sale item list , clear the list every turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed , which in this case is DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Generate monologue of Toad when even turn
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " + generateMonologue(RANDOM_TALK_LOWER_BOUND,RANDOM_TALK_UPPER_BOUND));
        }

        saleItem.clear();
        saleItem.put(new PowerStar(),POWER_STAR_PRICE);
        saleItem.put(new SuperMushroom(),SUPER_MUSHROOM_PRICE);
        saleItem.put(new Wrench(),WRENCH_PRICE);
        return new DoNothingAction();
    }

    /**
     * Make Toad can speak to Player.
     * Besides, this also allow to Player can buy the three items from Toad.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status #HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // allow player to buy the three item from toad
        for (Item cost:saleItem.keySet()) {
            actions.add(new BuyAction(cost, saleItem.get(cost), cost.toString()));
        }
        // allow player to speak with toad
        if(otherActor.hasCapability(Status.SPEAK)) {
            actions.add(new SpeakAction(this));
        }
        if(! otherActor.hasCapability(Status.HAS_BOTTLE)) {
            actions.add(new GetBottleAction());
        }
        return actions;
    }
}