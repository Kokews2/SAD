import java.util.ArrayList;

public class Line {

    private ArrayList<Character> line;
    private boolean mode; // True = sobreescrivir. False = insertar
    private int pos;

    public Line() {
        line = new ArrayList<>();
        mode = false;
        pos = 0;
    }

    public void addChar(char car) {
        if (!mode || pos >= line.size()) {
            line.add(pos, car);
        } else {
            line.set(pos, car);
        }
        System.out.print(car);
        pos++;
    }

    public void home() {
        if (pos > 0) {
            this.pos = 0;
        }
    }

    public void end() {
        if (pos < line.size()) {
            pos = line.size();
        }
    }

    public void right() {
        if (pos < line.size()) {
            pos++;
        }
    }

    public void left() {
        if (pos > 0) {
            pos--;
        }
    }

    public void delete() {
        if (pos < line.size()) {
            line.remove(pos);
        }
    }

    public void backSpace() {
        if (pos > 0) {
            pos--;
            line.remove(pos);
        }
    }

    public void insert() {
        mode = !mode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (char s : line)
            str = str.append(s);
        return str.toString();
    }

}