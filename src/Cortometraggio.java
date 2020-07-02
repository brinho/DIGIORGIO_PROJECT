public class Cortometraggio extends Film {

    private Integer metraggio;

    public Cortometraggio(String titolo, Integer anno, Integer durata, Integer metraggio) {
        super(titolo, anno, durata);
        this.metraggio = metraggio;
    }

    public Integer getMetraggio() {
        return metraggio;
    }

    public void setMetraggio(Integer metraggio) {
        this.metraggio = metraggio;
    }
    

    @Override
    public String toString() {
        return "Cortometraggio{" +
                "titolo='" + super.getTitolo() + '\'' +
                ", anno=" + super.getAnno() +
                ", durata=" + super.getDurata() +
                ", metraggio=" + metraggio +
                '}';
    }

}
