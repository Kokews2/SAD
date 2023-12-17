package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame { 

    private int width = 640;     //Width of the window
    private int height = 480;    //Height of the window
    private int frequency = 100; //Frequency en ms

    private JFrame frame;
    private Controller controller;

    private Snake snake;
    private Food food;
    private Board board;
    private Timer timer;

    private Snake snake2;
    private boolean multiplayer = false;

    public SnakeGame() {
        // Decorem la finestra
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Creem i configurem la finestra. Sortir al tancar
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Afegim un menu
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));

        // Creem i afegim el títol del Joc
        JLabel titleLabel = new JLabel("SNAKE GAME");

        // Creem els botons del menu
        JButton singleplayerButton = new JButton("Single Player");
        JButton multiplayerButton = new JButton("Multiplayer");
        JButton exitButton = new JButton("Exit");

        // Afegim el títol y els botons al menu
        menu.add(titleLabel);
        menu.add(Box.createRigidArea(new Dimension(0, 20))); // Espai en blanc
        menu.add(singleplayerButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10))); // Espai en blanc
        menu.add(multiplayerButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10))); // Espai en blanc
        menu.add(exitButton);

        // Afegim el menu al Frame
        frame.getContentPane().add(menu, BorderLayout.CENTER);

        // Accions dels botons
        singleplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayer = false;
                startGame();
                singleplayerButton.setEnabled(false);
                multiplayerButton.setEnabled(false);
            }
        });

        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayer = true;
                startGame();
                singleplayerButton.setEnabled(false);
                multiplayerButton.setEnabled(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Mostrar finestra
        frame.setSize(width,height);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void startGame() {
        //Instanciem els elements del joc
        snake = new Snake(width/2, height/2);
        if (multiplayer) snake2 = new Snake(3*width / 4, height / 2);
        food = new Food(width, height);
        board = new Board(snake, food);
        if (multiplayer) board.setSnake2(snake2);

        // Inicialitzem el score
        snake.setScore(0);
        if (multiplayer) snake2.setScore(0);

        // Settegem la frequencia inicial
        frequency = 100;

        // Eliminar el menú quan comença el joc
        frame.getContentPane().removeAll();

        //Add Board
        frame.getContentPane().add(board, BorderLayout.CENTER);

        //Afegir els key events
        controller = new Controller(snake);
        frame.addKeyListener(controller);
        if (multiplayer) controller.setSnake2(snake2);          

        //Afegim entrada en ratoli
        Ratoli mousecontroller = new Ratoli(snake, board);
        board.addMouseListener(mousecontroller);

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
        // Mode multiplayer activat
        if (multiplayer) {
            snake2.move(board.getWidth(), board.getHeight());
            checkCollisions(food, snake2);
            checkCollisionsBetweenSnakes();
        }

        snake.move(board.getWidth(), board.getHeight());   
        checkCollisions(food, snake);    

        if (multiplayer) {
            if (((snake.getScore() + snake2.getScore()) > 0) && ((snake.getScore() + snake2.getScore()) % 50 == 0)) adjustTimerSpeed();
        } else {
            if ((snake.getScore() > 0) && ((snake.getScore() % 50) == 0)) adjustTimerSpeed();
        }
        
        board.repaint();
    }

    private void checkCollisions(Food food, Snake snake) {
        if (snake.collidesWithFood(food)) {
            snake.grow();
            food.placeFood(board.getWidth(), board.getHeight());
            snake.setScore(snake.getScore() + 10);
        } else if (snake.collidesWithSelf()) {
            gameOver();
        }
    }

    private void checkCollisionsBetweenSnakes() {
        if (snake.collidesWithSnake(snake2) || snake2.collidesWithSnake(snake))
            gameOver();
    }

    private void adjustTimerSpeed() {
        int newFrequency = frequency - 10;

        if (newFrequency <= 20) {
            newFrequency = 20; 
        }

        timer.setDelay(newFrequency);
        timer.restart();
    }

    private void gameOver() {
        timer.stop();
        
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        if(multiplayer) {
            label.setText("Game Over! Player 1: " + snake.getScore() + " - Player 2: " + snake2.getScore());
        } else {
            label.setText("Game Over! Score: " + snake.getScore());
        }

        gameOverPanel.add(label, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
            frame,
            gameOverPanel,
            "Game Over!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"Single Player","Multiplayer", "Exit"},
            "Retry");

            if (option == 0) {
                // Si se hace clic en "Single Player", reiniciar el juego
                multiplayer = false;
                startGame();
            } else if (option == 1) {
                // Si se hace clic en "Multiplayer", reiniciar el juego
                multiplayer = true;
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
