package snakegame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Snake snake;
    private Snake snake2 = null;
    private Food food;
    private Walls walls;

    public Board(Snake snake, Food food, Walls walls) {
        this.snake = snake;
        this.food = food;
        this.walls = walls;
        setBackground(new Color(34, 34, 34));
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSnake(g, snake, Color.GREEN, Color.YELLOW);
        if (snake2 != null)
            drawSnake(g, snake2, Color.CYAN, Color.MAGENTA);
        drawFood(g);
        drawScore(g);
        if (!walls.getArrayDePuntos().isEmpty())
            drawWalls(g);
    }

    private void drawSnake(Graphics g, Snake snake, Color color1, Color color2) {
        int i = 0;
        for (Point point : snake.getBody()) {
            if (i % 2 == 0)
                g.setColor(color1);
            if (i % 2 == 1)
                g.setColor(color2);
            g.fillOval(point.x, point.y, snake.getWidth(), snake.getHeight());
            i++;
        }
    }

    private void drawFood(Graphics g) {
        Point point = food.getFood();
        g.setColor(Color.RED);
        g.fillRect(point.x, point.y, food.getWidth(), food.getHeight());
    }

    public void drawScore(Graphics g) {
        Font font = new Font("Arial", Font.ITALIC, 14);
        g.setFont(font);

        g.setColor(Color.GREEN);
        g.drawString("Player 1 (Green): " + snake.getScore(), 10, 20);

        if (snake2 != null) {
            g.setColor(Color.CYAN);
            Font fontPlayer2 = new Font("Arial", Font.ITALIC, 14);
            g.setFont(fontPlayer2);
            g.drawString("Player 2 (Blue): " + snake2.getScore(), 10, 40);
        }
    }

    public void drawWalls(Graphics g) {
        g.setColor(Color.ORANGE);

        for (Point wall : walls.getArrayDePuntos()) {
            g.fillRect(wall.x, wall.y, walls.getWidth(), walls.getHeight());
        }
    }
}
