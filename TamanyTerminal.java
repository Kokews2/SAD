import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TamanyTerminal {
    public static void main(String[] args) throws InterruptedException {
        try {
            // Executa la comanda "tput cols" per obtenir la mida en columnes
            ProcessBuilder processBuilder = new ProcessBuilder("tput", "cols");
            Process process = processBuilder.start();

            // Llegaix la sortida de la comanda
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                int columns = Integer.parseInt(line);
                System.out.println("Tamaño en columnas del terminal: " + columns);
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}