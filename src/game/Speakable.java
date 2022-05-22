package game;

/**
 * It is an interface that will be implemented by those class that need to speak every even turn.
 * The reason to create this interface instead of just directly putting the following two methods in
 * each actor class is because this kind of design allow for extension.
 *
 * @author Huang GuoYueYang
 */
public interface Speakable {

    /**
     * A default interface method that generate the monologue sentence from Monologue instance by using boundary index
     * @param monologueIndexLowerBound Lower boundary of index
     * @param monologueIndexUpperBound Upper boundary of index
     * @return the suitable monologue string
     */
    default String generateMonologue(int monologueIndexLowerBound, int monologueIndexUpperBound){
        return Monologue.getInstance().getMonologue(monologueIndexLowerBound,monologueIndexUpperBound);
    }

    /**
     * A default interface method that check whether current turn is even turn or not
     * @param currentTurn current turn of this game
     * @return true if currentTurn is an even number
     */
    default boolean timeToSpeak(int currentTurn){
        return currentTurn % 2 == 0;
    }
}
