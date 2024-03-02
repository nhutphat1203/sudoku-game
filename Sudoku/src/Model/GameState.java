package Model;

public class GameState {
    private int counter;
    private int startValue;
    private int maxCount;
    public GameState(int maxCount) {
        counter = startValue = 0;
        this.maxCount = maxCount;
    }
    public GameState(int startValue, int maxCount) {
        this.counter = this.startValue = startValue;
        this.maxCount = maxCount;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
    public boolean isEndGame() {
        return counter == maxCount;
    }
    public void increaseCount() {
        ++this.counter;
    }
}
