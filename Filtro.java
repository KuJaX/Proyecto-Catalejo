
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos_Filtro {
    
    public String filtro (String nombre){
        
        try{
        ObjectInputStream entrada=new ObjectInputStream
        (new FileInputStream("faros.obj"));
        
        try{
        ArrayList faros = (ArrayList)entrada.readObject();
        String tiempo = "10s";
        int pos = faros.indexOf(tiempo);
        if(pos!=-1)
            System.out.println(tiempo + " se ha encontrado en la posición: "+pos);
        else
            System.out.println(tiempo + " no se ha encontrado");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
            }
        entrada.close();
        }
        catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        
        return null;
    
    }
    
}
