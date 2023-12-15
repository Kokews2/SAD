package snakegame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controller implements KeyListener {

    private Snake snake;

    public Controller(Snake snake) {
        this.snake = snake;
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
