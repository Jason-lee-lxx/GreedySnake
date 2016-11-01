import java.util.Arrays;
import java.util.Random;

/**
 * Created by  johnj on 10/31/16.
 */
public class Grid {

    private final int width;
    private final int height;

    private final boolean status[][];
    private Snake snake;
    private Node food;

    private Direction snakeDirection = Direction.LEFT; //初始方向

    private Random random = new Random();

    public Grid(int width, int length) {
        this.width = width;
        this.height = length;

        status = new boolean[width][length];
        for (boolean[] stat : status) {
            Arrays.fill(stat, false);
        }

        initSnake();
        createFood();
    }

    private Snake initSnake() {
        snake = new Snake();
        for (int i = 0; i < Settings.INIT_SNAKE_LENGTH; i++) {
            int x = width / 2 + i;
            int y = height / 2;
            snake.addTail(new Node(x, y));
            status[x][y] = true;
        }
        return snake;
    }

    private Node createFood() {
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (status[x][y]);

        food = new Node(x, y);
        return food;
    }

    public void restart() {
        for (boolean[] stat : status) {
            Arrays.fill(stat, false);
        }

        initSnake();
        createFood();
    }

    public boolean nextRound() {
        Node tail = snake.move(snakeDirection);
        Node head = snake.getHead();

        if (validPosition(head)) {
            if (head.equals(food)) {
                snake.addTail(tail);
                createFood();
            } else {
                dispose(tail);
            }
            occupy(head);
            return true;
        }
        return false;
    }

    private void dispose(Node node) {
        status[node.getX()][node.getY()] = false;
    }

    private void occupy(Node node) {
        status[node.getX()][node.getY()] = true;
    }

    private boolean validPosition(Node node) {
        int x = node.getX(), y = node.getY();
        return x >= 0 && x < width && y >= 0 && y < height && !status[x][y];
    }

    public void changeDirection(Direction d) {
        if (snakeDirection.compatibleWith(d)) {
            snakeDirection = d;
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Node getFood() {
        return food;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
