package snakegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MouseController implements ActionListener {

    private JButton botonUP, botonRight, botonLeft, botonDOWN;
    private Snake snake;
    private JFrame frame;

    public MouseController(Snake snake, JFrame frame) {
        this.snake = snake;
        this.frame = frame;
        BotonesClick();
    }

    public void BotonesClick() {

        // Boton UP
        botonUP = new JButton();
        botonUP.setBounds(0, 0, 640, 240);
        frame.add(botonUP);
        botonUP.setVisible(false);
        botonUP.addActionListener(this);

        // Boton DOWN
        botonDOWN = new JButton();
        botonDOWN.setBounds(0, 240, 640, 240);
        frame.add(botonDOWN);
        botonDOWN.setVisible(false);
        botonDOWN.addActionListener(this);

        // Boton RIGHT
        botonRight = new JButton();
        botonRight.setBounds(0, 0, 320, 480);
        frame.add(botonRight);
        botonRight.setVisible(false);
        botonRight.addActionListener(this);

        // Boton LEFT
        botonLeft = new JButton();
        botonLeft.setBounds(0, 320, 320, 480);
        frame.add(botonLeft);
        botonLeft.setVisible(false);
        botonLeft.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == botonUP && snake.getDirection()!= Snake.DOWN) {
            snake.setDirection(Snake.UP);
        }    
        if (e.getSource() == botonDOWN && snake.getDirection()!= Snake.UP) {
            snake.setDirection(Snake.DOWN);
        }  
        if (e.getSource() == botonRight && snake.getDirection()!= Snake.LEFT) {
            snake.setDirection(Snake.RIGHT);
        }  
        if (e.getSource() == botonLeft && snake.getDirection()!= Snake.RIGHT) {
            snake.setDirection(Snake.LEFT);
        }  
    }

}
