import java.awt.*;
import javax.swing.*;

public class Xat {

    // Atributs de la finestra login
    private JFrame login;
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField loginTextField;
    private JButton loginButton;

    // Atributs de la finestra del Xat
    private JFrame xat;
    private JPanel outputPanel;
    private JPanel inputPanel;
    private JPanel usersPanel;
    private JButton sendButton;
    private JTextArea textArea;
    private JTextField messageField;
    private JList<String> userList;
    private DefaultListModel<String> usersListModel;

    private String username;
    private MySocket socket;

    public Xat(MySocket socket) {
        this.socket = socket;

        usersListModel = new DefaultListModel<>();
        userList = new JList<>(usersListModel);

        javax.swing.SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // Decorem la finestra
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Creació de la finestra login
        login = new JFrame("Login");
        login.setLayout(new BorderLayout(5, 5));
        login.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creació dels widgets del Login
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginLabel = new JLabel("Enter your username");
        loginTextField = new JTextField(25);
        loginButton = new JButton("LOGIN");
        loginButton.addActionListener(e -> login());

        // Afegim els widgets al loginPanel
        loginPanel.add(loginLabel);
        loginPanel.add(loginTextField);
        loginPanel.add(loginButton);

        // Afegim el JPanel al JFrame
        login.getContentPane().add(loginPanel, BorderLayout.PAGE_START);

        // Mostrar la finestra centrada
        login.setSize(450, 500);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public void login() {
        // Enviem el username al Server
        socket.println(loginTextField.getText());

        // Guardem el nick ÚNIC a una variable
        username = socket.readLine();

        // Métode per anar actualitzant tot el que arriba del servidor
        usersListModel.addElement(username);
        update();

        // Iniciem el xat
        login.setVisible(false);
        createXat();
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
        textArea.append("[+] Benvingut al xat " + username + "!\n");

        // Creem el inputPanel amb el JTextField del missatge a enviar amb el JButon
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        messageField = new JTextField(25);
        sendButton = new JButton("Enviar");
        sendButton.addActionListener(e -> buttonSendMessage());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Creem el usersPanel amb el JTextArea dels usuaris dins
        usersPanel = new JPanel();
        usersPanel.setLayout(new BorderLayout());
        usersPanel.setSize(5, 15);
        usersPanel.add(new JScrollPane(userList), BorderLayout.EAST);

        // Add panels to the main frame
        xat.getContentPane().setLayout(new BorderLayout());
        xat.getContentPane().add(outputPanel, BorderLayout.CENTER);
        xat.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        xat.getContentPane().add(usersPanel, BorderLayout.EAST);

        xat.setSize(450, 500);
        xat.setLocationRelativeTo(null);
        xat.setVisible(true);
    }

    public void buttonSendMessage() {
        String message = messageField.getText();
        socket.println(message); // Enviem el missatge al servidor
        textArea.append(username + ": " + message + "\n"); // Afegim textualment el missatge
        messageField.setText("");
    }

    public void update() {
        new Thread(() -> {
            String line;
            try {
                while ((line = socket.readLine()) != null) {
                    if (line.contains("onlineUsersList:")) {
                        // Actualizar la llista d'usuaris
                        updateUserList(line);
                    } else {
                        // Imprimim missatges provenents del servidor
                        textArea.append(line + " \n");
                    }
                }
            } catch (Exception ex) {
                socket.close();
                System.exit(0);
            }
        }).start();
    }

    public void updateUserList(String onlineUsersList) {
        String[] users = onlineUsersList.split(" ");
        usersListModel.clear();
        for (int i = 1; i < users.length; i++) {
            usersListModel.addElement(users[i]);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Xat(new MySocket(args[0], Integer.parseInt(args[1]))));
    }
}
