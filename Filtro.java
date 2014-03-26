import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos_Filtro {
    
    public String filtro(String nombre) throws IOException{
        ObjectInputStream ois=null;

        try{
            File f = new File ("faros.obj");
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            
            while (true){
                Faro faros = (Faro) ois.readObject();
                System.out.println("Tipo: "+faros.getTipo());
                
            }
            /*
            ArrayList<Faro> lista = new ArrayList<Faro>();
            boolean fin = false;
            
            while (entrada.available()>0){
                Faro faros = (Faro) entrada.readObject();
                lista.add(faros);     
            
            }
            entrada.close();
            for (Faro r : lista) {
                System.out.println(r);
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
          }   
        catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("\n**********fin*************");
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Metodos_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            ois.close();
        }
        return null;
    
}
}
