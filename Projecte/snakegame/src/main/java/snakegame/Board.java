package Projecte.snakegame.src.main.java.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {

    private Snake snake;

    public Board() {
        super(new BorderLayout());

        snake = new Snake();
        addKeyListener(this); // permet que pugui adonarse dels nous events (tecles polsades)
    }

    public void moveSnake() {
        snake.move();
        checkCollisions();
    }

    private void checkCollisions() {
        if (snake.collidesWithFood()) {
            snake.grow();
            placeFood();
        } else if (snake.collidesWithSelf() || snake.collidesWithBorder(getWidth(), getHeight())) {
            gameOver();
        }
    }

    private void placeFood() {
        // Colocar el menjar
    }

    private void gameOver() {
        System.out.println("Game Over");
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        // falta dibuixar el menjar
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // falta gestionar les tecles polsades
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no l'utilizem pero es necesari per KeyListener
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // no l'utilizem pero es necesari per KeyListener
    }
}
