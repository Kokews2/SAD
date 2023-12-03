package Projecte.snakegame.src.main.java.snakegame;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame implements ActionListener {

    private JFrame frame;
    private Board board;
    private Timer timer;

    public SnakeGame() {
        // creem l'objecte JFrame com a finestra principal del Joc
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // creem el tauler del joc per on es mourá l'snake
        board = new Board();
        frame.getContentPane().add(board, BorderLayout.CENTER);; // afegim el tauler a la finestra del joc

        timer = new Timer(100, this); // actualitzar els events del joc cada 100ms (Aprofitar el ActionListener)
        timer.start();

        frame.pack(); // per redimensionar la finestra
        frame.setVisible(true);
    }

    // per gestionar els events del temporitzador (anar actualitzant el joc segons els canvis)
    @Override
    public void actionPerformed(ActionEvent e) {
        board.moveSnake(); // actualitzar la posició del snake
        board.repaint(); // dibuixar el tauler
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
