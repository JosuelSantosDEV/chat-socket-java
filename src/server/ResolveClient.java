package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ResolveClient extends Thread{
	
	public Socket cliente;
	public String nameCliente;
	Scanner getDataClient; // Obtem dados do cliente
	PrintStream outputDatas ; // Envia dados ao cliente
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
			
			printMsg("Informe seu nome de Usuário: ");
			getDataClient.nextLine();
			nameCliente = getDataClient.nextLine();
			System.out.println("Name client: " + nameCliente);
			printMsg("Server: Seja bem vindo "+ nameCliente);
			
			users.add(this);
			
			while (getDataClient.hasNextLine()) {
				String msgChegadaCliente = getDataClient.nextLine();
				System.out.println("Client: " + msgChegadaCliente);
				for (ResolveClient rc : users) {
					if(rc.nameCliente != this.nameCliente) rc.printMsg(msgChegadaCliente);
					System.out.println("Users name: " + rc.nameCliente);
				}
				System.out.println("Tamanho lista: " + users.size());
			}

			System.out.println("Cliente Finalizado: " + cliente.getInetAddress() + 
					" da thread (" + this.getId() + ")");

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error na entrada de dados em ResolvClient : "+ e.getMessage());

		}
		
		super.run();
	}

	public void printMsg(String msg) {
		outputDatas.println(msg);
	}
	
	
}