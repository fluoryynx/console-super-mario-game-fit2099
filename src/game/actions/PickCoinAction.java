package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Wallet;
import game.items.Coin;

public class PickCoinAction extends Action {
    private Coin coin;

    public PickCoinAction(Coin coin) {
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Wallet.getInstance().addBalance(coin.getValue());
        System.out.println("Wallet balance: " + Wallet.getInstance().getBalance());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player picks up " + coin.getValue() + " coins.";
    }
}