import java.io.Serializable;

public class Film implements Serializable, Comparable<Film> {
    private String titolo;
    private Integer anno;
    private Integer durata;
    private Integer voto;
    private Boolean visto;


    public Film(String titolo,Integer anno,Integer durata,Integer voto,Boolean visto) {
        this.titolo = titolo;
        this.anno = anno;
        this.durata = durata;
        this.voto = voto;
        this.visto = visto;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
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
                ", voto=" + voto +
                ", visto=" + visto +
                '}';
    }

    @Override
    public int compareTo(Film f) {

        return titolo.compareTo(f.getTitolo());

    }
}
