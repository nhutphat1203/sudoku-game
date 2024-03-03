package Model;

public class GameState {
    private int counter;
    private final int maxCount;

    public GameState(int startValue, int maxCount) {
        this.counter = startValue;
        this.maxCount = maxCount;
    }

    public boolean isEndGame() {
        return counter == maxCount;
    }
    public void increaseCount() {
        ++this.counter;
    }
}
