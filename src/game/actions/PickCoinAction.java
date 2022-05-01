package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.Wallet;
import game.items.Coin;

/**
 * This class extends the Action class to allow player to pick up coins
 * add coin value to wallet balance
 * This class has association relationship with the Coin class
 * as there will be a coin attribute initialised in the PickCoinAction constructor
 * so that it knows which coin (of what value) is picked.
 *
 * @author Lim Fluoryynx
 */
public class PickCoinAction extends Action {
    /**
     * the coin pick up by player
     */
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
     * return the value of coin picked up in the menu description
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Wallet.getInstance().addBalance(coin.getValue());
        return menuDescription(actor);
    }

    /**
     * return the value of coin picked up in the menu description
     * @param actor The actor performing the action.
     * @return a string, e.g. "Mario picks up 5 coins."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + coin.getValue() + " coins.";
    }
}