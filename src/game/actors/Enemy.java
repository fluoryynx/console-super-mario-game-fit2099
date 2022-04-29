package game.actors;

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

/**
 * Enemy is an abstract class represents the enemies in the game. It is a class that extends from the Actor.
 * There are two types of enemies in this game, which is Goomba and Koopa.
 *
 * @author Kuah Jia Chen
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * Hashmap to store behaviors.
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();  // list of behaviours store in hashmap

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the first priority
     */
    protected static final int FIRST_PRIORITY = 1; // key of hashmap

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the second priority
     */
    protected static final int SECOND_PRIORITY = 2; // key of hashmap

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the third priority
     */
    protected static final int THIRD_PRIORITY = 3; // key of hashmap

    /**
     * Damage done by this enemy instance
     */
    private int damage;  // deduct in hit point of enemy

    /**
     * Attack verb
     */
    private String verb; // Attack verb

    /**
     * The hit rate of this enemy instance
     */
    private int hitRate; // Possibility of enemy hit actor

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit point
     * @param damage      the deduction in hit point of enemy
     * @param verb        the attack verb of enemy
     * @param hitRate     the possibility of enemy hit actor
     */
    public Enemy(String name, char displayChar, int hitPoints, int damage, String verb, int hitRate) {
        super(name, displayChar, hitPoints);
        this.damage = damage;
        this.behaviours.put(THIRD_PRIORITY,new WanderBehaviour());
        this.verb = verb;
        this.addCapability(Status.IS_ENEMY);
        this.hitRate = hitRate;
        this.registerInstance(); // append to the array list in ResetManager
    }

    /**
     * Select and return an action to perform on the current turn.
     * For each exits in this game, if the destination is not null and the actor around the enemy has capability HOSTILE_TO_ENEMY,
     * the enemy will follow the actor in second priority.
     * If the actor is around enemy, enemy will attack actor in first priority.
     * Other than that, enemy will wander around then return do nothing action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // when the enemy has this capability, it means the reset action is selected by the user
        // hence it will be removed from the map
        if (this.hasCapability(Status.RESET_CALLED)){
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
        // If under successful hit rate, enemy will attack actor in first priority
        if(rand.nextInt(100)<=hitRate){
            this.behaviours.put(FIRST_PRIORITY,new AttackBehaviour());
        }
        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     * Enemy is allowed to be attack by the actor with capability HOSTILE_TO_ENEMY
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious()) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Creates and returns an intrinsic weapon.
     * Actor attack other actors with more interesting descriptions and/or different damage.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage,verb);
    }

    /**
     * Add RESET_CALLED to its capability set
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }
}
