public class EchoServer {

    private static final ChatMonitor chatMonitor = new ChatMonitor();

    public static void main(String[] args){
                
        MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(args[0]));
        System.out.println("[+] Servidor escoltant pel port " + args[0] + "...");

        while(true){
            // Esperar la següent connexió de client
            MySocket socket = serverSocket.accept();

            // Thread auxiliar que ate al client
            new Thread(() -> {
                // Demanem un nick al client
                socket.println("[+] Introdueixi un nick: ");

                // Verificar si el nick esta repetit
                String nick = null;
                boolean nickValid = false;
                while (!nickValid) {
                    nick = socket.readLine();
                    if (chatMonitor.doesUserExist(nick)) {
                        socket.println("[!] El Nick existeix en el xat, escull un altre");
                    } else {
                        chatMonitor.addUser(nick, socket);
                        socket.println("[+] Benvingut al xat " + nick + "!");
                        System.out.println("[+] " + nick + " s'ha conectat\n");
                        nickValid = true;
                    }
                }

                String line;
                while((line = socket.readLine()) != null){
                    chatMonitor.sendMessage(nick, line);
                    System.out.println("[+] " + nick + ": " + line);
                }

                // Desconnexió del usuari
                System.out.println("[!] " + nick + " s'ha desconectat\n");
                chatMonitor.removeUser(nick);
                socket.close();
            }).start();
        }
    }
}
