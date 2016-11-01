/**
 * Created by  johnj on 10/31/16.
 */
public class Node {

    private final int x;
    private final int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNeighborTo(Node other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY()) == 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Node)) return false;

        Node n = (Node) o;
        return x == n.getX() && y == n.getY();
    }
}
