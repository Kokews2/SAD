package snakegame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    
    private Snake snake;
    private Board board;

    public MouseHandler(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int dir = snake.getDirection();

        if (x<board.getWidth()/2 && dir != Snake.LEFT && dir !=Snake.RIGHT) {
            snake.setDirection(Snake.LEFT);
        }    
        if (x>board.getWidth()/2 && dir != Snake.LEFT && dir !=Snake.RIGHT) {
            snake.setDirection(Snake.RIGHT);
        }      
        if (y<board.getHeight()/2 && dir!= Snake.UP && dir!=Snake.DOWN) {
            snake.setDirection(Snake.UP);
        }    
        if (y>board.getHeight()/2 && dir!= Snake.UP && dir!=Snake.DOWN) {
            snake.setDirection(Snake.DOWN);
        }    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No l'utilizem pero es necessari per MouseListener
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No l'utilizem pero es necessari per MouseListener
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No l'utilizem pero es necessari per MouseListener
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No l'utilizem pero es necessari per MouseListener
    }

}
