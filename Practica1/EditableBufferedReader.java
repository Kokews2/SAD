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
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "stty -echo raw</dev/tty");
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
            // Creem un procés que executi la comana
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "stty cooked echo</dev/tty");
            Process process = processBuilder.start();

            // Esperamos a que el proceso termine
            int exitCode = process.waitFor();

            // Verifiquem que s'hagi executat bé la comana
            if (exitCode == 0) {
                System.out.println("Teclat en mode Cooked");
            } else {
                System.out.println("Error al posar el teclat en mode Cooked");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* SECUENCIAS ESCAPE
     * ***********************************
     * Right: ESC [ C
     * Left: ESC [ D
     * Home: ESC O H, ESC [ 1 ~ (keypad)
     * End: ESC O F, ESC [ 4 ~ (keypad)
     * Insert: ESC [ 2 ~
     * Delete: ESC [ 3 ~
     */

    public int read() throws IOException {
        int lect;
        try {
            
            if ((lect = super.read()) != KeyCar.ESC) {
                return lect;
            }
            
            switch(lect = super.read()) {
                case 'O':
                    switch(lect = super.read()) {
                        case 'H': return KeyCar.HOME;
                        case 'F': return KeyCar.END;
                        default: return lect;
                    }
                case '[':
                    switch(lect = super.read()) {
                        case 'C': return KeyCar.RIGHT_ARROW;
                        case 'D': return KeyCar.LEFT_ARROW;
                        case '1': 
                        case '2':
                        case '3':
                        case '4':
                            if ((lect = super.read()) != '~')
                                return lect;
                            return KeyCar.HOME + lect - '1';
                        default: return lect;
                    }
                default: return lect;
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