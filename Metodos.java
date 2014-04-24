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
                String nombre_faro = "";
                String longitud = "";
                String latitud = "";
                String color = "";
                String segundos = "";
                String repeticiones = "1";
                String tiempoTotal = ""; 
                String coord_X = "";
                String coord_Y = "";
                double[] luz = null;
                double[] ocultaciones = null;
                int numeroDeDestellos = 0;
                int n = 0;
                int i = 0;
                int t;
                int v = 0;
                int k;
                double tiempo_Luz = 0;
                double tiempo_Ocultacion = 0;

                System.out.println(linea);

                while (n < linea.length()) {
                    t = 0;
                    char caracter = linea.charAt(n);
                    char caracterSiguiente = '-';
                    try {
                        caracterSiguiente = linea.charAt(n + 1);
                    } catch (Exception e) {
                    }
                    if(caracter == '|'){
                        i++;   
                    }
                    else {
                        switch (i){
                            case 0:
                                if(caracter == ' ') break;
                                else coord_X += caracter;
                                break;
                            case  1:
                                if(caracter == ' ') break;
                                else coord_Y += caracter;
                                break;
                            case  2:
                            case  3:
                                break;
                            case  4:
                                if(caracter == ' ' && caracterSiguiente == '|') break;
                                else if(caracter == ' ' && caracterSiguiente != '|') 
                                     nombre_faro += caracter;
                                else nombre_faro += caracter;
                                break;
                            case  5:
                                if(caracter == ' ') break;
                                else longitud += caracter;
                                break;
                            case  6:
                                if(caracter == ' ') break;
                                else latitud += caracter;
                                break;
                            case  7:
                                if(caracter == ' ') break;
                                else color += caracter;
                                break;
                            case  8:
                                
                                char parte;
                                String cadena;
                                int numero;
                                if(caracter == '('){
                                    while(true){
                                        n++;
                                        parte = linea.charAt(n);
                                        if(parte == ')') break;
                                        else if(parte == '+'){
                                            parte = linea.charAt(n + 1);
                                            cadena = "" + parte;
                                            numero = Integer.parseInt(cadena);

                                            numeroDeDestellos += numero;
                                            repeticiones = Integer.toString(numeroDeDestellos);
                                            break;
                                        }
                                        else {
                                            cadena = "" + parte;
                                            numero = Integer.parseInt(cadena);
                                            numeroDeDestellos += numero;
                                            repeticiones = Integer.toString(numeroDeDestellos);
                                        }
                                        
                                    }
                                }
                                else break; 
                            case  9:
                                break;
                            case 10:
                                char cuenta;
                                char punto = '.';
                                String tiempoL = "";
                                String tiempoO = "";
                                String cadena_cuenta;
                                int numero_que_se_repite;
                                luz = new double[50];
                                ocultaciones = new double[50];
                                //caracteristicas
                                if(caracter == 'L'){
                                    t++;
                                    n += 2;
                                    caracter = linea.charAt(n);
                                            if(linea.charAt(n+8) == ')'){
                                                cuenta = linea.charAt(n+9);
                                                cadena_cuenta = "" + cuenta;
                                                numero_que_se_repite = Integer.parseInt(cadena_cuenta);
                                                
                                                if(caracterSiguiente == ' ' && linea.charAt(n+2) != 'o'){
                                                tiempoL += caracter;
                                                tiempoL += punto;
                                                tiempoL += linea.charAt(n+2);
                                                tiempo_Luz = Double.parseDouble(tiempoL);
                                                    for(k = 0;k < numero_que_se_repite;k++){
                                                        luz[t] = tiempo_Luz;
                                                        t++;
                                                    }
                                                }
                                                else {
                                                    tiempoL += caracter;
                                                    tiempo_Luz = Double.parseDouble(tiempoL);
                                                    for(k = 0;k < numero_que_se_repite;k++){
                                                        luz[t] = tiempo_Luz;
                                                        t++;
                                                    }
                                                }        
                                            }
                                            else if(caracterSiguiente == ' ' && linea.charAt(n+2) != 'o'){
                                                tiempoL += caracter;
                                                tiempoL += punto;
                                                tiempoL += linea.charAt(n+2);
                                                tiempo_Luz = Double.parseDouble(tiempoL);
                                                luz[t] = tiempo_Luz;
                                            }
                                            else if(caracter == ' ' && caracterSiguiente == 'o') break;
                                            else {
                                                tiempoL += caracter;
                                                tiempo_Luz = Double.parseDouble(tiempoL);
                                                luz[t] = tiempo_Luz;
                                            }
                                        
                                        
                                    break;
                                    }
                                else if (caracter == 'o'){
                                    v++;
                                    n += 3;
                                    caracter = linea.charAt(n);
                                            if(caracterSiguiente == ' ' && linea.charAt(n+2) != 'L' ||
                                               caracterSiguiente == ' ' && linea.charAt(n+2) != '|'){
                                                tiempoO += caracter;
                                                tiempoO += punto;
                                                tiempoO += linea.charAt(n+2);
                                                tiempo_Ocultacion = Double.parseDouble(tiempoO);
                                                ocultaciones[v] = tiempo_Ocultacion;

                                            }
                                            else if(caracter == ' ' && caracterSiguiente == 'L' || 
                                                    caracter == ' ' && caracterSiguiente == '|' ) break;
                                            else {
                                                tiempoO += caracter;
                                                tiempo_Ocultacion = Double.parseDouble(tiempoO);
                                                ocultaciones[v] = tiempo_Ocultacion;
                                            }
                                      
                                      break; 
                                }
                                else break;
                            case 11:
                                if(caracter == ' ') break;
                                else tiempoTotal += caracter;
                            case 12:
                            case 13:
                                break;
                            //case 13: break; (No sirve porque nunca entraria
                        }   
                    }
                    n++;
                    System.out.println(linea + " /// " + nombre_faro + ":"+ " " + longitud +" - "+ latitud + " - " +  coord_X + " - " +  coord_Y + " - " +  tiempo_Luz + " - " +  tiempo_Ocultacion + " - " + repeticiones + " - " + color + " - " + tiempoTotal);
                }
                System.out.println("Estas son los tiempos de Luz:");
                System.out.println(luz[0]);
                System.out.println("Estas son los tiempos de Ocultacion:");
                System.out.println(ocultaciones[0]);
                Faro f = new Faro(nombre_faro, longitud, latitud, coord_X, coord_Y, repeticiones, color, ocultaciones, luz, Double.parseDouble(tiempoTotal));
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
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista;
    }
}
