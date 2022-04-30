package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.managers.Wallet;

/**
 * BuyAction is an action that allows the player to use coin to purchase items from the toad.
 * It is trigger by the Toad class when players approach the toad and enter the purchase item hotkey
 *
 * @author Lim Fluoryynx
 */
public class BuyAction extends Action {
    /**
     * item player wants to buy
     */
    private Item item;

    /**
     * price of the item
     */
    private int cost;

    /**
     * name of the item
     */
    private String name;

    /**
     * Constructor
     * @param item - the item to be purchased by actor
     * @param cost - amount of coin required to purchase the item
     * @param name - name of the item
     */
    public BuyAction(Item item,int cost,String name){
        this.item=item;
        this.cost=cost;
        this.name=name;
    }

    /**
     * Add the item to the actor's inventory and subtract the cost from actor's wallet balance
     *  display "You don't have enough coins!" is actor's balance is insufficient
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output="";
        if(actor.hasCapability(Status.BUY) && Wallet.getInstance().getBalance()>=this.cost){
            actor.addItemToInventory(item);
            Wallet.getInstance().minusBalance(cost);
            output+= actor + " purchased a " + this.item;
        }
        else{
            output+="You don't have enough coins!";
        }
        return output;
    }

    /**
     * Describe the buy action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player buys Super Mushroom ($400)"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + name + " ($ " + this.cost + ")";
    }


}

