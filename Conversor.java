import java.text.Normalizer;


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
     
     a.leer("codigos.txt");
     
     
     
    /*
       System.out.println(esMayuscula('A'));
       System.out.println(esMayuscula('b'));
       System.out.println(esMayuscula('É'));
       */
    }   
}
