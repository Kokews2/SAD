package snakegame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    
    private Snake snake;
    private Food food;
    private int score;

    public Board(Snake snake, Food food, int score) {
        this.snake = snake;
        this.food = food;
        this.score = score;
    }

    public void update(int score) {
        this.score = score;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSnake(g);
        drawFood(g);
        drawScore(g);
    }

    private void drawSnake(Graphics g) {
        int i=0;
        for (Point point : snake.getBody()) {            
            if(i%2 == 0) g.setColor(Color.GREEN);
            if(i%2 == 1)g.setColor(Color.YELLOW);
            g.fillOval(point.x, point.y, snake.getWidth(), snake.getHeight());
            i++;
        }        
    }

    private void drawFood(Graphics g) {
        Point food = this.food.getFood();
        g.setColor(Color.RED);
        g.fillRect(food.x, food.y, this.food.getWidth(), this.food.getHeight());
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawString("Score: " + score, 10, 20);
    }

}
