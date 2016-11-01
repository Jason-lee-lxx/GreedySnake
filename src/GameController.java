import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by  johnj on 10/31/16.
 */
public class GameController implements KeyListener, Runnable {

    private final Grid grid;
    private final GameView gameView;

    private enum STATUS {RUNNING, PAUSED, GAMEOVER, EXIT}

    private STATUS status;

    public GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.status = STATUS.PAUSED;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                grid.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                grid.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                grid.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                grid.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_ENTER:
                if (status == STATUS.GAMEOVER) {
                    grid.restart();
                    gameView.draw();
                    synchronized (this.status) {
                        status = STATUS.PAUSED;
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                synchronized (this.status) {
                    if (status == STATUS.RUNNING) status = STATUS.PAUSED;
                    else if (status == STATUS.PAUSED) status = STATUS.RUNNING;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                synchronized (this.status) {
                    status = STATUS.EXIT;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void run() {
        while (status != STATUS.EXIT) {
            //实际运行的时候,好像读不到这个值一样, 这里要等待一段时候, 等待变量值被改变?
            //两个while之间没有语句, 所以执行的速度是极快的, 几乎等不到status被改变, 这里要同步代码块, 但是该怎么写呢?
            synchronized (this.status) {
                while (status == STATUS.RUNNING) {
                    try {
                        Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
                    } catch (InterruptedException e) {
                        break;
                    }

                    if (grid.nextRound()) {
                        gameView.draw();
                    } else {
                        gameView.showGameOverMessage();
                        status = STATUS.GAMEOVER;
                    }
                }
            }
        }
    }

}
