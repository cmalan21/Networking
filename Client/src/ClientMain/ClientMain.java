package ClientMain;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain extends Thread {
	private static String serverName;
	private static int portNumber;
	private static BufferedReader inputStream;
	private static DataOutputStream outputStream;
	private static Scanner in;
	private boolean listeningThread = true;
	private String username;

	public ClientMain() {
		in = new Scanner(System.in);
		System.out.println("Enter Username:");
		username = in.nextLine().toString();
		System.out.println("Enter Server name:");
		serverName = in.nextLine().toString();
		System.out.println("Enter port Number:");
		portNumber = in.nextInt();


		try{
			System.out.println("Trying to connect to server " + serverName +  "on port" + portNumber );
			Socket clientSocket = new Socket(serverName, portNumber);
			System.out.println("Connected to:" + clientSocket.getRemoteSocketAddress());

			inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			outputStream.writeBytes(username + '\n');

			System.out.println(inputStream.readLine());

		}catch(IOException ex) {
			System.err.println("Could not connect to server.");
			ex.printStackTrace();
		}

		this.start();
	}

	public void run() {
		while(listeningThread) {
			String sentence = null;
			try {
				sentence = inputStream.readLine();
				if(sentence != null && sentence.length() != 0) {
					String[] received = sentence.split("\\\\");
					if(received[0].equals("c")) {
						System.out.println(received[1]);
					} else {
						System.out.println(received[0] + ": " + received[1]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ClientMain newClient = new ClientMain();
		System.out.println("Chat:");
		while(true) {
			String sentence = in.nextLine();
			outputStream.writeBytes(sentence + '\n');
			outputStream.flush();
		}
	}

}

