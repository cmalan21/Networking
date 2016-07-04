package ClientMain;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	private static String serverName;
	private static int portNumber;
	private static BufferedReader inputStream;
	private static DataOutputStream outputStream;
	private static Scanner in;

	public ClientMain() {
		in = new Scanner(System.in);
		System.out.println("Enter Server name");
		serverName = in.nextLine().toString();
		System.out.println("Enter port Number");
		portNumber = in.nextInt();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		ClientMain newClient = new ClientMain();
		
		try{
			System.out.println("Trying to connect to server " + serverName +  "on port" + portNumber );
			Socket clientSocket = new Socket(serverName, portNumber);
			System.out.println("Connected to:" + clientSocket.getRemoteSocketAddress());
			inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println(inputStream.readLine());
			
		}catch(IOException ex) {
			System.err.println("Could not connect to server.");
			ex.printStackTrace();
		}
		
		System.out.println("Chat:");
		while(true) {
			String sentence = in.nextLine();
			outputStream.writeBytes(sentence + '\n');
		}
	}

}

