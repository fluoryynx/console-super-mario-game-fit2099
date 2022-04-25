package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Wallet;

public class BuyAction extends Action {
    private Item item;
    private int cost;
    private String name;

    public BuyAction(Item item,int cost,String name){
        this.item=item;
        this.cost=cost;
        this.name=name;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + name + " ($ " + this.cost + ")";
    }


}

