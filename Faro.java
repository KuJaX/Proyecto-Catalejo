import java.io.Serializable;
public class Faro implements Serializable {
    private String nombre;
    private String longitud;
    private String latitud;
    private String color;
    private String repeticiones;
    private double TiempoTotal;
    private String coord_X;
    private String coord_Y;
    private double[] luz;
    private double[] ocultacion;

    public Faro(String nombre, String longitud, String latitud, String coord_X,
                String coord_Y, String repeticiones, String color,  
                double[] ocultacion, double[] luz, double TiempoTotal) {
        this.nombre  = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.coord_X = coord_X;
        this.coord_Y = coord_Y;
        this.luz = luz;
        this.ocultacion = ocultacion;
        this.color = color;
        this.repeticiones = repeticiones;
        this.TiempoTotal = TiempoTotal;
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getCoord_X() {
        return coord_X;
    }

    public String getCoord_Y() {
        return coord_Y;
    }

    public double[] getLuz() {
        return luz;
    }

    public double[] getOcultacion() {
        return ocultacion;
    }
    
    public String getColor() {
        return color;
    }

    public String getRepeticiones() {
        return repeticiones;
    }
    
    public double getTiempoTotal() {
        return TiempoTotal;
    }
    
    public String toString() {
        return nombre+" "+longitud+" "+latitud+" "+coord_X+" "+coord_Y
                +" "+luz+" "+ocultacion+" "+color+" "+repeticiones+" "+TiempoTotal;
    }

}

