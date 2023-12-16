package snakegame;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MouseController extends JFrame implements ActionListener{
    
    private JButton botonUP,botonRight,botonLeft,botonDOWN;
    private Snake snake;

    public MouseController(Snake snake) {
        this.snake = snake;
    }

    public void BotonesClick() {

        //Layout absoluto
        setLayout(null);
    
        //Tamaño de la ventana
        setBounds(0,0,640,480);
    
    
        //No redimensionable
        setResizable(false);
    
        //Cerrar proceso al cerrar la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        //Botones
        botonUP=new JButton();
        boton1.setBounds(10,100,90,30);
        add(boton1);
        boton1.addActionListener(this);
        
        boton2=new JButton("2");
        boton2.setBounds(110,100,90,30);
        add(boton2);
        boton2.addActionListener(this);
        boton3=new JButton("3");
        boton3.setBounds(210,100,90,30);
        add(boton3);
        boton3.addActionListener(this);
    
        //Muestro JFrame (lo último para que lo pinte todo correctamanete)
        //setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==boton1) {
              setTitle("boton 1");
            }
            if (e.getSource()==boton2) {
              setTitle("boton 2");
            }
            if (e.getSource()==boton3) {
              setTitle("boton 3");
            }
          }

}

