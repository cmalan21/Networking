package ClientMain;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.*;

import ClientInterface.ClientThread;

public class ClientInterface extends JFrame {
	
	private final int WIDTH = 600;
	private final int HEIGHT = 300;
	private static BufferedReader inputStream;
	private static DataOutputStream outputStream;
	private JTextArea chatArea;
	private JLabel userStatus;
	private GridBagConstraints loginConst;
	private ClientMain client;

	public ClientInterface(ClientMain clientMain) {
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.setTitle("BeetleChat");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.createInterface();
		this.client = clientMain;
	}
	
	public void connectToServer(String server, String username, int port) {
		try{
			System.out.println("Trying to connect to server " + server +  "on port" + port );
			Socket clientSocket = new Socket(server, port);
			System.out.println("Connected to:" + clientSocket.getRemoteSocketAddress());

			inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			outputStream.writeBytes(username + '\n');
			
			updateStatus();
			
			client.startListeningThread();
			
			//ClientThread listeningThread = new ClientThread(this);

			System.out.println(inputStream.readLine());

		}catch(IOException ex) {
			System.err.println("Could not connect to server.");
			ex.printStackTrace();
		}
	}
	
	public void updateStatus() {
		ImageIcon icon = new ImageIcon("Resources\\online.png"); 
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon newImageIcon = new ImageIcon(newImage);
		
		userStatus.setIcon(newImageIcon);
		userStatus.setText("Online");
		
	}
	
	public void updateChatAread() {
		
	}
	
	public void createInterface() {
		
		JPanel mainPanel = new JPanel();
		JPanel loginpanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel chatPanel = new JPanel();
		
		JLabel serverLabel = new JLabel("Server IP Address");
		JLabel portLabel = new JLabel("Port");
		JLabel usernameLabel = new JLabel("Username");
		userStatus = new JLabel();
		JLabel userStatusText = new JLabel("Offline");
		
		final JTextField serverInput = new JTextField(20);
		final JTextField portInput = new JTextField();
		final JTextField usernameInput = new JTextField();
		
		JButton loginButton = new JButton("Login");
		
		chatArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(chatArea);
		
		GridLayout mainLayout = new GridLayout(3, 1);
		GridBagLayout loginLayout = new GridBagLayout();
		GridLayout chatLayout = new GridLayout(1, 0);
		
		ImageIcon icon = new ImageIcon("Resources\\offline.png"); 
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon newImageIcon = new ImageIcon(newImage);
		
		userStatus.setIcon(newImageIcon);
		userStatus.setText("Offline");
		
		mainPanel.setLayout(mainLayout);
		loginpanel.setLayout(loginLayout);
		chatPanel.setLayout(chatLayout);
		
		GridBagConstraints loginConst = new GridBagConstraints();
		loginConst.fill= GridBagConstraints.HORIZONTAL;
		
		loginConst.weightx = 1.0;
		loginConst.gridx = 0;
		loginConst.gridy = 0;
		loginpanel.add(serverLabel, loginConst);
		loginConst.weightx = 1.0;
		loginConst.gridx = 1;
		loginConst.gridy = 0;
		loginpanel.add(portLabel, loginConst);
		loginConst.weightx = 1.0;
		loginConst.gridx = 2;
		loginConst.gridy = 0;
		loginpanel.add(usernameLabel);
		
		loginConst.fill= GridBagConstraints.HORIZONTAL;
		loginConst.weightx = 1.0;
		loginConst.gridx = 0;
		loginConst.gridy = 1;
		loginpanel.add(serverInput, loginConst);
		loginConst.weightx = 1.0;
		loginConst.gridx = 1;
		loginConst.gridy = 1;
		loginpanel.add(portInput, loginConst);
		loginConst.weightx = 1.0;
		loginConst.gridx = 2;
		loginConst.gridy = 1;
		loginpanel.add(usernameInput, loginConst);
		
		loginConst.fill = GridBagConstraints.HORIZONTAL;
		loginConst.gridx = 0;
		loginConst.gridy = 2;
		loginpanel.add(userStatus, loginConst);
		
		loginConst.fill = GridBagConstraints.HORIZONTAL;
		loginConst.gridx = 2;
		loginConst.gridy = 2;
		loginpanel.add(loginButton, loginConst);

		chatPanel.add(scrollPane);

		mainPanel.add(loginpanel);
		mainPanel.add(chatPanel);
		
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String serverName;
				String username;
				int portNumber;
				
				if((serverInput.getText() != null && serverInput.getText().length() != 0) && (portInput.getText() != null && portInput.getText().length() != 0) 
						&& (usernameInput.getText() != null && usernameInput.getText().length() != 0)) {
					
					serverName = serverInput.getText();
					username = usernameInput.getText();
					portNumber = Integer.parseInt(portInput.getText());
					connectToServer(serverName, username, portNumber);
				}
				
			}
		});
		
		
		this.getContentPane().add(mainPanel);	
	}
}
