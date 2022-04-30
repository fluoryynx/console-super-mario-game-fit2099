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
     * @param balance an integer that indicating the amount of increase in the balance
     * @throws IllegalArgumentException if balance is 0 or negative
     */
    public void addBalance(int balance) {

        if (balance <= 0){
            throw new IllegalArgumentException("The balance cannot be 0 or negative");
        }

        this.balance += balance;
    }

    /**
     * subtract certain value from wallet balance
     * @param balance an integer that indicating the amount of decrease in the balance
     * @throws IllegalArgumentException if balance is greater than the remaining balance
     */
    public void minusBalance(int balance) {
        if (balance > this.balance){
            throw new IllegalArgumentException("The balance cannot be greater than the remaining balance");
        }
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