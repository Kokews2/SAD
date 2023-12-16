package snakegame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class Ratoli implements MouseListener{
    
    private Snake snake;
    private JFrame frame;

    public Ratoli(Snake snake, JFrame frame) {
        this.snake = snake;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int dir = snake.getDirection();

        if (x<640/2 && dir != Snake.LEFT && dir !=Snake.RIGHT) {
            snake.setDirection(Snake.LEFT);
        }    
        if (x>frame.getWidth()/2 && dir != Snake.LEFT && dir !=Snake.RIGHT) {
            snake.setDirection(Snake.RIGHT);
        }      
        if (y<frame.getHeight()/2 && dir!= Snake.UP && dir!=Snake.DOWN) {
            snake.setDirection(Snake.UP);
        }    
        if (y>frame.getHeight()/2 && dir!= Snake.UP && dir!=Snake.DOWN) {
            snake.setDirection(Snake.DOWN);
        }    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}
