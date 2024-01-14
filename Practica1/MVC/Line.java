import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Line {

    private StringBuffer line;
    private int cursorPosition;
    private boolean insertMode; 

    Object oldValue = 0;
    private PropertyChangeSupport propertyChangeSupport;

    public Line() {
        line = new StringBuffer();
        cursorPosition = 0;
        insertMode = false;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void notifyLineChanged(Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange("line", oldValue, newValue);
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void moveCursorRight() {
        if (cursorPosition < line.length()) {
            notifyLineChanged(oldValue, KeyCar.M_RIGHT);
            oldValue = KeyCar.M_RIGHT;
            cursorPosition++;
        }
    }
    
    public void moveCursorLeft() {
        if (cursorPosition > 0) {
            notifyLineChanged(oldValue, KeyCar.M_LEFT);
            oldValue = KeyCar.M_LEFT;
            cursorPosition--;
        }
    }

    public void home() {
        notifyLineChanged(oldValue, KeyCar.M_HOME);
        oldValue = KeyCar.M_HOME;
        cursorPosition = 0;
    }

    public void end() {
        notifyLineChanged(oldValue, KeyCar.M_END);
        oldValue = KeyCar.M_END;
        cursorPosition = line.length();
    }

    public void insert() {
        insertMode = !insertMode;
    }

    public void delete() {
        if (cursorPosition >= 0 && cursorPosition < line.length()) {
            notifyLineChanged(oldValue, KeyCar.M_DEL);
            oldValue = KeyCar.M_DEL;
            line.deleteCharAt(cursorPosition);
        }
    }

    public void deleteChar() {
        if (cursorPosition > 0 && cursorPosition <= line.length()) {
            notifyLineChanged(oldValue, KeyCar.M_DEL);
            oldValue = KeyCar.M_DEL;
            line.deleteCharAt(cursorPosition - 1);
            moveCursorLeft();
        }
    }

    public void addChar(char car) {
        if (insertMode && cursorPosition < line.length()) {
            notifyLineChanged(oldValue, KeyCar.M_INS);  
            oldValue = KeyCar.M_INS;       
            line.setCharAt(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } else {
            notifyLineChanged(oldValue, KeyCar.M_NOINS);
            oldValue = KeyCar.M_NOINS;
            line.insert(cursorPosition, car);
            System.out.print(car);
            cursorPosition++;
        } 
    }
    
    @Override
    public String toString() {
        return line.toString();
    }

    public int length() {
        return line.length();
    }
}