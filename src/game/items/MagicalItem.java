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

    public void updateStatus(Actor actor){
        this.consumer=actor;
    };

    public abstract void currentStatus( Location location);

    public boolean getIsExpired(){
        return this.isExpired;
    }

    public void setIsExpired(boolean IsExpired) {
        this.isExpired = isExpired;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (droppable){
            return new DropItemAction(this);
        }
        return null;
    }
}
