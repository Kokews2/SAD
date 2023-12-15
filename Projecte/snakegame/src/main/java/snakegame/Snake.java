package snakegame;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Snake extends JPanel {

    // constants per les direccions de la serp
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private LinkedList<Point> body; // cos de la serp on 'Point' indica la posició del tauler
    private int direction; // cap a on mira la serp
    private int width = 10;
    private int heigth = 10;

    public Snake(int x, int y) {
        body = new LinkedList<>();
        body.add(new Point(x, y));
        direction = RIGHT;
    }

    public void setDirection(int direction) {
        if (direction >= 0 && direction <= 3) {
            this.direction = direction;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void update(int widthWnd, int heightWnd) {
        move(widthWnd, heightWnd);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (Point point : body) {
            g.fillRect(point.x, point.y, width, heigth);
        }
    }

    public void move(int widthWnd, int heightWnd) {
        Point head = body.getFirst();
        Point newHead = new Point(head);
        switch (direction) {
            case UP:
                newHead.y = newHead.y - this.heigth;
                if(newHead.y < 0)
                    newHead.y = heightWnd - this.heigth;
                if(newHead.y > heightWnd)
                    newHead.y = 0;
                break;
            case RIGHT:
                newHead.x = newHead.x + this.width;
                if(newHead.x < 0)
                    newHead.x = widthWnd - this.width;
                if(newHead.x > widthWnd)
                    newHead.x = 0;
                break;
            case DOWN:
                newHead.y = newHead.y + this.heigth;
                if(newHead.y < 0)
                    newHead.y = heightWnd - this.heigth;
                if(newHead.y > heightWnd)
                    newHead.y = 0;
                break;
            case LEFT:
                newHead.x = newHead.x - this.width;
                if(newHead.x < 0)
                    newHead.x = widthWnd - this.width;
                if(newHead.x > widthWnd)
                    newHead.x = 0;
                break;
        }
        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        Point tail = body.getLast();
        Point newtail = new Point(tail);
        body.addLast(newtail);
    }

    public boolean collidesWithFood(Point food) {
        Rectangle headRect = new Rectangle(body.getFirst().x, body.getFirst().y, 20, 20);
        Rectangle foodRect = new Rectangle(food.x, food.y, 20, 20);
        return headRect.intersects(foodRect);
    }

    public boolean collidesWithSelf() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithBorder(int boardWidth, int boardHeight) {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight;
    }
}
