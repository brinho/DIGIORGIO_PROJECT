import java.io.Serializable;

public class Film implements Serializable, Comparable<Film> {
    private String titolo;
    private Integer anno;
    private Integer durata;


    public Film(String titolo,Integer anno,Integer durata) {
        this.titolo = titolo;
        this.anno = anno;
        this.durata = durata;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public void play(){
        System.out.println("Playing the film ......" );
    }

    @Override
    public String toString() {
        return "Film{" +
                "titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", durata=" + durata +
                '}';
    }

    @Override
    public int compareTo(Film f) {

        return titolo.compareTo(f.getTitolo());

    }
}