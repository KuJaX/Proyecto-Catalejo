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

            while (entrada.available()>0){
                Faro faros = (Faro) entrada.readObject();
                
                    System.out.println(faros);
                
            entrada.close();
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    
    }
    
}
