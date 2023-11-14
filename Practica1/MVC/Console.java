import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Console implements PropertyChangeListener{
    private Line line;

    public Console(Line line) {
        this.line = line;
        line.addPropertyChangeListener(this);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("line".equals(evt.getPropertyName())) {
            
            int newContent = (int) evt.getNewValue();

            switch (newContent) {
                case KeyCar.M_RIGHT: System.out.print("\u001b[1C"); 
                break;
                case KeyCar.M_LEFT: System.out.print("\u001b[1D");
                break;
                case KeyCar.M_HOME: System.out.print("\u001b[" + line.getCursorPosition() + "D");
                break;
                case KeyCar.M_END: System.out.print("\u001b[" + (line.length()-line.getCursorPosition()) + "C");
                break;
                case KeyCar.M_DEL: System.out.print("\u001b[P");
                break;
                case KeyCar.M_INS: System.out.print("\u001b[4l");
                break;
                case KeyCar.M_NOINS: System.out.print("\u001b[4h");
                break;
                default: break;
            }
        }
    }
}
