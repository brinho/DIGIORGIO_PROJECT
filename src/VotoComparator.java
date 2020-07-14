import java.util.Comparator;

public class VotoComparator  implements Comparator<Film> {
    public int compare(Film f1, Film f2) {
        return f1.getVoto() - f2.getVoto();
    }
}
