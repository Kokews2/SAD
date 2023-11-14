import java.awt.*;
import javax.swing.*;        

public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    private static void createAndShowGUI() {

        //Create and set up the window. Exit on close
        Jframe frame = new Jframe("Hello World Swing");
        frame.setDefaultCloseOperation(Jframe.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label to frame.
        JLabel label = new JLabel("Hello World Pau");
        label.setHorizontaalAlignment(JLabel.CENTER);
        frame.getContentPane().add(label);

        //Display the window. set size and center
        frame.pack();
        frame.setSize(100,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}