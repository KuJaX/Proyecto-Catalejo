import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos {
    
    public ArrayList<Resultado> parseFile(String nombre) {
        FileReader lectorArchivo;
        ArrayList<Resultado> lista = new ArrayList<Resultado>();
        
        try {
            lectorArchivo = new FileReader(new File(nombre));
            BufferedReader br = new BufferedReader(lectorArchivo);
            String linea = br.readLine();

            while(linea != null) {
                System.out.println(linea);
                lista.add(new Resultado(parseFaro(linea)));
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
    
    public ArrayList<Resultado> filtrarPorTiempo(ArrayList<Resultado> res, double tiempo, double margen){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        for (Resultado r : res){
            Faro f = r.getFaro();
            
            if(tiempo-margen<=tiempoTotal(f.getDestellos()) && tiempo+margen>=tiempoTotal(f.getDestellos())){
                r.addIndice(1-Math.abs(tiempo-tiempoTotal(f.getDestellos()))/margen);
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
     public ArrayList<Resultado> filtrarPorRepeticiones(ArrayList<Resultado> res, int repeticiones, double margen){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        for (Resultado r : res){
            Faro f = r.getFaro();
            
            if(repeticiones-margen<=numeroDeRepeticiones(f.getDestellos()) && repeticiones+margen>=numeroDeRepeticiones(f.getDestellos()) ){
                r.addIndice(1-Math.abs(repeticiones-numeroDeRepeticiones(f.getDestellos()))/margen);
                resultados.add(r);
            }
        }
        
        return resultados;
    }
     
     public ArrayList<Resultado> calcularPorcentaje (ArrayList<Resultado> res){
         ArrayList<Resultado> resultados = new ArrayList<Resultado>();
         for (Resultado r : res){
            r.addIndice(indiceDeCorrectitudTotal(r.getIndices()));
            resultados.add(r);
        }
         return resultados;
     }
     
     public double indiceDeCorrectitudTotal (ArrayList<Double> indices){
         double suma = 0;
         for(Double indice:indices)
             suma += indice;

         return suma/indices.size();
     }
    
    private static Faro parseFaro(String linea) {
        Faro faro = new Faro();
        String[] campos = linea.split(" \\| ");
        
        faro.setCoordenadaCartesiana(parseCoordenadaCartesiana(campos[0], campos[1]));
        faro.setCoordenadaRadial(parseCoordenadaRadial(campos[5], campos[6]));
        faro.setColor(campos[7]);
        faro.setNombre(campos[4]);
        faro.setDestellos(parseDestellos(campos[10]));

        return faro;
    }
        
    private static Coordenada<Double> parseCoordenadaCartesiana(String x, String y) {
        Coordenada<Double> coordenada = new Coordenada<Double>();
        coordenada.setX(Double.parseDouble(x));
        coordenada.setY(Double.parseDouble(y));
        
        return coordenada;
    }
    
    private static Coordenada<String> parseCoordenadaRadial(String x, String y) {
        Coordenada<String> coordenada = new Coordenada<String>();
        coordenada.setX(x);
        coordenada.setY(y);
        
        return coordenada;
    }
    
    private static ArrayList<Destello> parseDestellos(String caracteristicas) {
        ArrayList<Destello> destellos = new ArrayList<Destello>();

        while(caracteristicas.length()>0) {
            String caracteristica = parseCaracteristica(caracteristicas);
            destellos.addAll(calculateRepetitions(caracteristica));
            caracteristicas = caracteristicas.replace(caracteristica, "");
        }
        
        return destellos;
    }
    
    private static ArrayList<Destello> calculateRepetitions(String data) {
        ArrayList<Destello> destellos = new ArrayList<Destello>();
        
        if(data.startsWith("["))
            destellos.addAll(repiteDestello(data));
        else
            destellos.add(parseDestello(data));
        
        return destellos;
    }
    
    private static String parseCaracteristica(String caracteristicas) {
        String data = "";
        int n = 0;

        do {
            data += caracteristicas.charAt(n++);
        } while(n<caracteristicas.length() && (caracteristicas.charAt(n)!='L' || n==2));
        
        return data;
    }
    
    private static ArrayList<Destello> repiteDestello(String data) {
        int parentesisInicial = 2;
        int parentesisFinal = data.indexOf(')');
        int repeticiones = Integer.parseInt(data.charAt(parentesisFinal+1)+"");
        String destelloData = data.substring(parentesisInicial, parentesisFinal);
        
        ArrayList<Destello> destellos = new ArrayList<Destello>();
        Destello d = parseDestello(destelloData);
        
        for(int i = repeticiones; i>0; i--)
            destellos.add(d);
        
        return destellos;
    }
    
    private static Destello parseDestello(String data) {
        Destello destello = new Destello();
        String dataEncendido = data.split("oc")[0].substring(2);
        String dataApagado   = data.split("oc")[1];
        
        dataEncendido = dataEncendido.trim().replace(" ", ".");
        dataApagado = dataApagado.trim().replace(" ", ".");
        
        destello.setTiempoEncendido(Double.parseDouble(dataEncendido));
        destello.setTiempoApagado(Double.parseDouble(dataApagado));

        return destello;
    }
    
    public static double tiempoTotal (ArrayList<Destello> destellos){
        double suma = 0;
        for (Destello d : destellos){
            suma += d.getTiempoApagado() + d.getTiempoEncendido();
        }
        return suma;
    }
    
    public static int numeroDeRepeticiones (ArrayList<Destello> destellos){
        int numeroDestellos = destellos.size();
        System.out.println(numeroDestellos);
        return numeroDestellos; 
    }
}
