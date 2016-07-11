package ServerMain;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ServerThread.ServerThread;



public class ServerMain{

	private ServerSocket serverSocket;
	private ArrayList<ServerThread> serverThreads;
	private Map<String, ServerThread> users;

	public ServerMain(int portNumber) throws IOException {
		try{
			serverThreads = new ArrayList<ServerThread>();
			users = new HashMap<String, ServerThread>();
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Waiting to connect to new client on port " + serverSocket.getLocalPort());

			
		}catch(IOException ex) {
			System.err.println("Could not connect to port.");
			ex.printStackTrace();
		}
		
		while(true) {
			Thread newServerThread = new ServerThread(serverSocket.accept(), this);
			newServerThread.start();
			serverThreads.add((ServerThread) newServerThread);
		}

	}
 
	public static void main(String[] args) throws IOException {
		ServerMain newServer = new ServerMain(3000);
	}
	
	public void addUser(String username, Thread user) {
		users.put(username, (ServerThread) user);	
	}
	
	public void connectedNotification(String username) throws IOException {
		if(users.size() != 0) {
			for(Entry<String, ServerThread> user: users.entrySet()) {
				if(!(user.getKey().equals(username))) {
					user.getValue().sendMessage("c\\" + username + " is online.");
				}	
			}
		}
		
	}
	
	public void send(String sentence) throws IOException {
		String[] received = sentence.split("\\\\");
		for(Entry<String, ServerThread> user: users.entrySet()) {
			if(!(user.getKey().equals(received[0]))) {
				user.getValue().sendMessage(sentence);
			}	
		}
	}
}
