package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.Wallet;
import game.items.Coin;

public class PickCoinAction extends Action {
    private Coin coin;

    /**
     * constructor
     * @param coin - coin pick up by player from the game map
     */
    public PickCoinAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * remove coin from the game map after actor picks it up
     * add value of the coin into actor's wallet balance
     * display updated wallet balance into the console
     * return the value of coin picked up in the menu description
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Wallet.getInstance().addBalance(coin.getValue());
        System.out.println("Wallet balance: " + Wallet.getInstance().getBalance());
        return menuDescription(actor);
    }

    /**
     * return the value of coin picked up in the menu description
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player picks up " + coin.getValue() + " coins.";
    }
}