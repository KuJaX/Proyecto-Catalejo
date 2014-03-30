import java.text.Normalizer;
import java.util.ArrayList;


public class Conversor {
    private static final String mayusculas = "QWERTYUIOPLKJHGFDSAZXCVBNM";

    public static String slug(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll( "[^\\p{ASCII}]", "")
                .replaceAll( "[^a-zA-Z0-9 ]", "" );
    }
    
    public static boolean esMayuscula(char letra) {
        return mayusculas.contains(slug(letra+""));
    }
    
    public static void main(String[] args) {
        
        Metodos a = new Metodos();
     
        ArrayList<Resultado> faros = a.leer("codigos.txt");
        System.out.println("Todos los faros:");
        System.out.println(faros);
     
        ArrayList<Resultado> resultados = a.filtrarPorTiempo(faros, 7, 2);
        System.out.println("Faros filtrados:");
        System.out.println(resultados);
     
     
    /*
       System.out.println(esMayuscula('A'));
       System.out.println(esMayuscula('b'));
       System.out.println(esMayuscula('É'));
       */
    }   
}
