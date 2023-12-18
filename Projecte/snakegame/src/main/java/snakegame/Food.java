package snakegame;

import java.awt.*;
import java.util.Random;

public class Food {

    private Point food;
    private int width = 10;
    private int height = 10;

    private Random rnd;

    public Food(int widthWnd, int heightWnd) {
        this.rnd = new Random();
        this.food = new Point(widthWnd/4, heightWnd/4);
    }

    public Point getFood() {
        return food;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void placeFood(int widthWnd, int heightWnd, Walls walls) {
        int x = rnd.nextInt(widthWnd / width) * width; // Assegura que x es múltiple de width
        int y = rnd.nextInt(heightWnd / height) * height; // Assegura que y es múltiple de heigth

        while (!walls.getArrayDePuntos().isEmpty() && collidesWithWall(walls)) {
            x = rnd.nextInt(widthWnd / width) * width;
            y = rnd.nextInt(heightWnd / height) * height;
        }

        food = new Point(x, y);
    }

    public boolean collidesWithWall(Walls walls) {
        Rectangle foodRect = new Rectangle(food.x, food.y, width, height);

        for (Point punt : walls.getArrayDePuntos()) {
            Rectangle wallRect = new Rectangle(punt.x, punt.y, walls.getWidth(), walls.getHeight());
            
            if (foodRect.intersects(wallRect)) return true;
        }
        return false;
    }
}
