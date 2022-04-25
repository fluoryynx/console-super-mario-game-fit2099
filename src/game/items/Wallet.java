package game.items;

public class Wallet {

    // private int balance;
    private int balance = 0;

    /**
     * A singleton reset manager instance
     */
    private static Wallet instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static Wallet getInstance(){
        if(instance == null){
            instance = new Wallet();
        }
        return instance;
    }


    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void minusBalance(int balance) {
        this.balance -= balance;
    }


    public int getBalance() {
        // return Wallet.value;
        return this.balance;
    }

}