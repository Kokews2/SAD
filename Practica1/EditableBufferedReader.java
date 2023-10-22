import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(InputStreamReader  in) {
        super(in);
    }

    public void setRaw() {
        try {
            // Creem un procés que executi la comana
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "stty -echo raw</dev/tty");
            Process process = processBuilder.start();

            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void unsetRaw() {
        try {
            // Creem un procés que executi la comana
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "stty cooked echo</dev/tty");
            Process process = processBuilder.start();

            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

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
                        default: return -1;
                    }
                case '[':
                    switch(lect = super.read()) {
                        case 'C': return KeyCar.M_RIGHT;
                        case 'D': return KeyCar.M_LEFT;
                        case '1': 
                            if ((lect = super.read()) != '~')
                                return -1;
                            return KeyCar.M_HOME;
                        case '2':
                            if ((lect = super.read()) != '~')
                                return -1;
                            return KeyCar.M_INS;
                        case '3':
                            if ((lect = super.read()) != '~')
                                return -1;
                            return KeyCar.M_DEL;
                        case '4':
                            if ((lect = super.read()) != '~')
                                return -1;
                            return KeyCar.M_END;
                        default: return -1;
                    }
                default: return -1;
            }
        } catch (IOException ex) {
            System.out.println("Interrupted Exception");
        }
        return -1;
    }

    public String readLine() throws IOException {
        try {

            setRaw();

            Line line = new Line();
            int key;
            
            while ((key = this.read()) != KeyCar.CR){
                switch(key) {
                    case KeyCar.M_RIGHT: line.moveCursorRight();
                    break;
                    case KeyCar.M_LEFT: line.moveCursorLeft();
                    break;
                    case KeyCar.HOME: line.home();
                    break;
                    case KeyCar.END: line.end();
                    break;
                    case KeyCar.INS: line.insert();
                    break;
                    case KeyCar.DEL: line.delete();
                    break;
                    case KeyCar.BS: line.deleteChar();
                    break;
                    default: line.addChar((char) key);
                }
            }

            return line.toString();

        } catch (IOException e) {
            throw e;
        } finally{

            unsetRaw();

        }
    }
}
