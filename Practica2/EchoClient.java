import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoClient {
    public static void main (String[] args){
        MySocket socket = new MySocket(args[0], Integer.parseInt(args[1]));

        // Thread de input (teclat)
        new Thread() {
            public void run() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                try{
                    while((line = reader.readLine()) != null || line.matches("exit")){
                        socket.println(line);
                    }
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();

        // Stream de output (consola)
        new Thread() {
            public void run() {
                String line;
                while((line = socket.readLine()) != null || line.matches("exit")){
                    System.out.println(line);
                }
                System.out.println("[!] Client Desconectat...");
                socket.close();
                System.exit(0);
            }
        }.start();
    }
}
