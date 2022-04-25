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

public class Toad extends Actor {
    private ArrayList<String> toadTalk = new ArrayList<>();
    private static final int POWER_STAR_INDEX = 1;
    private static final int WRENCH_INDEX = 0;
    private Random rand = new Random();
    private static final int SUPER_MUSHROOM_PRICE=400;
    private static final int POWER_STAR_PRICE=600;
    private static final int WRENCH_PRICE=200;
    private HashMap<Item,Integer> saleItem=new HashMap<>();
    private static final String ACTOR_NAME = "toad";
    private static final char DISPLAY_CHAR = 'O';
    private static final int HIT_POINT = 0;


    public Toad() {
        super(ACTOR_NAME, DISPLAY_CHAR, HIT_POINT);
        toadTalk.add("You might need a wrench to smash Koopa's hard shells.");
        toadTalk.add("You better get back to finding the Power Stars.");
        toadTalk.add("The Princess is depending on you! You are our only hope.");
        toadTalk.add("Being imprisoned in these walls can drive a fungus crazy :(");
    }

    public String giveRandomTalk() {
        return getReplyString(null);
    }

    public String noTalkPowerStar() {
        return getReplyString(POWER_STAR_INDEX);
    }

    public String noTalkWrench() {
        return getReplyString(WRENCH_INDEX);
    }

    public String getReplyString(Integer removeIndex){
        int currentIndex;
        if (removeIndex == null){
            currentIndex = rand.nextInt(toadTalk.size());
        } else {
            currentIndex = rand.nextInt(toadTalk.size());
            while (currentIndex == removeIndex){ // 1 == 1
                currentIndex = rand.nextInt(toadTalk.size());
            }
        }
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