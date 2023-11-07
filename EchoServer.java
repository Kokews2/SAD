\\

public class EchoServer{

    public static void main (String[] args){

        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
        while(true){
            //Esperar seguent conexio' client
            MySocket s = ss.accept();
            // Crear un thread auxiliar que atengiui ael client
            new Thread(() -> {
                String line;
                while ((line = s.readLine()) != null){
                    s.println(line);
                }
                s.close();
            }).start();

        } 
    }
}