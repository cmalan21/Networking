package ClientMain;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientMain extends Thread {
	private static String serverName;
	private static int portNumber;
	private static BufferedReader inputStream;

	private boolean listeningThread = true;
	private String username;
	private JFrame newIntereface;

	public ClientMain() {
		newIntereface = new ClientInterface(this);
	}
	
	public void startListeningThread() {
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
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new ClientMain();
		    }
		});

			
//		System.out.println("Chat:");
//		while(true) {
//			String sentence = in.nextLine();
//			outputStream.writeBytes(sentence + '\n');
//			outputStream.flush();
//		}
	}

}

