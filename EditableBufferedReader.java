import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(Reader in) {
        super(in);
    }

    void setRaw(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("***** RAW MODE *****");

        while(true){
            if(scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }    
        }
    }

    void unsetRaw() {

    }

    public int read() throws IOException {
        return 0;
    }

    public String readLine() throws IOException {
        return "";
    }
}