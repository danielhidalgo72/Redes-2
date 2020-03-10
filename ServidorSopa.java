/*import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class ServidorSopa{
    static int PUERTO = 5000;
    ServerSocket sc;
    Socket so;
    String mensajeRecibido;
 
    public void initServidor(){
         
        Scanner teclado = new Scanner(System.in);
        try{
            sc = new ServerSocket(PUERTO);
            so = new Socket();
             
            System.out.println("Esperando conexión...");
            so = sc.accept();
            System.out.println("Se conecto uno...");
            ObjectInputStream entrada = new ObjectInputStream(so.getInputStream());
			DataInputStream entrada = new DataInputStream(so.getOutputStream());
            DataOutputStream salida = new DataOutputStream(so.getOutputStream());
            String msn = "";
            while(!msn.equals("x")){
                /*Juego juego=(Juego)entrada.readObject();
				System.out.println(String.valueOf(juego.getModo()));
				salida.writeUTF(String.valueOf(juego.getModo()));
				msn = "Coodenadas incorrectas";//teclado.nextLine();
                mensajeRecibido = entrada.readUTF();//Leemos respuesta
                System.out.println(mensajeRecibido);
                //System.out.println("Escriba un msn para enviar");
                msn = "Coodenadas incorrectas";//teclado.nextLine();
                salida.writeUTF(""+msn);//enviamos mensaje
 
            }
            sc.close();
        }catch(Exception e){
 
        }
    }
 
    public static void main(String[] args){
        ServidorSopa o = new ServidorSopa();
        o.initServidor();
    }
 
}*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tablero juego = null;
        
//         <editor-fold desc="Servidor">
        try {
            // Socket de servidor
            ServerSocket ss = new ServerSocket(4040);
            System.out.println("Servidor escuchando.");
            while (true) {
                // Aceptamos un cliente y abrimos los flujos de IO
                Socket s = ss.accept();
                System.out.println("Cliente conectado");
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                os.flush();

                Jugada j = (Jugada) is.readObject();

                if (j != null) {
                    if (j.getX() < 0 && j.getY() < 0) {
                        System.out.println("Nuevo juego");
                        juego = new Tablero();
                    } else {
                        juego.hazJugada(j.getX1(), j.getY1(), j.getX2(), j.getY2(), j.getModo(), j.getCategoria());
                        if (juego.getEstadoJuego() == -1) {
                            System.out.println("El jugador perdió");
                        }
                    }
                }
                os.writeObject(juego);
                os.flush();
                is.close();
                os.close();
                s.close();
            }

        } catch (ClassNotFoundException ce) {
        } catch (IOException ex) {
        }
        // </editor-fold>
    }
}
