import java.util.Comparator;

public class YearComparator implements Comparator<Film> {
    @Override
    public int compare(Film f1, Film f2) {
        return f1.getAnno() - f2.getAnno();
    }
}
