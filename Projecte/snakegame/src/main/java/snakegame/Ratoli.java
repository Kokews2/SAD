package snakegame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Ratoli implements MouseListener{
    
    private Snake snake;
    private JFrame frame;

    public Ratoli(Snake snake, JFrame frame) {
        this.snake = snake;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        double x = e.getX();
        double y = e.getY();
        if (x<frame.getWidth()/2 && (snake.getDirection()!= Snake.LEFT || snake.getDirection()!=Snake.RIGHT)) {
            snake.setDirection(Snake.LEFT);
        }    
        if (x>frame.getWidth()/2 && (snake.getDirection()!= Snake.LEFT || snake.getDirection()!=Snake.RIGHT)) {
            snake.setDirection(Snake.RIGHT);
        }      
        if (y<frame.getHeight()/2 && (snake.getDirection()!= Snake.UP || snake.getDirection()!=Snake.DOWN)) {
            snake.setDirection(Snake.UP);
        }    
        if (y>frame.getHeight()/2 && (snake.getDirection()!= Snake.UP || snake.getDirection()!=Snake.DOWN)) {
            snake.setDirection(Snake.DOWN);
        }    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}
