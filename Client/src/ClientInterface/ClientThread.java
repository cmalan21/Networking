package ClientInterface;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JFrame;

public class ClientThread extends Thread{
	
	private boolean listeningThread = true;
	private static BufferedReader inputStream;
	private ClientInterface clientInterface;
	
	
	public ClientThread(ClientInterface clientInterface2) {
		// TODO Auto-generated constructor stub
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

}
