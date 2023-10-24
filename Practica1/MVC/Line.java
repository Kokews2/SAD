import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Line {

    private StringBuffer line;
    private int cursorPosition;
    private boolean insertMode; 

    private PropertyChangeSupport propertyChangeSupport;

    public Line() {
        line = new StringBuffer();
        cursorPosition = 0;
        insertMode = false;

        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    // Métodos para manejar los oyentes de cambio de propiedad
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void moveCursorRight() {
        if (cursorPosition < line.length()) {
            // Movemos cursor a la derecha (secuencia ANSI)
            System.out.print("\u001b[1C");

            cursorPosition++;
        }
    }
    
    public void moveCursorLeft() {
        if (cursorPosition > 0) {
            // Movemos cursor a la izquierda (secuencia ANSI)
            System.out.print("\u001b[1D");

            cursorPosition--;
        }
    }

    public void home() {
        // Movemos el cursor a la izquierda 'cursorPosition' veces (secuencia ANSI)
        System.out.print("\u001b[" + cursorPosition + "D");

        cursorPosition = 0;
    }

    public void end() {
        // Movemos el cursor al final de la linea (secuencia ANSI)
        int posRestantes = line.length() - cursorPosition; 
        System.out.print("\u001b[" + posRestantes + "C");

        cursorPosition = line.length();
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
        if (insertMode && cursorPosition < line.length()) {
            // Activamos modo de reemplazar (secuencia ANSI)
            System.out.print("\u001b[4l");
            
            line.setCharAt(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } else {
            // Desactivamos modo de reemplazar (secuencia ANSI)
            System.out.print("\u001b[4h");

            line.insert(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } 
    }
    
    @Override
    public String toString() {
        return line.toString();
    }
}