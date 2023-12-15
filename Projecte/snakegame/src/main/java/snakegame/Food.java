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

        int x = rnd.nextInt(widthWnd);
        if((x % 5) > 0)
            x = x - (x % 5);
        if(x < 5)
            x = x + 10;

        int y = rnd.nextInt(heightWnd);
        if((y % 5) > 0)
            y = y - (y % 5);
        if(y < 5)
            y = y + 10;

        food = new Point(x,y);
    }
}
