package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

/**
 * This class is an abstract class extended from the Item class to provide blueprint for magical items
 * such as Super Mushroom and Power Star classes.
 *
 * @author Lim Fluoryynx
 */
public abstract class MagicalItem extends Item {

    /**
     * to indicate whether item can be drop
     */
    private boolean droppable;
    /**
     * consume action to consume item
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);
    /**
     * to indicate whether item is expired
     */
    protected boolean isExpired;
    /**
     * actor that consumes the item
     */
    protected Actor consumer;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param droppable true if and only if the Item can be dropped
     */
    public MagicalItem(String name, char displayChar, boolean portable, boolean droppable) {
        super(name, displayChar, portable);
        this.droppable = droppable;
        this.addCapability(Status.CONSUMABLE);
        this.isExpired = false;
    }

    /**
     * allow actor to consume magical item that exists in their inventory
     * disable actor's consume action after actor consumes the item
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (this.getAllowableActions().contains(consumeAction)){
            this.removeAction(consumeAction);
        }
        if (actor.getInventory().contains(this)){
            this.addAction(consumeAction);
        }
    }

    /**
     * update actor's status upon consumption of magical item
     * @param actor the actor that consumed the magical item
     * @throws IllegalArgumentException if actor is null
     */
    public void updateStatus(Actor actor){

        if (actor == null){
            throw new IllegalArgumentException("The input parameter (i.e., actor) cannot be null");
        }

        this.consumer=actor;
    }

    /**
     *  the current status of magical item in actor's inventory
     * @param location the location of current actor
     */
    public abstract void currentStatus(Location location);

    /**
     * @return true if effect of magical items wear off
     */
    public boolean getIsExpired(){
        return this.isExpired;
    }

    /**
     * set isExpired to true if effect of magical items wear off
     * @param IsExpired indicating the magical item is expired or not
     */
    public void setIsExpired(boolean IsExpired) {
        this.isExpired = IsExpired;
    }

    /**
     * Create and return an action to drop this Item if it is droppable.
     * @param actor the actor who currently having this magical item
     * @return drop action is the magical item is droppable, else return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (droppable){
            return new DropItemAction(this);
        }
        return null;
    }
}
