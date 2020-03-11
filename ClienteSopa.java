/*import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class ClienteSopa{
    static String HOST = "localhost";
    static int PUERTO = 5000;
	int band=0;
    Socket sc;
    String mensajeRecibido;
 
    public void iniJuego(Juego juego){
        Scanner teclado = new Scanner(System.in);
		int x1, y1, x2, y2;
        try{
            sc = new Socket(HOST, PUERTO);
            //ObjectOutputStream salida = new ObjectOutputStream(sc.getOutputStream());
			DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
            DataInputStream entrada = new DataInputStream(sc.getInputStream());
			String msn = "";
            while(!msn.equals("x")){
				/*salida.writeObject(juego);
				mensajeRecibido = entrada.readUTF();//Leemos respuesta
				System.out.println(mensajeRecibido);
				msn = "Coodenadas incorrectas";//teclado.nextLine();
                System.out.println("Escriba un msn para enviar");
                msn = teclado.nextLine();
                salida.writeUTF(msn);//enviamos mensaje
                mensajeRecibido = entrada.readUTF();//Leemos respuesta
                System.out.println(mensajeRecibido);
            }
             
            sc.close();
        }catch(Exception e){
 
        }
    }
 
    public static void main(String[] args){
		String op1="0", op2="0", op3="0";
		Scanner teclado = new Scanner(System.in);
		//Sopa s=new Sopa();
		Juego juego = new Juego();
		ClienteSopa cs = new ClienteSopa();
		while(!(op1.equals(1)) && !(op1.equals(2))){
			System.out.println("-------------------Sopa de Letras-----------------------------");
			System.out.println("1. Iniciar Juego.");
			System.out.println("2. Salir.");
			System.out.println("Opcion: ");
			op1=teclado.nextLine();
			switch(op1){
				case "1":
					while(!(Integer.parseInt(op2)>0 && Integer.parseInt(op2)<3)){
						System.out.println("-------------------Modo de juego-----------------------------");
						System.out.println("1. Concepto.");
						System.out.println("2. Anagrama.");
						System.out.println("3. Regresar.");
						System.out.println("Opcion: ");
						op2=teclado.nextLine();
						switch(op2){
							case "1":
								System.out.println("Concepto");
								juego.setModo(1);
								while(!(Integer.parseInt(op3)>0 && Integer.parseInt(op3)<6)){
									System.out.println("-------------------Categorias---------------------------");
									System.out.println("1. Animales.");
									System.out.println("2. Paises.");
									System.out.println("3. Generos Musicales.");
									System.out.println("4. Flores.");
									System.out.println("5. Regresar.");
									System.out.println("Opcion: ");
									op3=teclado.nextLine();
									switch(op3){
										case "1":
											System.out.println("Animales");
											juego.setCategoria(1);
											cs.iniJuego(juego);
										break;
										case "2":
											System.out.println("Paises");
											juego.setCategoria(2);
										break;
										case "3":
											System.out.println("Generos");
											juego.setCategoria(3);
										break;
										case "4":
											System.out.println("Flores");
											juego.setCategoria(4);
										break;
										default:
										break;
									}
								}
							break;
							case "2":
								System.out.println("Anagrama");
								juego.setModo(2);
								//anagrama();
							break;
							default:
							break;
						}
					}
				break;
				case "2":
					System.exit(0);
				break;
				default:
				break;
			}
		}
    }
 
}*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class ClienteSopa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int modo,categoria,x1,y1,x2,y2;

        System.out.println("-------------Sopa de letras-----------");
        System.out.println("Modo de Juego");
        System.out.println("1.Concepto");
        System.out.println("2.Anagrama");
        modo = sc.nextInt();
		System.out.println("Categoria");
        System.out.println("1. Animales.");
		System.out.println("2. Paises.");
		System.out.println("3. Generos Musicales.");
		System.out.println("4. Flores.");
        categoria = sc.nextInt();
        Tablero tab = Tiro(-1, -1, -1, -1, modo, categoria);

        System.out.println(tab);
        while (true) {
            System.out.println("Ingrese las coordenadas");
			System.out.println("x1: ");
            x1=sc.nextInt()-1;
			System.out.println("y1: ");
            y1=sc.nextInt()-1;
			System.out.println("x2: ");
            x2=sc.nextInt()-1;
			System.out.println("y2: ");
            y2=sc.nextInt()-1;
            tab = Tiro(x1, y1, x2, y2, modo, categoria);
            System.out.println(tab);
        }
       

    }

    public static Tablero Tiro(int x1, int y1, int x2, int y2, int modo, int categoria) {
        Tablero t = null;
        try {
            // Apertura del socket y los flujos IO
            Socket s = new Socket(InetAddress.getByName("localhost"), 4040);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());

            os.flush();

            Juego j1 = new Juego();

            j1.setModo(modo);
			j1.setCategoria(categoria);
            j1.setX1(x1);
            j1.setY1(y1);
			j1.setX2(x2);
            j1.setY2(y2);
            os.writeObject(j1);
            os.flush();

            t = (Tablero) is.readObject();


            //Cerramos los flujos y el socket
            is.close();
            os.close();
            s.close();
        } catch (UnknownHostException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return t;
    }
}