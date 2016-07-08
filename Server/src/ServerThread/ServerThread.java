package ServerThread;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{

	private Socket socket;
	private DataOutputStream outputStream;
	private BufferedReader inputStream;

	public ServerThread(Socket clientSocket) throws IOException {
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
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
