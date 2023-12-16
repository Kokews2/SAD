package snakegame;

import java.awt.*;
import java.util.Random;

public class Food {

    private Point food;
    private int width = 10;
    private int heigth = 10;

    public Food(int widthWnd, int heightWnd) {
        placeFood(widthWnd, heightWnd);
    }

    public Point getFood() {
        return food;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigth;
    }

    public void placeFood(int widthWnd, int heightWnd) {
        Random rnd = new Random();

        int x = rnd.nextInt(widthWnd / 10) * 10; // Assegura que x es múltiple de 10
        int y = rnd.nextInt(heightWnd / 10) * 10; // Assegura que y es múltiple de 10

        food = new Point(x, y);
    }
}
