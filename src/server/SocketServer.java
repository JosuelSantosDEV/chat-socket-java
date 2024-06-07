package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



import java.net.ServerSocket;
import client.SocketClient;


public class SocketServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		
			
			try {
				 serverSocket = new ServerSocket(SocketClient.PORT_CLIENT);
				 System.out.println("Servidor em execução na porta: "+ SocketClient.PORT_CLIENT);
				 
				 while(true) {
					 
					 ResolveClient resolvCli = new ResolveClient(serverSocket.accept());
					 resolvCli.start();
					 
					 /*
					 Socket socketClient =  serverSocket.accept();
					 System.out.println("Cliente conectado: "+ socketClient.getInetAddress());
					 InputStreamReader inputReaderDataFluxo = new InputStreamReader(socketClient.getInputStream());
					 BufferedReader data = new BufferedReader(inputReaderDataFluxo);
					 System.out.println(data.readLine());
					 
					 // Multi clientes com multi threads
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("Erro na Thread sleep: " + e.getMessage());
					}
					 
					 // Fechando conexão com socker cliente
					 socketClient.close();
					*/
				 }
				 
			} catch (IOException errorOpen) {
				System.out.println("Erro ao instaciar servidor :" + errorOpen.getMessage());
				if(serverSocket != null) {
					try {
						serverSocket.close();
					} catch (IOException errorClose) {
						System.out.println("Erro ao fechar servidor :" + errorClose.getMessage());
					}
				}
			}

	}

}
