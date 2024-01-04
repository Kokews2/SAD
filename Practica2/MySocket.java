import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public MySocket(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public MySocket(String host, int port) throws IOException, UnknownHostException {
        this.socket = new Socket(host, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    public void close() throws IOException {
        socket.close();
    }

    public int read() throws IOException {
        return reader.read();
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void print(String line) {
        writer.print(line);
    }

    public void println(String line) {
        writer.println(line);
    }
}
