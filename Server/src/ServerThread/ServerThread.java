package ServerThread;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ServerMain.ServerMain;

public class ServerThread extends Thread{

	private Socket socket;
	private DataOutputStream outputStream;
	private BufferedReader inputStream;
	private ServerMain mainServer;

	public ServerThread(Socket clientSocket, ServerMain server) throws IOException {
		this.mainServer = server;
		this.socket = clientSocket;
		System.out.println("Connected to new client " + socket.getRemoteSocketAddress());
		outputStream = new DataOutputStream(socket.getOutputStream());
		inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}

	public void run() {
		try {
			outputStream.writeBytes("Welcome " + socket.getRemoteSocketAddress() + '.' + '\n');
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				String sentence = inputStream.readLine();
				if(sentence != null && sentence.length() != 0) {
					System.out.println(socket.getRemoteSocketAddress() + " says: " + sentence);
					mainServer.send(sentence);


				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String sentence) throws IOException {
		outputStream.writeBytes(sentence + '\n');
		outputStream.flush();
		
	}
}
