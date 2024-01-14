public class EchoServer {

    public static final ChatMonitor chatMonitor = new ChatMonitor();

    public static void main(String[] args){
                
        MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(args[0]));
        System.out.println("[+] Servidor escoltant pel port " + args[0] + "...\n");

        while(true){
            // Esperar la següent connexió de client
            MySocket socket = serverSocket.accept();

            // Thread auxiliar que ate al client
            new Thread(() -> {
                // Verificar si el nick esta repetit
                String nick = null;
                boolean nickValid = false;
                int i = 1;
                while (!nickValid) {
                    nick = socket.readLine();
                    if (chatMonitor.doesUserExist(nick)) {
                        // Si ja existeix el nom d'usuari li posem un identificador únic al final
                        nick = nick + i;
                        i++;
                    } else {
                        chatMonitor.addUser(nick, socket);
                        socket.println(nick);   // Enviem el username al client
                        sendOnlineUsers(socket);
                        System.out.println("[+] " + nick + " s'ha conectat\n");
                        nickValid = true;
                    }
                }

                String line;
                while((line = socket.readLine()) != null){
                    chatMonitor.sendMessage(nick, line);
                    sendOnlineUsers(socket);
                    System.out.println("[+] " + nick + ": " + line);
                }

                // Desconnexió del usuari
                System.out.println("[!] " + nick + " s'ha desconectat\n");
                chatMonitor.removeUser(nick);
                socket.close();
            }).start();
        }
    }

    public static void sendOnlineUsers(MySocket socket) {
        String usernames = "onlineUsersList: ";
        for (String userString : chatMonitor.getUsers().keySet()) {
            usernames = usernames + userString + " ";
        }
        socket.println(usernames);
    }
}
