package server;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		Server servidor = new Server();
		System.out.println("Iniciando el servidor");

		servidor.iniciarServer();
		servidor.finalizaServer();
	}

}
