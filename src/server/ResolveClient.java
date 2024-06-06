package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ResolveClient extends Thread{
	
	public Socket cliente;

	public ResolveClient(Socket cli) {
		this.cliente = cli;
	}
	
	@Override
	public void run() {
		System.out.println("Cliente conectado com thread (" + this.getId() + 
				") : " + cliente.getInetAddress());
		
		// Obtendo dados do prompt
		Scanner inputPrompt = new Scanner(System.in);
		Scanner getDataClient; // cliente está digitando ou vai digitar

		InputStreamReader fluxoDados;
		
		try {
			getDataClient = new Scanner(cliente.getInputStream());
			PrintStream outputDatas = new PrintStream(cliente.getOutputStream());

			while (getDataClient.hasNextLine()) {
				String msgChegadaCliente = getDataClient.nextLine();
				System.out.println("Client: " + msgChegadaCliente);
				String msgResposta = inputPrompt.nextLine();
				outputDatas.println(msgResposta);
				System.out.println("-----------------------------------");
			}

			System.out.println("Cliente Finalizado: " + cliente.getInetAddress() + 
					" da thread (" + this.getId() + ")");

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error na entrada de dados em ResolvClient : "+ e.getMessage());

		}
		
		super.run();
	}

}
