/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;

/**
 *
 * @author alejandropoloavila
 */
public class Faro implements Serializable {
    private StringBuffer tipo;
    private StringBuffer color;
   
    private int repeticiones;
    private int tT;

    public Faro(StringBuffer tipo, StringBuffer color, int repeticiones, int tT) {
        this.tipo = tipo;
        this.color = color;
        this.repeticiones = repeticiones;
        this.tT = tT;
        
    }
    
    public StringBuffer getTipo() {
        return tipo;
    }
    
    public StringBuffer getColor() {
        return color;
    }

    public int getRepeticiones() {
        return repeticiones;
    }
    
    public int gettT() {
        return tT;
    }
    
    public String toString() {
        return tipo+" "+color+" "+repeticiones+" "+tT;
    }

}
