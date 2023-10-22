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

        // Movemos el cursor a la fila 0 y columna 0 (secuencia ANSI)
        System.out.print("\u001b[0;0H");
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

            // Mover el texto vacio dejado por el caracter eliminado
            System.out.print("\u001b[P");
        }
    }

    public void deleteChar() {
        if (cursorPosition > 0 && cursorPosition <= line.length()) {
            line.deleteCharAt(cursorPosition - 1);
            moveCursorLeft();
        }
    }

    public void addChar(char car) {
        if (cursorPosition >= 0 && cursorPosition <= line.length()) {
            line.insert(cursorPosition, car);
            moveCursorRight();
            System.out.print(line.toString());
        }
    }
    
    @Override
    public String toString() {
        return line.toString();
    }
}