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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alejandropoloavila
 */
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

	public String leer(String nombre) {
		FileReader lectorArchivo;

		try {
			lectorArchivo = new FileReader(new File(nombre));
			BufferedReader br = new BufferedReader(lectorArchivo);
			ArrayList<Faro> lista = new ArrayList<Faro>();
			String linea;

			linea = br.readLine();

			while(linea != null) {
				String tipo = "";
				String color = "";
				String segundos = "";
                                String repeticiones = "1";
                                String tiempoTotal = "0";
				int n = 0;
				 
                                System.out.println(linea);
                                
				while(n<linea.length()) {
					char caracter = linea.charAt(n);
					char caracterSiguiente = '-';
                                        
                                        try {
                                            caracterSiguiente = linea.charAt(n+1);
                                        } catch (Exception e) {}

					if(esMayuscula(caracter) || caracter == ' ') {
						switch(caracter) {
							case ' ':
                                                                       tiempoTotal = "";
                                                                       char tiempo;
                                                                       while (true) {
                                                                       	n++;
                                                                       	tiempo = linea.charAt(n);
                                                                       	if (tiempo == 's') {
                                                                       		break;
                                                                       		} 
                                                                       else tiempoTotal += tiempo
                                                                       }
                                                                       break;

							case 'G':
								if(caracterSiguiente=='p') n++;
								else System.out.println("Error, Gp chungo: "+linea+", "+caracter);
								break;

							case 'D':
							case 'C':
							case 'O':
								tipo += caracter;
                                                                if(caracter!='D') {
                                                                    if( (caracter=='O' && caracterSiguiente=='c') || (caracter=='C' && caracterSiguiente=='t')) {
                                                                            tipo += caracterSiguiente;
                                                                            n++;
                                                                    }
                                                                    else {
                                                                            System.out.println("Error, Tipo chungo: "+linea+", "+caracter);
                                                                            break;
                                                                    }
                                                                }

								if(linea.charAt(n+1)=='(') {
                                                                        repeticiones = "";
									char parte;

									while(true) {
										n++;
										parte = linea.charAt(n+1);
										if(parte==')') break;

										repeticiones += parte;
									}
                                                                        
								}
							
								break;

							case 'V':
							case 'A':
							case 'B':
								color += caracter;
								break;

							case 'R':
								if(caracterSiguiente=='p') {
									tipo += caracter;
									tipo += caracterSiguiente;
									n++;
								}
								else color += caracter;

								break;
						}

					}
					else  {
						System.out.println("Error, algo chungo está pasando: "+linea+", "+caracter);
					}

					n++;
					System.out.println(linea+" - "+tipo+":"+repeticiones+" - "+color+" - "+tiempoTotal);
				}

				//lista.add(new Faro(txt, color, repeticiones, segundos));

				linea = br.readLine();
			}
			br.close();
			lectorArchivo.close();

			ObjectOutputStream salida;
			try {
				salida = new ObjectOutputStream(new FileOutputStream("media.obj"));

				for (Faro r : lista) {
					salida.writeObject(r);
				}

				salida.close();
			} catch (IOException ex) {
				Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
			}

			System.out.println("Datos guardados");
			for (Faro r : lista) {
				System.out.println(r);
			}
		} catch (IOException e) {
			System.out.println("Error:" + e.getMessage());
		}
		return null;
	}
}
