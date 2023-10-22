public class Line {

    private StringBuffer line;
    private int cursorPosition;
    private boolean insertMode; 

    public Line() {
        line = new StringBuffer();
        cursorPosition = 0;
        insertMode = false;
    }
    
    public int getCursorPosition() {
        return cursorPosition;
    }

    public void moveCursorRight() {
        if (cursorPosition < line.length()) {
            cursorPosition++;

            // Movemos cursor a la derecha (secuencia ANSI)
            System.out.print("\u001b[1C");
        }
    }
    
    public void moveCursorLeft() {
        if (cursorPosition > 0) {
            cursorPosition--;

            // Movemos cursor a la izquierda (secuencia ANSI)
            System.out.print("\u001b[1D");
        }
    }

    public void home() {
        cursorPosition = 0;

        // Movemos el cursor a la izquierda 'cursorPosition' veces (secuencia ANSI)
        System.out.print("\u001b[" + cursorPosition + "D");
    }

    public void end() {
        cursorPosition = line.length();

        // Movemos el cursor al final de la linea (secuencia ANSI)
        int posRestantes = line.length() - cursorPosition; 
        System.out.print("\u001b[" + posRestantes + "C");
    }

    public void insert() {
        insertMode = !insertMode;
    }

    public void delete() {
        if (cursorPosition >= 0 && cursorPosition < line.length()) {
            line.deleteCharAt(cursorPosition);

            // Eliminamos el caracter en la posicion actual del cursor y mover el texto (secuencia ANSI)
            System.out.print("\u001b[P");
        }
    }

    public void deleteChar() {
        if (cursorPosition > 0 && cursorPosition <= line.length()) {
            line.deleteCharAt(cursorPosition - 1);
            moveCursorLeft();

            // Eliminamos el caracter en la posicion actual del cursor y mover el texto (secuencia ANSI)
            System.out.print("\u001b[P");
        }
    }

    public void addChar(char car) {
        if (insertMode && cursorPosition <= line.length()) {
            // Activamos modo de inserccion (secuencia ANSI)
            System.out.println("\u001b[4h");
            
            line.setCharAt(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } else {
            // Desactivamos modo de inserccion (secuencia ANSI)
            System.out.print("\u001b[4l");

            line.insert(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        }
    }
    
    @Override
    public String toString() {
        home();
        return line.toString();
    }
}