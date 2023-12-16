package snakegame;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame { 

    private int width = 640;    //Width of the window
    private int height = 480;   //Height of the window
    private int frequency = 50; //Frequency en ms
    private int score = 0;

    private JFrame frame;

    private Snake snake;
    private Food food;
    private Board board;
    private Timer timer;

    public SnakeGame() {
        // Decorem la finestra
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Creem i configurem la finestra. Sortir al tancar
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creem el botó per iniciar el joc
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                startButton.setEnabled(false);
            }
        });

        // Afegim un menu
        JPanel menu = new JPanel();
        menu.add(startButton, BorderLayout.CENTER);

        // Afegim el botó al Frame
        frame.getContentPane().add(menu, "North");

        // Mostrar finestra
        frame.setSize(width,height);        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void startGame() {
        // Eliminar el menú cuando comienza el juego
        frame.getContentPane().removeAll();

        //Instanciem els elements del joc
        snake = new Snake(width/2, height/2);
        food = new Food(width, height);
        board = new Board(snake, food, score);

        //Add Board
        frame.getContentPane().add(board);

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

        frame.setFocusable(true);  // Permitim que el frame tingui el focus del Listener
        frame.requestFocusInWindow();  // Demanem que sigui el focus del Listener

        frame.revalidate();
    }

    public void update(int width, int height) {
        snake.move(width, height);
        checkCollisions(food);
        board.update(score);
    }

    private void checkCollisions(Food food) {
        if (snake.collidesWithFood(food)) {
            snake.grow();
            food.placeFood(width, height);
            score += 10;
        } else if (snake.collidesWithSelf()) {
            gameOver();
        }
    }

    private void gameOver() {
        timer.stop();
        
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Game Over! Score: " + score);
        gameOverPanel.add(label, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
            frame,
            gameOverPanel,
            "Game Over!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"Retry", "Exit"},
            "Retry");

            if (option == 0) {
                // Si se hace clic en "Retry", reiniciar el juego
                startGame();
            } else {
                // Si se hace clic en "Exit" o se cierra el cuadro de diálogo, salir del juego
                System.exit(0);
            }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
