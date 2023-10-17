import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(InputStreamReader  in) {
        super(in);
    }

    /*
     * Cambia el mode del terminal a Raw 
     * (Utilitzem la API de ProcessBuilder per executar comanes desde el terminal)
     */
    public void setRaw() {
        try {
            // Creem un procés que executi la comana 'stty -echo raw'
            ProcessBuilder processBuilder = new ProcessBuilder("stty", "-echo", "raw");
            Process process = processBuilder.start();

            // Esperamos a que el proceso termine
            int exitCode = process.waitFor();

            // Verifiquem que s'hagi executat bé la comana
            if (exitCode == 0) {
                System.out.println("Teclat en mode Raw");
            } else {
                System.out.println("Error al posar el teclat en mode Raw");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Cambia el mode del terminal a Cooked 
     * (Utilitzem la API de ProcessBuilder per executar comanes desde el terminal)
     */
    void unsetRaw() {
        try {
            // Creem un procés que executi la comana 'stty -echo raw'
            ProcessBuilder processBuilder = new ProcessBuilder("stty", "-echo", "cooked");
            Process process = processBuilder.start();

            // Esperamos a que el proceso termine
            int exitCode = process.waitFor();

            // Verifiquem que s'hagi executat bé la comana
            if (exitCode == 0) {
                System.out.println("Teclat en mode Raw");
            } else {
                System.out.println("Error al posar el teclat en mode Raw");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int read() throws IOException {
        
        int lect;
        try {
            
            if ((lect = super.read()) != KeyCar.ESC ) {
                return lect;
            }
            
            if (lect == KeyCar.CRTL_C) {
                System.err.print("Has salido");   
                return KeyCar.EXIT_KEY;
            }
            if (lect == KeyCar.CLAVE) {
                lect = super.read();
                return lect - 1000;
            }
        } catch (IOException ex) {
            System.out.println("Interrupted Exception");
        }
        return KeyCar.CARAC;


    }

    public String readLine() throws IOException {
        try {
            setRaw();
            Line line = new Line();
            int key;
            while ((key = this.read()) != '\r'){        //llegeix fins retorn de carro
                switch(key) {
                    case KeyCar.RIGHT_ARROW: line.right();
                    break;
                    case KeyCar.LEFT_ARROW: line.left();
                    break;
                    case KeyCar.HOME: line.home();
                    break;
                    case KeyCar.END: line.end();
                    break;
                    case KeyCar.INS: line.insert();
                    break;
                    case KeyCar.DEL: line.delete();
                    break;
                    case KeyCar.BS: line.backSpace();
                    break;
                    default: line.addChar((char) key);
                }
            }
            return line.toString();
        } catch (IOException e) {
            // TODO: handle exception
            throw e;
        } finally{
            unsetRaw();
        }
    }
}