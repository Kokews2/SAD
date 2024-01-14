import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoClient {

    public static void main (String[] args){

        MySocket socket = new MySocket(args[0], Integer.parseInt(args[1]));
        System.out.println("[+] Conectat al servidor com " + args[0] + ":" + args[1]);

        // Input Thread
        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    socket.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[!] Tancant conexió...");
            socket.close();
            System.exit(0);
        }).start();

        // Output Thread
        new Thread(() -> {
            String line;
            while ((line = socket.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("[!] Client desconectat...");
            socket.close();
            System.exit(0);
        }).start();
    }
}
