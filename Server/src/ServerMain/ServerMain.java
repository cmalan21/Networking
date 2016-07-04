package ServerMain;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import ServerThread.ServerThread;



public class ServerMain{

	private ServerSocket serverSocket;
	private ArrayList<ServerThread> serverThreads;

	public ServerMain(int portNumber) {
		try{
			serverThreads = new ArrayList<ServerThread>();
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Waiting to connect to new client on port " + serverSocket.getLocalPort());
			Thread newServerThread = new ServerThread( serverSocket.accept());
			newServerThread.start();
			serverThreads.add((ServerThread) newServerThread);
		}catch(IOException ex) {
			System.err.println("Could not connect to port.");
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ServerMain newServer = new ServerMain(3000);
	}

}
