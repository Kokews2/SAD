package snakegame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Snake snake;
    private Point food;

    public Board() {
        super(new BorderLayout());

        snake = new Snake(5, 5);

        placeFood(); // posem el menjar aleatoriament al tauler
    }

    public void moveSnake() {
        checkCollisions();
    }

    private void checkCollisions() {
        if (snake.collidesWithFood(food)) {
            snake.grow();
            placeFood();
        } else if (snake.collidesWithSelf() || snake.collidesWithBorder(getWidth(), getHeight())) {
            gameOver();
        }
    }

    private void placeFood() {
        int x = (int) (Math.random() * this.getWidth());
        int y = (int) (Math.random() * this.getHeight());

        food = new Point(x,y);
    }

    private void gameOver() {
        System.out.println("Game Over");
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFood(g);
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.x * 20, food.y * 20, 20, 20);
    }
}
