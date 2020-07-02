import java.util.Comparator;

public class TimeComparator implements Comparator<Film> {
    @Override
    public int compare(Film f1, Film f2) {
        return f1.getDurata() - f2.getDurata() ;
    }
}
