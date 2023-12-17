package snakegame;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    //Snake direction constants
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private LinkedList<Point> body;
    private int direction = RIGHT;
    private int score = 0;

    private int width = 10;
    private int height = 10;

    public Snake(int x, int y) {
        body = new LinkedList<>();
        body.add(new Point(x, y));
    }

    public void setDirection(int direction) {
        if (direction >= 0 && direction <= 3) {
            this.direction = direction;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public int getScore() {
        return this.score;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void move(int widthWnd, int heightWnd) {
        Point head = body.getFirst();
        Point newHead = new Point(head);
        switch (direction) {
            case UP:
                newHead.y = newHead.y - this.height;
                if (newHead.y < 0)
                    newHead.y = heightWnd - this.height;
                break;
            case RIGHT:
                newHead.x = newHead.x + this.width;
                if (newHead.x > widthWnd)
                    newHead.x = 0;
                break;
            case DOWN:
                newHead.y = newHead.y + this.height;
                if (newHead.y > heightWnd)
                    newHead.y = 0;
                break;
            case LEFT:
                newHead.x = newHead.x - this.width;
                if (newHead.x < 0)
                    newHead.x = widthWnd - this.width;
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

    public boolean collidesWithFood(Food food) {
        Rectangle headRect = new Rectangle(body.getFirst().x, body.getFirst().y, width, height);
        Rectangle foodRect = new Rectangle(food.getFood().x, food.getFood().y, food.getWidth(), food.getHeight());
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

    public boolean collidesWithSnake(Snake otherSnake) {
        Point head = body.getFirst();
        Rectangle headRect = new Rectangle(head.x, head.y, width, height);

        for (Point point : otherSnake.getBody()) {
            Rectangle otherHeadRect = new Rectangle(point.x, point.y, otherSnake.getWidth(), otherSnake.getHeight());

            if (headRect.intersects(otherHeadRect)) {
                return true;
            }
        }
        return false;
    }
}
