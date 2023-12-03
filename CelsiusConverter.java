/**
 * CelsiusConverter.java is a 1.4 application that 
 * demonstrates the use of JButton, JTextField and
 * JLabel.  It requires no other files.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CelsiusConverter implements ActionListener {
    JFrame converterFrame;
    JPanel converterPanel;
    JTextField tempCelsius;
    JLabel celsiusLabel, fahrenheitLabel;
    JButton convertTemp;


    public CelsiusConverter() {
        // Create and set up the window.
        converterFrame = new JFrame("Celsius Converter");
        converterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Create and set up the panel.
        converterPanel = new JPanel();


        // Add the widgets.
        addWidgets();


        // Add the panel to the window.
        converterFrame.getContentPane().add(converterPanel, BorderLayout.CENTER);


        // Display the window.
        converterFrame.pack();
        converterFrame.setVisible(true);
    }


    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
        // Create widgets.
        tempCelsius = new JTextField(5);
        celsiusLabel = new JLabel("Celsius");
        convertTemp = new JButton("Convert");
        fahrenheitLabel = new JLabel("Fahrenheit");


        // Listen to events from the Convert button.
        convertTemp.addActionListener(this);


        // Add the widgets to the container.
        converterPanel.add(tempCelsius);
        converterPanel.add(celsiusLabel);
        converterPanel.add(convertTemp);
        converterPanel.add(fahrenheitLabel);
    }


    public void actionPerformed(ActionEvent event) {
        // Parse degrees Celsius as a double and convert to Fahrenheit.
        try {
            double celsius = Double.parseDouble(tempCelsius.getText());
            double fahrenheit = celsius * 9/5 + 32;
            fahrenheitLabel.setText(fahrenheit + " Fahrenheit");
        } catch (NumberFormatException e) {
            fahrenheitLabel.setText("Enter a valid number");
        }
    }


    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Set the look and feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}


        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);


        CelsiusConverter converter = new CelsiusConverter();
    }


    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}



