package snakegame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame {

    private int width = 640;    //Width of the window
    private int height = 480;   //Height of the window
    private int frequency = 50; //Frequency en ms

    private Snake snake;
    private Timer timer;

    public SnakeGame() {
        //Create and set up the window. Exit on close
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add Snake
        snake = new Snake(width/2, height/2);
        frame.setContentPane(snake);

        //Add the key events
        Controller controller = new Controller(snake);
        frame.addKeyListener(controller);

        //Upload the game with Timer from Swing
        timer = new Timer(frequency, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.update(width, height);
            }
        });
        timer.start();

        //Display de window
        frame.pack();
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
