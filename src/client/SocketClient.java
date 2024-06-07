package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

	public static final String LOCALHOST = "127.0.0.1";
	public static final Integer PORT_CLIENT = 7000;
	
	public static void main(String[] args) {
		
		try {
			// Socket do cliente (IP, PORTA)
			Socket clientSocket = new Socket(LOCALHOST, PORT_CLIENT);
			
			// Scanner para ler dados de entrada no prompt
			Scanner scannerPrompt = new Scanner(System.in);
			// Coletando dados do servidor
			Scanner conectionData = new Scanner(clientSocket.getInputStream());

			// Fluxo de dados para envio
			PrintStream printStream =  new PrintStream(clientSocket.getOutputStream());
			
			
			System.out.println("Fale oi :)");
			
			String msg = null;
		
			do {
				
				msg =  scannerPrompt.nextLine();
				// mandada a mensagem para o server
				printStream.println(msg);
				// Obtendo resposta do server
				String responseServer = conectionData.nextLine();
				System.out.println(responseServer);
				
				
			}while(msg.length() != 0 || msg != null);
			
			
			//  Fechando conexão com cliente
			clientSocket.close();
			System.out.println("Cliente finalizado");
			
		} catch (UnknownHostException errorHost) {
			System.out.println("O endereço host é invalido: " + errorHost.getMessage());
		} catch (IOException errorPort) {
			System.out.println("A porta está invalida: " + errorPort.getMessage());
		}

	}

}
