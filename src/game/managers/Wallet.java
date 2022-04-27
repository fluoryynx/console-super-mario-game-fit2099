package game.managers;

public class Wallet {

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

    /**
     * add certain value to wallet balance
     * @param balance
     */
    public void addBalance(int balance) {
        this.balance += balance;
    }

    /**
     * subtract certain value from wallet balance
     * @param balance
     */
    public void minusBalance(int balance) {
        this.balance -= balance;
    }

    /**
     * getter to return walet balance
     * @return wallet balance
     */
    public int getBalance() {
        return this.balance;
    }

}