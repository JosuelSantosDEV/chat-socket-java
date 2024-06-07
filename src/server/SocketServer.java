package server;

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
