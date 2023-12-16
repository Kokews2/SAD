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

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_W:
                if(snake.getDirection() != Snake.DOWN) 
                    snake.setDirection(Snake.UP);
                break;
            case KeyEvent.VK_A:
                if(snake.getDirection() != Snake.RIGHT)
                    snake.setDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_S:
                if(snake.getDirection() != Snake.UP)
                    snake.setDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_D:
                if(snake.getDirection() != Snake.LEFT)
                    snake.setDirection(Snake.RIGHT);
                break;
                case KeyEvent.VK_UP:
                if (snake2.getDirection() != Snake.DOWN)
                    snake2.setDirection(Snake.UP);
                break;
            case KeyEvent.VK_LEFT:
                if (snake2.getDirection() != Snake.RIGHT)
                    snake2.setDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                if (snake2.getDirection() != Snake.UP)
                    snake2.setDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                if (snake2.getDirection() != Snake.LEFT)
                    snake2.setDirection(Snake.RIGHT);
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
