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

    private void notifyLineChanged(String oldValue, String newValue) {
        propertyChangeSupport.firePropertyChange("line", oldValue, newValue);
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void moveCursorRight() {
        if (cursorPosition < line.length()) {
            notifyLineChanged(null, KeyCar.M_RIGHT)
            cursorPosition++;
        }
    }
    
    public void moveCursorLeft() {
        if (cursorPosition > 0) {
            notifyLineChanged(null, KeyCar.M_LEFT)
            cursorPosition--;
        }
    }

    public void home() {
        notifyLineChanged(null, KeyCar.M_HOME)
        cursorPosition = 0;
    }

    public void end() {
        notifyLineChanged(null, KeyCar.M_END)
        cursorPosition = line.length();
    }

    public void insert() {
        insertMode = !insertMode;
    }

    public void delete() {
        if (cursorPosition >= 0 && cursorPosition < line.length()) {
            notifyLineChanged(null, KeyCar.M_DEL)
            line.deleteCharAt(cursorPosition);
        }
    }

    public void deleteChar() {
        if (cursorPosition > 0 && cursorPosition <= line.length()) {
            notifyLineChanged(null, KeyCar.M_DEL)
            line.deleteCharAt(cursorPosition - 1);
            moveCursorLeft();
        }
    }

    public void addChar(char car) {
        if (insertMode && cursorPosition < line.length()) {
            notifyLineChanged(null, KeyCar.M_INS)            
            line.setCharAt(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } else {
            notifyLineChanged(null, KeyCar.M_NOINS)
            line.insert(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } 
    }
    
    @Override
    public String toString() {
        return line.toString();
    }

    @Override
    public int length() {
        return line.length();
    }
}