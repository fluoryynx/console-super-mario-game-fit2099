package game;

public interface Speakable {

    default String generateMonologue(int monologueIndexLowerBound, int monologueIndexUpperBound){
        return Monologue.getInstance().getMonologue(monologueIndexLowerBound,monologueIndexUpperBound);
    }

    default boolean timeToSpeak(int currentTurn){
        return currentTurn%2 == 0;
    }
}
