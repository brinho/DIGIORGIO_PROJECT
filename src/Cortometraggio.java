public class Cortometraggio extends Film {

    private Integer metraggio;

    public Cortometraggio(String titolo, Integer anno, Integer durata,Integer voto,Boolean visto, Integer metraggio) {
        super(titolo, anno, durata,voto,visto);
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
                ", voto=" + super.getVoto() +
                ", visto=" + super.getVisto() +
                ", metraggio=" + metraggio +
                '}';
    }

}
