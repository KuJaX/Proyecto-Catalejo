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
        Buffer b = new Buffer();
        ArrayList <Destello> ciclo = b.simular();
        int repeticiones = a.numeroDeRepeticiones(ciclo);
        double tiempoTotal = a.tiempoTotal(ciclo);
        
        ArrayList<Resultado> faros = a.parseFile("results.txt");
        System.out.println("Todos los faros:");
        System.out.println(faros);
        
        ArrayList<Resultado> resultados = a.filtrarPorTiempo(faros, tiempoTotal, 2);
        System.out.println("Faros filtrados:");
        System.out.println(resultados);
        
        ArrayList<Resultado> resultadosPorRepeticiones = a.filtrarPorRepeticiones(resultados, repeticiones, 1);
        System.out.println("Faros filtrados:");
        System.out.println(resultadosPorRepeticiones);
        
        ArrayList<Resultado> calculoPorcentajes = a.calcularPorcentaje(resultadosPorRepeticiones);
        System.out.println("Faros filtrados segun mayor probabilidad:");
        System.out.println(calculoPorcentajes);
        
    }   
}
