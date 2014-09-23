
import java.io.Serializable;
import java.util.ArrayList;

public class Faro implements Serializable {
    private String nombre;
    private String color;
    private ArrayList<Double> indice;
    private Coordenada<Double> coordenadaCartesiana;
    private Coordenada<String> coordenadaRadial;
    private ArrayList<Destello> destellos;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setIndice(ArrayList<Double>  indice) {
        this.indice = indice;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCoordenadaCartesiana(Coordenada<Double> coordenadaCartesiana) {
        this.coordenadaCartesiana = coordenadaCartesiana;
    }

    public void setCoordenadaRadial(Coordenada<String> coordenadaRadial) {
        this.coordenadaRadial = coordenadaRadial;
    }

    public void setDestellos(ArrayList<Destello> destellos) {
        this.destellos = destellos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Double> getIndice() {
        return indice;
    }

    public Coordenada<Double> getCoordenadaCartesiana() {
        return coordenadaCartesiana;
    }

    public Coordenada<String> getCoordenadaRadial() {
        return coordenadaRadial;
    }

    public ArrayList<Destello> getDestellos() {
        return destellos;
    }

    @Override
    public String toString() {
        return "[FARO: "+nombre+" / "+"COLOR: "+color+" / "+"COORDENADAS CARTESIANAS: "+coordenadaCartesiana+" / "+"COORDENADAS RADIALES: "+coordenadaRadial+" / "
                +"CICLO: "+destellos+" / ";
    }
}
