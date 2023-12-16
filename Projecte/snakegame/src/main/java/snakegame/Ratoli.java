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
        
    }

}
