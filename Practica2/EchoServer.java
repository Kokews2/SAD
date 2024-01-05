public class EchoServer {

    public static void main(String[] args){
        
        MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(args[0]));
        System.out.println("[+] Servidor escoltant pel port " + args[0] + "...");

        while(true){
            // Esperar la següent connexió de client
            MySocket socket = serverSocket.accept();

            // Thread auxiliar que ate al client
            new Thread(() -> {
                // Demanem un nick al client
                socket.println("[+] Conectat!");
                socket.println("[+] Introdueixi un nick: ");
                String nick = socket.readLine();
                socket.println("[+] Benvingut al xat " + nick + "!");
                System.out.println("[+] " + nick + " s'ha conectat\n");

                String line;
                while((line = socket.readLine()) != null){             
                    System.out.println("[+] " + nick + ": " + line);   
                    socket.println(line);
                }
                System.out.println("[!] " + nick + " s'ha desconectat\n");
                socket.close();
            }).start();
        }
    }

}
