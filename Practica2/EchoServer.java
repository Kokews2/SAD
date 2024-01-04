public class EchoServer {

    public static void main(String[] args){
        
        MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(args[0]));

        while(true){
            // Esperar la següent connexió de client
            MySocket socket = serverSocket.accept();
            socket.println("Connected");

            // Thread auxiliar que até al client
            new Thread(){
                public void run(){
                    String line;
                    while((line = socket.readLine()) != null || line.matches("exit")){                
                        socket.println(line);
                    }
                    socket.close();
                }
            }.start();
        }
    }

}
