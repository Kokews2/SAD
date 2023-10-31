/**
    EchoClient
 **/
// L'hem executat amb java -cp bin XatTextCon localhost 50000 fran
public class EchoClient{

    public static void main (String[] args){
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));
        // Inpu threas (keyboard)
        new Thread(){
            public void run(){
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                String line;
                try{
                    while((line = kbd.readLine()) != null){
                        sc.println(line);
                    }
                } catch (IOException ex){
                ex.printStackTrace();
                }
            }
        }.start();

        /*new Thread() {
            public void run(){
                
                String line;
                while((line = sc.readLine()) != null){
                    System.out.println(line);
                }
            }
        }.start();*/

        new Thread() -> {
                            
                String line;
                while((line = sc.readLine()) != null){
                    System.out.println(line);
                }
        }.start(); 


    }

}