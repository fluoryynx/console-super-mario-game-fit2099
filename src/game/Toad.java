package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.MagicalItem;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.HashMap;
import java.util.Map;

public class Toad extends Actor {

    private static int SUPER_MUSHROOM_PRICE=400;
    private static int POWER_STAR_PRICE=600;
    private static int WRENCH_PRICE=200;

    private HashMap<Item,Integer> saleItem=new HashMap<>();

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    public Toad() {
        super("toad", 'O', 0);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        saleItem.clear();
        saleItem.put(new PowerStar(),POWER_STAR_PRICE);
        saleItem.put(new SuperMushroom(),SUPER_MUSHROOM_PRICE);
        saleItem.put(new Wrench(),WRENCH_PRICE);
        for (Behaviour behaviour: behaviours.values()){
            Action action=behaviour.getAction(this,map);
            if(action!=null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item cost:saleItem.keySet()) {
            actions.add(new BuyAction(cost, saleItem.get(cost), cost.toString()));
        }
        return actions;
    }

}