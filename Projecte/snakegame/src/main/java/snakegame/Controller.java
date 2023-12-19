package snakegame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controller implements KeyListener {

    private Snake snake;
    private Snake snake2;

    public Controller(Snake snake) {
        this.snake = snake;
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    private void handleDirectionChange(int key, Snake currentSnake, int upKey, int leftKey, int downKey, int rightKey) {
        if (key == upKey && currentSnake.getDirection() != Snake.DOWN) {
            currentSnake.setDirection(Snake.UP);
        } else if (key == leftKey && currentSnake.getDirection() != Snake.RIGHT) {
            currentSnake.setDirection(Snake.LEFT);
        } else if (key == downKey && currentSnake.getDirection() != Snake.UP) {
            currentSnake.setDirection(Snake.DOWN);
        } else if (key == rightKey && currentSnake.getDirection() != Snake.LEFT) {
            currentSnake.setDirection(Snake.RIGHT);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                handleDirectionChange(key, snake, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
                if (snake2 != null)
                    handleDirectionChange(key, snake2, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN,
                            KeyEvent.VK_RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no l'utilizem pero es necesari per KeyListener
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // no l'utilizem pero es necesari per KeyListener
    }
}
