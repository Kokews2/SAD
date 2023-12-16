package snakegame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame { 

    private int width = 640;    //Width of the window
    private int height = 480;   //Height of the window
    private int frequency = 50; //Frequency en ms

    private Snake snake;
    private Food food;
    private Board board;
    private Timer timer;
º
    public SnakeGame() {
        //Creem i configurem la finestra. Sortir al tancar
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Start Game
        snake = new Snake(width/2, height/2);
        food = new Food(width, height);
        board = new Board(snake, food);

        //Add Board
        frame.setContentPane(board);

        //Afegir els key events
        Controller controller = new Controller(snake);
        frame.addKeyListener(controller);

        //Posar el joc amb Timer de Swing
        timer = new Timer(frequency, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update(width, height);
            }
        });
        timer.start();

        //Mostrar finestra
        frame.pack();
        frame.setSize(width,height);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void update(int width, int height) {
        snake.move(width, height);
        checkCollisions(food);
        board.repaint();
    }

    private void checkCollisions(Food food) {
        if (snake.collidesWithFood(food)) {
            snake.grow();
            food.placeFood(width, height);  // Coloca nueva comida en el tablero
        } else if (snake.collidesWithSelf()) {
            gameOver();
        }
    }

    private void gameOver() {
        timer.stop();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
