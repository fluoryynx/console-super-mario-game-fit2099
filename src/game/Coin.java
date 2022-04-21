package game;

import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {
    private int amount;

    public Coin(int amount) {
        super("coin", '$', true);
        this.amount = amount;
    }
}
