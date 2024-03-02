package Model;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj || getClass() != obj.getClass())
            return false;
        Coordinates that = (Coordinates) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
