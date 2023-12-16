package snakegame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    
    private Snake snake;
    private Food food;

    public Board(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSnake(g);
        drawFood(g);
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

}
