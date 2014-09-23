import java.util.ArrayList;

public class Resultado {
    private Faro faro;
    private ArrayList<Double> indicesDeCorrectitud;
    
    public Resultado(Faro faro) {
        this.faro = faro;
        this.indicesDeCorrectitud = new ArrayList<Double>();
    }
    
    public Faro getFaro() {
        return faro;
    }
    
    public void addIndice(double i) {
        this.indicesDeCorrectitud.add(i);
    }
    
    public ArrayList<Double> getIndices() {
        return this.indicesDeCorrectitud;
    }

    @Override
    public String toString() {
        return "\n"+faro + " indices=" + indicesDeCorrectitud;
    }
}
