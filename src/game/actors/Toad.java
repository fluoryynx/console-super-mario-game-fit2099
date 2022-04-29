package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.BuyAction;
import game.actions.SpeakAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Toad is a class that represents the NPC Toad in the game map. It is a class that extends from the Actor class.
 * Toad is a NPC is friendly (0 hits point) and it will have a conversation with the actor when
 * the actor is beside it.
 *
 * @author Huang GuoYueYang
 */
public class Toad extends Actor {
    /**
     * ArrayList of string to store the sentences
     */
    private ArrayList<String> toadTalk = new ArrayList<>();

    /**
     * Hashmap to store sale items and their price
     */
    private HashMap<Item,Integer> saleItem=new HashMap<>();

    /**
     * Index of the sentence about power star
     */
    private static final int POWER_STAR_INDEX = 1;

    /**
     * Index of the sentence about wrench
     */
    private static final int WRENCH_INDEX = 0;

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
    private static final String ACTOR_NAME = "toad";

    /**
     * Character of toad
     */
    private static final char DISPLAY_CHAR = 'O';

    /**
     * HitPoint of toad
     */
    private static final int HIT_POINT = 0;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor.
     * Four sentences of toad added here
     */
    public Toad() {
        super(ACTOR_NAME, DISPLAY_CHAR, HIT_POINT);
        toadTalk.add("You might need a wrench to smash Koopa's hard shells.");
        toadTalk.add("You better get back to finding the Power Stars.");
        toadTalk.add("The Princess is depending on you! You are our only hope.");
        toadTalk.add("Being imprisoned in these walls can drive a fungus crazy :(");
    }

    /**
     * Give any sentences from the arraylist
     */
    public String giveRandomTalk() {
        return getReplyString(null);
    }

    /**
     * Give any sentences from the arraylist except the sentence about the power star
     */
    public String noTalkPowerStar() {
        return getReplyString(POWER_STAR_INDEX);
    }

    /**
     * Give any sentences from the arraylist except the sentence about the wrench
     */
    public String noTalkWrench() {
        return getReplyString(WRENCH_INDEX);
    }

    /**
     * If the removeIndex equals to null, then the currentIndex will be any random
     * number within the size of the toadTalk Array.
     * Else we put the currentIndex as any random number within the size of the toadTalk Array.
     * If the currentIndex equals to the removeIndex, the currentIndex will jump over that removeIndex,
     * so the corresponding sentence will not be print.
     * @param removeIndex the index of the sentence to be remove
     */
    public String getReplyString(Integer removeIndex){
        int currentIndex;
        // check if the removeIndex is null
        if (removeIndex == null){
            // Any sentence from the arraylist can be print
            currentIndex = rand.nextInt(toadTalk.size());
        } else {
            currentIndex = rand.nextInt(toadTalk.size());
            // check if currentIndex equals to the input removeIndex
            while (currentIndex == removeIndex){ // 1 == 1
                // jump over it
                currentIndex = rand.nextInt(toadTalk.size());
            }
        }
        // give the corresponding sentences
        return toadTalk.get(currentIndex);
    }

    /**
     * add items into the sale item list , clear the list every turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed , which in this case is DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        saleItem.clear();
        saleItem.put(new PowerStar(),POWER_STAR_PRICE);
        saleItem.put(new SuperMushroom(),SUPER_MUSHROOM_PRICE);
        saleItem.put(new Wrench(),WRENCH_PRICE);
        return new DoNothingAction();
    }

    /**
     * Make Toad can speak to Player. Besides, this also allow to Player can buy the three item
     * from Toad.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
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
        return actions;
    }
}