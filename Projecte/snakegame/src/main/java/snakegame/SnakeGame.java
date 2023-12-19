package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class SnakeGame {
    public static final int MAX_WALLS = 100;
    public static final int MAX_FREQUENCY = 40;

    private int width = 640; // Width of the window
    private int height = 480; // Height of the window
    private int frequency = 100; // Frequency en ms

    private JFrame frame;
    private Controller controller;

    private Snake snake;
    private Food food;
    private Board board;
    private Timer timer;
    private Walls walls;

    private Snake snake2;
    private boolean multiplayer = false;
    private boolean setWalls = true;

    public SnakeGame() {
        // Decorem la finestra
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Creem i configurem la finestra. Sortir al tancar
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);

        // Afegim un menu
        createMenu();

        // Mostrar finestra
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.setBackground(new Color(34, 34, 34)); // Color de fons gris fosc

        // Crear y añadir el título del juego
        ImageIcon snakeIcon = createImageIcon("\\Imatges\\snakefoto.png");  // Carregem l'imatge
        ImageIcon resizedSnakeIcon = new ImageIcon(snakeIcon.getImage().getScaledInstance(640, 230, Image.SCALE_SMOOTH));
        JLabel titleLabel = new JLabel(resizedSnakeIcon);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(titleLabel);

        // Añadir espacio en blanco
        menu.add(Box.createRigidArea(new Dimension(0, 20)));

        // Crear los botones del menú
        JButton singleplayerButton = createStyledButton("Single Player");
        JButton multiplayerButton = createStyledButton("Multiplayer");
        JButton exitButton = createStyledButton("Exit");

        // Añadir ActionListener a los botones
        singleplayerButton.addActionListener(e -> startSinglePlayer());
        multiplayerButton.addActionListener(e -> startMultiplayer());
        exitButton.addActionListener(e -> System.exit(0));

        // Alinear los botones al centro
        singleplayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        multiplayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir los botones al menú
        menu.add(singleplayerButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado
        menu.add(multiplayerButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado
        menu.add(exitButton);

        // Añadir el menú al frame
        frame.getContentPane().add(menu, BorderLayout.CENTER);

    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(68, 68, 68)); // Color de fondo más oscuro
        button.setForeground(Color.GREEN);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false); // Eliminar el resaltado al obtener el foco
        button.setBorderPainted(false); // Eliminar el borde pintado

        // Cambiar el color del botón al pasar el ratón por encima
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(88, 88, 88));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(68, 68, 68));
            }
        });

        return button;
    }

    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se pudo encontrar el archivo de imagen: " + path);
            return null;
        }
    }

    public void startSinglePlayer() {
        multiplayer = false;
        startGame();
    }

    public void startMultiplayer() {
        multiplayer = true;
        startGame();
    }

    public void startGame() {
        // Instanciem els elements del joc
        snake = new Snake(width / 2, height / 2);
        if (multiplayer)
            snake2 = new Snake(3 * width / 4, height / 2);
        walls = new Walls();
        food = new Food(width, height);
        board = new Board(snake, food, walls);
        if (multiplayer)
            board.setSnake2(snake2);

        // Inicialitzem el score
        snake.setScore(0);
        if (multiplayer)
            snake2.setScore(0);

        // Settegem la frequencia inicial
        frequency = 100;

        // Eliminar el menú quan comença el joc
        frame.getContentPane().removeAll();

        // Add Board
        frame.getContentPane().add(board, BorderLayout.CENTER);

        // Afegir els key events
        controller = new Controller(snake);
        frame.addKeyListener(controller);
        if (multiplayer)
            controller.setSnake2(snake2);

        // Afegim entrada en ratoli
        MouseHandler mousecontroller = new MouseHandler(snake, board);
        board.addMouseListener(mousecontroller);

        // Posar el joc amb Timer de Swing
        timer = new Timer(frequency, e -> update());
        timer.start();

        frame.setFocusable(true); // Permitim que el frame tingui el focus del Listener
        frame.requestFocusInWindow(); // Demanem que sigui el focus del Listener
        frame.revalidate();
    }

    public void update() {
        if (walls.getArrayDePuntos().size() < MAX_WALLS) {
            int totalScore = multiplayer ? (snake.getScore() + snake2.getScore()) : snake.getScore();
            if ((totalScore > 0) && (totalScore % 50 == 0) && setWalls) {
                walls.placeWalls(board.getWidth(), board.getHeight());
                setWalls = false;
            }
            if (totalScore % 50 != 0)
                setWalls = true;
        }

        // Mode multiplayer activat
        if (multiplayer) {
            snake2.move(board.getWidth(), board.getHeight());
            checkCollisions(food, snake2, walls);
            checkCollisionsBetweenSnakes();
        }

        snake.move(board.getWidth(), board.getHeight());
        checkCollisions(food, snake, walls);

        adjustTimerSpeed();

        board.repaint();
    }

    private void checkCollisions(Food food, Snake snake, Walls walls) {
        if (snake.collidesWithFood(food)) {
            snake.grow();
            food.placeFood(board.getWidth(), board.getHeight(), walls);
            snake.setScore(snake.getScore() + 10);
        } else if (snake.collidesWithSelf() || snake.collidesWithWall(walls)) {
            gameOver();
        }
    }

    private void checkCollisionsBetweenSnakes() {
        if (snake.collidesWithSnake(snake2) || snake2.collidesWithSnake(snake))
            gameOver();
    }

    private void adjustTimerSpeed() {
        int totalScore = multiplayer ? (snake.getScore() + snake2.getScore()) : snake.getScore();
        if (totalScore > 0 && totalScore % 50 == 0) {
            int newFrequency = frequency - 10;
            if (newFrequency < MAX_FREQUENCY)
                newFrequency = MAX_FREQUENCY;
            timer.setDelay(newFrequency);
            timer.restart();
        }
    }

    private void gameOver() {
        timer.stop();

        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        if (multiplayer && (snake.getScore() > snake2.getScore())) {
            label.setText("Player 1 wins! Player 1: " + snake.getScore() + " - Player 2: " + snake2.getScore());
        } else if (multiplayer && (snake.getScore() < snake2.getScore())) {
            label.setText("Player 2 wins! Player 1: " + snake.getScore() + " - Player 2: " + snake2.getScore());
        } else if (multiplayer && (snake.getScore() == snake2.getScore())) {
            label.setText("Its a draw! Player 1: " + snake.getScore() + " - Player 2: " + snake2.getScore());
        } else {
            label.setText("Game Over! Score: " + snake.getScore());
        }

        gameOverPanel.add(label, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
                frame,
                gameOverPanel,
                "Game Over!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Single Player", "Multiplayer", "Exit" },
                "Retry");

        if (option == 0) {
            startSinglePlayer();
        } else if (option == 1) {
            startMultiplayer();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
