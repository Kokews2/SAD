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
    public void setRaw() throws InterruptedException {
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
        return 0;
    }

    public String readLine() throws IOException {
        return "";
    }
}