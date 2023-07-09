package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	// Usamos la direccion localhost, puerto 9876 y apertura de socket y escaner
	private final String HOST = "localhost";
	private final int PUERTO = 9876;
	private Socket socket;
	Scanner sc = new Scanner(System.in);

	public Client() throws IOException {
		socket = new Socket(HOST, PUERTO);
	}

	public void iniciarClient() throws IOException {
		// Creo dos variables para el envio de datos de
		// entrada y salida para la comunicacion con el servidor
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

		System.out.println("Conexion realizada");
		// En la pantalla del cliente pedimos el nombre
		System.out.println("Escribe tu nombre");
		String nombre = sc.next();
		// Enviamos el nombre al servidor
		salida.writeUTF(nombre);
		System.out.println("Escribe el numero de tareas que quieres realizar");
		int tarea = sc.nextInt();
		// Mandamos el numero de tareas al servidor
		salida.writeInt(tarea);
// se ejecuta el numero de tareas introduciendo descripcion y estado por cada tarea
		for (int i = 0; i < tarea; i++) {

			System.out.println(entrada.readUTF());
			System.out.println(entrada.readUTF());

			String descripcion = sc.next();
			salida.writeUTF(descripcion);

			System.out.println(entrada.readUTF());
			String estado = sc.next();
			salida.writeUTF(estado);

		}

		// Se cirra el socket, el scanner y los canales de entrada (input) y salida
		// (output)
		System.out.println(entrada.readUTF());
		System.out.println("Proceso finalizado");
		salida.close();
		entrada.close();
		socket.close();
		sc.close();

	}

}
