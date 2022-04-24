package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Enemy extends Actor implements Resettable {
    // Attribute
    private int damage;
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();
    protected static final int FIRST_PRIORITY = 1;
    protected static final int SECOND_PRIORITY = 2;
    protected static final int THIRD_PRIORITY = 3;
    private String verb;
    private int hitRate;
    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, int damage, String verb, int hitRate) {
        super(name, displayChar, hitPoints);
        this.damage = damage;
        this.behaviours.put(THIRD_PRIORITY,new WanderBehaviour());
        this.verb = verb;
        this.addCapability(Status.IS_ENEMY);
        this.hitRate = hitRate;
        this.registerInstance();
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.RESET_CALLED)){
            map.removeActor(this);
            return new DoNothingAction();
        }

        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor()!=null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                behaviours.put(SECOND_PRIORITY,new FollowBehaviour(destination.getActor()));
            }
        }
        if(rand.nextInt(100)<=hitRate){
            this.behaviours.put(FIRST_PRIORITY,new AttackBehaviour());
        }
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious()) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage,verb);
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }
}
