package snakegame;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MouseController extends JFrame implements ActionListener {

    private JButton botonUP, botonRight, botonLeft, botonDOWN;
    private Snake snake;

    public MouseController(Snake snake) {
        this.snake = snake;
    }

    public void BotonesClick() {

        // Layout absoluto
        setLayout(null);

        // Tamaño de la ventana
        setBounds(0, 0, 640, 480);

        // No redimensionable
        setResizable(false);

        // Cerrar proceso al cerrar la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Boton UP
        botonUP = new JButton();
        botonUP.setBounds(0, 0, 640, 240);
        add(botonUP);
        botonUP.addActionListener(this);

        // Boton DOWN
        botonDOWN = new JButton();
        botonDOWN.setBounds(0, 240, 640, 240);
        add(botonDOWN);
        botonDOWN.addActionListener(this);

        // Boton RIGHT
        botonRight = new JButton();
        botonRight.setBounds(0, 0, 320, 480);
        add(botonRight);
        botonRight.addActionListener(this);

        // Boton LEFT
        botonLeft = new JButton();
        botonLeft.setBounds(0, 320, 320, 480);
        add(botonLeft);
        botonLeft.addActionListener(this);

        // Volem que estigui tot
         setVisible(true);
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
