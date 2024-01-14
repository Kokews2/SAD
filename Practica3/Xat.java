import java.awt.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class Xat {

    // Atributs de la finestra login
    private JFrame login;
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField loginTextField;
    private JButton loginButton;

    private JFrame xat;
    private JPanel outputPanel;
    private JPanel inputPanel;
    private JPanel usersPanel;
    private JButton sendButton;
    private JTextArea textArea;
    private JTextArea usersArea;
    private JTextField messageField;
    private DefaultListModel<String> usersList;

    private String username;
    private MySocket socket;
    private ChatMonitor chatMonitor;

    public Xat(ChatMonitor chatMonitor, MySocket socket) {
        super();

        this.socket = socket;
        this.chatMonitor = chatMonitor;
        this.chatMonitor.setXat(this);

        javax.swing.SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // Decorem la finestra
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Creació de la finestra login
        login = new JFrame("Log-in");
        login.setLayout(new BorderLayout(5, 5));
        login.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creació dels widgets del Login
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginLabel = new JLabel("ENTER USERNAME");
        makeStylishLabel(loginLabel);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto
        loginTextField = new JTextField(2);
        loginButton = createStyledButton("LOGIN");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto
        loginButton.addActionListener(e -> login());

        // Afegim els widgets al loginPanel
        loginPanel.add(loginLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaiat vertical
        loginPanel.add(loginTextField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaiat vertical
        loginPanel.add(loginButton);

        // Afegim el JPanel al JFrame
        login.getContentPane().add(loginPanel, BorderLayout.PAGE_START);

        // Mostrar la finestra centrada
        login.setSize(450, 500);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public void login() {
        username = loginTextField.getText();

        // LOGICA A COMPLETAR...
    }

    public void createXat() {
        xat = new JFrame("Xat");
        xat.setLayout(new BorderLayout(5, 5));
        xat.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        xat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creem el outputPanel amb el JTextArea dels missatges dins
        outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        outputPanel.add(new JScrollPane(textArea));

        // Creem el inputPanel amb el JTextField del missatge a enviar amb el JButon
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        messageField = new JTextField(25);
        sendButton = new JButton("Enviar");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Creem el usersPanel amb el JTextArea dels usuaris dins
        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.PAGE_AXIS));
        usersArea = new JTextArea(5, 15);
        usersArea.setEditable(false);
        usersPanel.add(new JScrollPane(usersArea));

        // Add panels to the main frame
        xat.getContentPane().setLayout(new BorderLayout());
        xat.getContentPane().add(outputPanel, BorderLayout.CENTER);
        xat.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        xat.getContentPane().add(usersPanel, BorderLayout.EAST);

        // Lógica del botó "Enviar"
        sendButton.addActionListener(e -> buttonSendMessage());

        xat.setSize(450, 500);
        xat.setLocationRelativeTo(null);
        xat.setVisible(true);
    }

    public void buttonSendMessage() {
        String message = messageField.getText();
        chatMonitor.sendMessage("Jo", message);
        messageField.setText("");
    }

    // Funció per agregar un missatge al area del chat
    public void appendMessage(String sender, String message) {
        textArea.append(sender + ": " + message + "\n");
    }

    public static void main(String[] args) {

        // javax.swing.SwingUtilities.invokeLater(() -> new Xat(new ChatMonitor() ,new
        // MySocket(args[0], Integer.parseInt(args[1]))));

        ChatMonitor chatMonitor = new ChatMonitor();
        MySocket sckt = new MySocket("127.0.0.1", 8080);
        Xat xat = new Xat(chatMonitor, sckt);

        xat.createAndShowGUI();
        /*
         * javax.swing.SwingUtilities.invokeLater(new Runnable() {
         * public void run() {
         * 
         * }
         * });
         */
    }








    private void makeStylishLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setPreferredSize(new Dimension(250, 50));
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY); // Color de fons gris
        button.setForeground(Color.BLUE); // Color de lletres
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false); // Eliminar el rectangle per ser el focus
        button.setBorderPainted(false); // Eliminar la bora pintada

        return button;
    }



}
