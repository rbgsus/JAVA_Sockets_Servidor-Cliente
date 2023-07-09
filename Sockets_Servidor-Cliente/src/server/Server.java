package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	// Usamos la direccion localhost, elegimos el puerto 9876 y apertura de socket
	private final int PUERTO = 9876;
	private ServerSocket serverSocket;
	private Socket socket;

	public Server() throws IOException {
		serverSocket = new ServerSocket(PUERTO);
		socket = new Socket();

	}

	// Iniciamos el servidor
	public void iniciarServer() throws IOException {
		// Esperando al cliente
		System.out.println("Esperando conexion y nombre del cliente ");
		socket = serverSocket.accept();
		// Creo dos variables para el envio de datos de
		// entrada y salida entre el servidor y los clientes

		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());


		// Se solicita el nombre al cliente, se lee del canal de entrada y se muestra
		// por pantalla
		String nombreCliente = entrada.readUTF();
		System.out.println("El cliente " + nombreCliente + " se ha conectado");
		// Se solicita el numero de tareas, lo guardamos en una variable para mostralo
		// por pantalla
		System.out.println("Esperando numero de tareas a realizar");
		int tarea = entrada.readInt();
		System.out.println("Numero de tareas para realizar: " + tarea);

		// Creamos una lista para ir añadiendo las tareas
		ArrayList<Tarea> listaDeTareas = new ArrayList<>();

		// Realizamos la peticion de atrubutos de cada Tarea, creamos una nueva tarea y
		// la añadimos a la lista
		for (int i = 0; i < tarea; i++) {
			salida.writeUTF("Tarea numero: " + (i + 1));

			salida.writeUTF("Escribe la descripcion de la tarea");
			String descripcion = (entrada.readUTF());

			salida.writeUTF("Escribe el estado de la tarea");
			String estado = entrada.readUTF();

			Tarea t = new Tarea(descripcion, estado);
			listaDeTareas.add(t);

		}

		// Enviamos al cliente el toString de la lista de tarea para mostrarsela todas y
		// tambien la mostramos en el sevidor
		System.out.println("Las tareas pendientes de realizar son: " + listaDeTareas.toString());
		salida.writeUTF("Las tareas son: " + listaDeTareas.toString());

	}

	// Cerramos el servidor (serversocket)
	public void finalizaServer() throws IOException {
		serverSocket.close();
		System.out.println("Fin del programa");
	}

}
