package client;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		Client cliente = new Client();

		System.out.println("El cliente se ha conectado");

		cliente.iniciarClient();

	}

}
