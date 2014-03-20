import java.io.Serializable;

/**
 *
 * @author alejandropoloavila
 */
public class Faro implements Serializable {
    private String tipo;
    private String color;
   
    private String repeticiones;
    private String tT;

    public Faro(String tipo, String color, String repeticiones, String tT) {
        this.tipo = tipo;
        this.color = color;
        this.repeticiones = repeticiones;
        this.tT = tT;
        
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getColor() {
        return color;
    }

    public String getRepeticiones() {
        return repeticiones;
    }
    
    public String gettT() {
        return tT;
    }
    
    public String toString() {
        return tipo+" "+color+" "+repeticiones+" "+tT;
    }

}

