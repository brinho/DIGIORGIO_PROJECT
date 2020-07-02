import java.util.Comparator;

public class LengthComparator implements Comparator<Cortometraggio> {
    @Override
    public int compare(Cortometraggio c1, Cortometraggio c2) {
        return c1.getMetraggio() - c2.getMetraggio();
    }
}
