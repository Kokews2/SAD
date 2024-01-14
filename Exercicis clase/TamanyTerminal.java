import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class TamanyTerminal {

    private static String readChars(Reader r) throws IOException {
        StringBuilder str = new StringBuilder();

        do {
            str.append((char) r.read());
        } while(r.ready());

        return str.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        int columns = 0;

        // ****************************** 1a Alternativa ******************************

        //              D'AQUESTA MANERA EXECUTEM UN PROGRAMA EXTERN !!!!!!!!
        /*try {
            // Executa la comanda "tput cols" per obtenir la mida en columnes y li diem que ho faci al terminal de treball
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "tput cols 2>/dev/tty");
            Process process = processBuilder.start();

            // Llegeix la sortida de la comanda
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                columns = Integer.parseInt(line);
                System.out.println("Tamaño en columnas del terminal: " + columns);
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            // Posem el terminal en mode Raw perque no ens mostri la sequncia de ESCAPE
            //EditableBufferedReader.setRaw();

            /*
             * La següent seqüència espace (CSI) retorna les dimensions del terminal de forma:
             * ESC [ 8 ; rows ; columns t
             */
            //System.out.print("\033[18t");

            // ****************************** 2a Alternativa ******************************

            /* // Volem capturar les columnes que tenen la forma:
            // ESC [ 8 ; rows ; columns t
            Scanner sc = new Scanner(System.in);
            sc.skip("\033\\[8;\\d+;(\\d+)t");
            columns = Integer.parseInt(sc.match().group(1));
            */

            // ****************************** 3a Alternativa ******************************
            
            // La idea es utilitzar delimitadors, en el nostre cas per llegir les rows tenim com a 
            // delimitador el ';' y per les columns tenim el 't'
            // ESC [ 8 ; rows ; columns t
            /* Scanner sc = new Scanner(System.in);
            sc.skip("\033\\[8;").useDelimiter(";");
            sc.nextInt(); // Obviem les columnes
            sc.skip(";").useDelimiter("t");
            columns = sc.nextInt();
            sc.skip("t"); */
            
            // ****************************** 4a Alternativa ******************************
            
            // Ara utilitzem Strings en comptes de expresions regulars
            // Volem buidar i guardar en forma de String tot el que hi ha al Buffer. Hem de fer una funció.
            /* try {
                String str = readChars(new BufferedReader(new InputStreamReader(System.in)));
                String cols = str.substring(str.lastIndexOf(";"), str.length() - 1);
                columns = Integer.parseInt(cols);
            } catch (IOException e) {
                System.out.println(e);
            } */


            // ****************************** 5a Alternativa ******************************

            // Podemos leer de la variable de entorno $COLUMNS el valor, pero li hem de passar la variable d'entorn amb el nom
            // COLUMNS = $COLUMNS java TamanyTerminal
            // O fer un 'export COLUMNS'
            columns = Integer.parseInt((System.getenv("COLUMNS")));
        } finally {
            // EditableBufferedReader.unsetRaw();
        }
        System.out.println("COLUMNS = " + columns);
    }
}
