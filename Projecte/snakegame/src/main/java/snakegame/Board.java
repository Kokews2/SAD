package snakegame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    
    private Snake snake;
    private Snake snake2 = null;
    private Food food;

    public Board(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
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

        drawBoard(g);
        drawSnake1(g);
        if(snake2 != null) drawSnake2(g);
        drawFood(g);
        drawScore(g);
    }

    private void drawBoard(Graphics g) {
         // Dibujar el fondo del tablero (puedes personalizar esto)
         g.setColor(Color.LIGHT_GRAY);
         g.fillRect(0, 0, getWidth(), getHeight());
 
         // Dibujar el rectángulo exterior de negro
         g.setColor(Color.BLACK);
         g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    private void drawSnake1(Graphics g) {
        int i=0;
        for (Point point : snake.getBody()) {            
            if(i%2 == 0) g.setColor(Color.GREEN);
            if(i%2 == 1) g.setColor(Color.YELLOW);
            g.fillOval(point.x, point.y, snake.getWidth(), snake.getHeight());
            i++;
        }        
    }

    private void drawSnake2(Graphics g) {
        int i=0;
        for (Point point : snake2.getBody()) {            
            if(i%2 == 0) g.setColor(Color.BLUE);
            if(i%2 == 1) g.setColor(Color.MAGENTA);
            g.fillOval(point.x, point.y, snake2.getWidth(), snake2.getHeight());
            i++;
        }        
    }

    private void drawFood(Graphics g) {
        Point point = food.getFood();
        g.setColor(Color.RED);
        g.fillRect(point.x, point.y, food.getWidth(), food.getHeight());
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawString("Green Player: " + snake.getScore(), 10, 20);
        if(snake2 != null) g.drawString("Blue Player: " + snake2.getScore(), 10, 40);
    }
}
