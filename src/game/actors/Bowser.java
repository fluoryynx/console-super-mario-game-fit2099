package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Application;
import game.Status;
import game.behaviours.Behaviour;
import game.behaviours.FireAttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.Key;

import java.util.List;

public class Bowser extends Enemy{
    /**
     * Name of Bowser
     */
    private static final String BOWSER_NAME = "Bowser";

    /**
     * Character of Bowser
     */
    private static final char BOWSER_CHAR = 'B';

    /**
     * HitPoint of Bowser
     */
    private static final int HIT_POINT = 500;

    /**
     * HitRate of Bowser
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of Bowser
     */
    private static final int DAMAGE = 80;

    /**
     * Hit verb of Bowser
     */
    private static final String HIT_VERB = "punch";

    private static final int X_COORDINATE = 3;
    private static final int Y_COORDINATE = 4;


    /**
     * Constructor.
     */
    public Bowser() {
        super(BOWSER_NAME,BOWSER_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE,7,10);
        this.behaviours.remove(THIRD_PRIORITY);
        this.addCapability(Status.DROP_KEY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location currentLocation = map.locationOf(this);
        List<Item> items = currentLocation.getItems();
        int fireCounter = 0;
        for (Item item: items){
            if (item.hasCapability(Status.FIRE_DAMAGE)){
                fireCounter += 1;
            }
        }
        if (fireCounter>0){
            this.hurt(20*fireCounter);
        }
        if (!this.isConscious()){
            currentLocation.addItem(new Key());
            map.removeActor(this);
            return new DoNothingAction();
        }
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            // If the destination has an actor with capability HOSTILE_TO_ENEMY
            if (destination.getActor()!=null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                // Enemy follow the actor
                behaviours.put(SECOND_PRIORITY,new FollowBehaviour(destination.getActor()));
            }
        }
        if (this.hasCapability(Status.RESET_CALLED)){
            behaviours.remove(SECOND_PRIORITY);
        }
        this.removeCapability(Status.RESET_CALLED);
        this.behaviours.put(FIRST_PRIORITY,new FireAttackBehaviour());

        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        super.resetInstance();
        GameMap currentMap = Application.getSecondGameMap();
        if (!currentMap.at(X_COORDINATE,Y_COORDINATE).containsAnActor()){
            currentMap.moveActor(this, currentMap.at(X_COORDINATE,Y_COORDINATE));
        }
        // Heal the Bowser to maximum
        this.heal(this.getMaxHp());
    }
}