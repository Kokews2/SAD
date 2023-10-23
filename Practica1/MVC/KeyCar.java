public class KeyCar {

    // Keys
    static final int CR = 13; 
    static final int BS = 127; 
    static final int ESC = 27;

    static final int INS = 50;      //  "^[[2~" donde 50 = "2"
    static final int DEL = 51;      //  "^[[3~" donde 51 = "3"
    static final int RIGHT = 67;    //  "^[[C" donde 67 = "C"
    static final int LEFT = 68;     //  "^[[D" donde 68 = "D"
    static final int END = 70;      //  "^[[F" donde 70 = "F"
    static final int HOME = 72;     //  "^[[H" donde 72 = "H"

    // Mapping (para el metodo read())
    public final static int M_HOME = -1; 
    public final static int M_INS = -2; 
    public final static int M_DEL = -3; 
    public final static int M_END = -4;
    public final static int M_RIGHT = -5;
    public final static int M_LEFT = -6;
}

