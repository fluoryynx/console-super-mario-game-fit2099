package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

public abstract class MagicalItem extends Item {

    private boolean droppable;
    private ConsumeAction consumeAction = new ConsumeAction(this);
    protected boolean isExpired;
    protected Actor consumer;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
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
     * @param actor
     */
    public void updateStatus(Actor actor){
        this.consumer=actor;
    };

    /**
     *  the current status of magical item in actor's inventory
     * @param location
     */
    public abstract void currentStatus( Location location);

    /**
     * @return true if effect of magical items wear off
     */
    public boolean getIsExpired(){
        return this.isExpired;
    }

    /**
     * set isExpired to true if effect of magical items wear off
     * @param IsExpired
     */
    public void setIsExpired(boolean IsExpired) {
        this.isExpired = IsExpired;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (droppable){
            return new DropItemAction(this);
        }
        return null;
    }
}
