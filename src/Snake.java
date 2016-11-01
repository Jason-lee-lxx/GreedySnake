import java.util.LinkedList;

/**
 * Created by  johnj on 10/31/16.
 */
public class Snake {

    private LinkedList<Node> body = new LinkedList<>();

    public boolean eat(Node food) {
        if (getHead().equals(food)) {
            addTail(food);
            return true;
        }
        return false;
    }

    public Node move(Direction d) {
        int x = getHead().getX();
        int y = getHead().getY();

        switch (d) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        addHead(new Node(x, y));
        return body.removeLast();
    }

    public Node getHead() {
        return body.getFirst();
    }

    public Node addHead(Node head) {
        body.addFirst(head);
        return head;
    }

    public Node addTail(Node tail) {
        body.addLast(tail);
        return tail;
    }

    public LinkedList<Node> getBody() {
        return body;
    }
}
