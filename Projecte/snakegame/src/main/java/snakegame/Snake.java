package Projecte.snakegame.src.main.java.snakegame;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body; // cos de la serp on 'Point' indica la posició del tauler
    private int direction; // cap a on mira la serp

    // constants per les direccions de la serp
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(5, 5));
        direction = RIGHT;
    }

    /* 
     * Per moure la serp, crearem un nou 'cap' en el 'Point' cap a on apunta la direcció de la serp
     * després eliminem la 'cua' de la serp
     */
    public void move() {
        Point head = body.getFirst();
        Point newHead = new Point(head);
        switch (direction) {
            case UP:
                newHead.y--;
                break;
            case RIGHT:
                newHead.x++;
                break;
            case DOWN:
                newHead.y++;
                break;
            case LEFT:
                newHead.x--;
                break;
        }
        body.addFirst(newHead);
        body.removeLast();
    }

    /*
     * La serp creix duplicant l'ultima part del seu cos al final
     */
    public void grow() {
        Point tail = body.getLast();
        Point newtail = new Point(tail);
        body.addLast(newtail);
    }

    public boolean collidesWithFood() {
        // quan colisiona amb la fruita
        return false;
    }

    public boolean collidesWithSelf() {
        // quan colisiona amb si mateixa
        return false;
    }

    public boolean collidesWithBorder(int boardWidth, int boardHeight) {
        // quan colisiona amb els bordes del tauler
        return false;
    }


    /*
     * Dibuixa la serp amb ajuda de la clase 'Graphics' en rectangles sólids de 20x20
     */
    public void draw(Graphics g) {
        for (Point point : body) {
            g.fillRect(point.x * 20, point.y * 20, 20, 20); // Dibuja la serpiente con cuadrados de 20x20 píxeles
        }
    }
}
