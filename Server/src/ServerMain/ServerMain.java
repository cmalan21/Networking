package ServerMain;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import ServerThread.ServerThread;



public class ServerMain{

	private ServerSocket serverSocket;
	private ArrayList<ServerThread> serverThreads;

	public ServerMain(int portNumber) throws IOException {
		try{
			serverThreads = new ArrayList<ServerThread>();
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Waiting to connect to new client on port " + serverSocket.getLocalPort());

			
		}catch(IOException ex) {
			System.err.println("Could not connect to port.");
			ex.printStackTrace();
		}
		
		while(true) {
			Thread newServerThread = new ServerThread(serverSocket.accept());
			newServerThread.start();
			serverThreads.add((ServerThread) newServerThread);
		}

	}

	public static void main(String[] args) throws IOException {
		ServerMain newServer = new ServerMain(3000);
	}

}
