import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos {
    
    private static final String mayusculas = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    
    public ArrayList<Resultado> filtrarPorTiempo(ArrayList<Resultado> res, double tiempo, double margen){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        for (Resultado r : res){
            Faro f = r.getFaro();
            
            if(tiempo-margen<=f.getTiempoTotal() && tiempo+margen>=f.getTiempoTotal()){
                r.addIndice(1-Math.abs(tiempo-f.getTiempoTotal())/margen);
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
     public ArrayList<Resultado> filtrarPorRepeticiones(ArrayList<Resultado> res, int repeticiones, double margen){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        for (Resultado r : res){
            Faro f = r.getFaro();
            
            if(repeticiones-margen<=f.getRepeticiones()&& repeticiones+margen>=f.getRepeticiones()){
                r.addIndice(1-Math.abs(repeticiones-f.getRepeticiones())/margen);
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
    public ArrayList<Resultado> filtrarPorTipo(ArrayList<Resultado> res, int repeticiones, double margen){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        for (Resultado r : res){
            Faro f = r.getFaro();
            String destello = "D";
            String ocultaciones = "Oc";
            double repeticionesPorMinuto = (repeticiones*60)/f.getTiempoTotal();
            
            if(repeticionesPorMinuto<60 && f.getTipo().equalsIgnoreCase(destello) ||
               repeticionesPorMinuto<60 && f.getTipo().equalsIgnoreCase(ocultaciones)){
                r.addIndice(1-Math.abs(repeticionesPorMinuto-59)/margen);
                resultados.add(r);
                
                }
            else if(repeticionesPorMinuto>=60 && repeticionesPorMinuto<120){
                r.addIndice(1-Math.abs(repeticionesPorMinuto-119)/margen);
                resultados.add(r);
                }
            else if(repeticionesPorMinuto>=120){
                r.addIndice(1);
                resultados.add(r);
                }
            }
        return resultados;
    }

    public static String slug(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9 ]", "");
    }

    public static boolean esMayuscula(char letra) {
        return mayusculas.contains(slug(letra + ""));
    }

    public ArrayList<Resultado> leer(String nombre) {
        FileReader lectorArchivo;
        ArrayList<Resultado> lista = new ArrayList<Resultado>();
        try {
            lectorArchivo = new FileReader(new File(nombre));
            BufferedReader br = new BufferedReader(lectorArchivo);
            String linea;

            linea = br.readLine();

            while (linea != null) {
                String tipo = "";
                String color = "";
                String segundos = "";
                String repeticiones = "1";
                String tiempoTotal = "0"; 
                int numeroDeDestellos = 0;
                int n = 0;

                System.out.println(linea);

                while (n < linea.length()) {
                    char caracter = linea.charAt(n);
                    char caracterSiguiente = '-';

                    try {
                        caracterSiguiente = linea.charAt(n + 1);
                    } catch (Exception e) {
                    }

                    if (esMayuscula(caracter) || caracter == ' ') {
                        switch (caracter) {
                            case ' ':
                                tiempoTotal = "";
                                char tiempo;

                                while (true) {
                                    n++;
                                    try {
                                        tiempo = linea.charAt(n);
                                        if (tiempo == 's') {
                                            break;
                                        } 
                                        else tiempoTotal += tiempo==','?'.':tiempo;
                                    } catch (Exception e) {
                                        break;
                                    }
                                }

                                break;

                            case 'G':
                                if (caracterSiguiente == 'p') {
                                    n++;
                                } else {
                                    System.out.println("Error, Gp chungo: " + linea + ", " + caracter);
                                }
                                break;

                            case 'D':
                            case 'C':
                            case 'O':
                                tipo += caracter;
                                if (caracter != 'D') {
                                    if ((caracter == 'O' && caracterSiguiente == 'c') || (caracter == 'C' && caracterSiguiente == 't')) {
                                        tipo += caracterSiguiente;
                                        n++;
                                    } else {
                                        System.out.println("Error, Tipo chungo: " + linea + ", " + caracter);
                                        break;
                                    }
                                }

                                if (linea.charAt(n + 1) == '(') {
                                    repeticiones = "";
                                    char parte;
                                    String cadena;
                                    int numero;
                                    
                                    while (true) {
                                        n++;
                                        parte = linea.charAt(n + 1);
                                        if (parte == ')') {
                                            break;
                                        }
                                        if(parte == '+') { 
                                            parte = linea.charAt(n + 2);
                                            cadena = "" + parte;
                                            numero = Integer.parseInt(cadena);

                                            numeroDeDestellos += numero;
                                            repeticiones = Integer.toString(numeroDeDestellos);
                                            break;
                                        }
                                        cadena = "" + parte;
                                        numero = Integer.parseInt(cadena);

                                        numeroDeDestellos += numero;
                                        repeticiones = Integer.toString(numeroDeDestellos);
                                        
                                    }

                                }

                                break;

                            case 'V':
                            case 'A':
                            case 'B':
                                color += caracter;
                                break;

                            case 'R':
                                if (caracterSiguiente == 'p') {
                                    tipo += caracter;
                                    tipo += caracterSiguiente;
                                    n++;
                                } else {
                                    color += caracter;
                                }

                                break;
                        }

                    } else {
                        System.out.println("Error, algo chungo está pasando: " + linea + ", " + caracter);
                    }

                    n++;
                    System.out.println(linea + " - " + tipo + ":" + repeticiones + " - " + color + " - " + tiempoTotal);
                
                }
                Faro f = new Faro(tipo, Integer.parseInt(repeticiones), color, Double.parseDouble(tiempoTotal));
		lista.add(new Resultado(f));
                linea = br.readLine();
            }
            br.close();
            lectorArchivo.close();

            ObjectOutputStream salida;
            try {
                salida = new ObjectOutputStream(new FileOutputStream("faros.obj"));

                for(Resultado r : lista) {
                    salida.writeObject(r.getFaro());
                }

                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }

           /* System.out.println("Datos guardados");
            for (Faro r : lista) {
                System.out.println(r);
            }*/
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista;
    }
}


