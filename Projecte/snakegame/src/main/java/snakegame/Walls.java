package snakegame;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Walls {

    public static final int MAX_WALLS = 10;

    private LinkedList<Point> arrayDePuntos;
    private int width = 10;
    private int height = 10;

    public Walls() {
        this.arrayDePuntos = new LinkedList<>();
    }

    public LinkedList<Point> getArrayDePuntos() {
        return arrayDePuntos;
    }

    public void setArrayDePuntos(LinkedList<Point> arrayDePuntos) {
        this.arrayDePuntos = arrayDePuntos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void placeWalls(int widthWnd, int heightWnd) {
        Random rnd = new Random();
        int x = rnd.nextInt(widthWnd / width) * width;
        int y = rnd.nextInt(heightWnd / height) * height;

        for (int i = 0; i < MAX_WALLS; i++) {
            x = rnd.nextInt(widthWnd / width) * width;
            y = rnd.nextInt(heightWnd / height) * height;
            
            arrayDePuntos.addLast(new Point(x,y)); 
        }
    }
}