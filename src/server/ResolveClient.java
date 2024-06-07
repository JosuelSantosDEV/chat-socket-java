package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ResolveClient extends Thread{
	
	private Socket cliente;
	private String nameCliente;
	private Scanner getDataClient; 
	private PrintStream outputDatas ;
	private static final List<ResolveClient> users = new ArrayList<ResolveClient>();
	
	public ResolveClient(Socket cli) {
		this.cliente = cli;
	}
	
	@Override
	public void run() {
		System.out.println("Cliente conectado com thread (" + this.getId() + 
				") : " + cliente.getInetAddress());
		
		
		try {
			
			getDataClient = new Scanner(cliente.getInputStream()); // Obtem dados vindo do cliente
			outputDatas = new PrintStream(cliente.getOutputStream()); // Envia dados ao cliente
			
			// Obtendo nome para usuário e adicionando na lista de Clientes
			printMsg("","Informe seu nome de Usuário: ");
			getDataClient.nextLine();
			nameCliente = getDataClient.nextLine();
			System.out.println("Name client: " + nameCliente);
			printMsg("","Server: Seja bem vindo "+ nameCliente);
			
			users.add(this);
			
			// Ouvindo as entradas dos clientes
			while (getDataClient.hasNextLine()) {
				String msgChegadaCliente = getDataClient.nextLine(); // Coletando a mensagem
				
				// Enviar mensagem para todos os conectados
				
				for (ResolveClient user : users) {
					if(user.nameCliente != this.nameCliente)
						user.printMsg(this.nameCliente,msgChegadaCliente);
					
				}
				
				
			}

			
			cliente.close();

		} catch (IOException e) {
			System.out.println("Error na entrada de dados em ResolvClient : "+ e.getMessage());

		}
		
		super.run();
	}

	// Imprime a mesagem para o cliente
	public void printMsg(String name, String msg) {
		if(msg.length() > 0 && msg != null)
			outputDatas.println(name + ": " + msg);
		else
			outputDatas.println("");
	}
	
	
	
}