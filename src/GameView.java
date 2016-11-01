import javax.swing.*;
import java.awt.*;

/**
 * Created by  johnj on 10/31/16.
 */
public class GameView {

    private final Grid grid;
    private JPanel canvas;

    public GameView(Grid grid) {
        this.grid = grid;
    }

    public void init() {
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                drawGridBackground(graphics);
                drawSnake(graphics, grid.getSnake());
                drawFood(graphics, grid.getFood());
            }
        };
    }

    public void draw() {
        canvas.repaint();
    }

    public JPanel getCanvas() {
        return canvas;
    }

    private void drawFood(Graphics graphics, Node food) {
        drawSquare(graphics, food, Settings.DEFAULT_FOOD_COLOR);
    }

    private void drawSnake(Graphics graphics, Snake snake) {
        drawCircle(graphics, snake.getHead(), Settings.DEFAULT_SNAKE_HEAD_COLOR);
        for (int i = 1; i < snake.getBody().size(); i++) {
            drawCircle(graphics, snake.getBody().get(i), Settings.DEFAULT_SNAKE_BODY_COLOR);
        }
    }

    private void drawGridBackground(Graphics graphics) {
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(0, 0, grid.getWidth() * size, grid.getHeight() * size);
    }

    private void drawSquare(Graphics graphics, Node square, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(square.getX() * size, square.getY() * size, size - 1, size - 1);
    }

    private void drawCircle(Graphics graphics, Node square, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(square.getX() * size, square.getY() * size, size, size);
    }

    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(canvas, "游戏结束", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
    }
}
