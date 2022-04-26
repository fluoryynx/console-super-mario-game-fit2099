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
 * Class representing the Toad.
 */
public class Toad extends Actor {
    // Attributes
    private ArrayList<String> toadTalk = new ArrayList<>();
    private HashMap<Item,Integer> saleItem=new HashMap<>();
    private static final int POWER_STAR_INDEX = 1;
    private static final int WRENCH_INDEX = 0;
    private static final int SUPER_MUSHROOM_PRICE=400;
    private static final int POWER_STAR_PRICE=600;
    private static final int WRENCH_PRICE=200;
    private static final String ACTOR_NAME = "toad";
    private static final char DISPLAY_CHAR = 'O';
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

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        saleItem.clear();
        saleItem.put(new PowerStar(),POWER_STAR_PRICE);
        saleItem.put(new SuperMushroom(),SUPER_MUSHROOM_PRICE);
        saleItem.put(new Wrench(),WRENCH_PRICE);
        return new DoNothingAction();
    }

    /**
     * Make Toad can speak to Player.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item cost:saleItem.keySet()) {
            actions.add(new BuyAction(cost, saleItem.get(cost), cost.toString()));
        }
        if(otherActor.hasCapability(Status.SPEAK)) {
            actions.add(new SpeakAction(this));
        }
        return actions;
    }
}