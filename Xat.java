import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Xat {
    private static void createAndShowGUI() {
        // Set the look and feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}


        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);


        // Create and set up the window.
        JFrame frame = new JFrame("Xat");
        frame.setLayout(new BorderLayout(5, 5));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        // Create an output JPanel and add a JTextArea(20, 30) inside a JScrollPane
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
        JTextArea chatTextArea = new JTextArea(20, 30);
        chatTextArea.setEditable(false);
        outputPanel.add(new JScrollPane(chatTextArea));


        // Create an input JPanel and add a JTextField(25) and a JButton
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
        JTextField messageField = new JTextField(25);
        JButton sendButton = new JButton("Enviar");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);


        // Add panels to the main frame
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(outputPanel, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);


        // Display the window centered.
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Xat::createAndShowGUI);
    }
}
