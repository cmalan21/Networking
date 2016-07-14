package ClientMain;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;


public class ClientInterface extends JFrame {
	
	private final int WIDTH = 600;
	private final int HEIGHT = 400;
	
	public ClientInterface() {
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.setTitle("BeetleChat");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		this.createInterface();
	}
	
	public void createInterface() {
		
		JPanel mainPanel = new JPanel();
		JPanel loginpanel = new JPanel();
		JPanel chatPanel = new JPanel();
		
		JLabel serverLabel = new JLabel("Server IP Address");
		JLabel portLabel = new JLabel("Port");
		JLabel usernameLabel = new JLabel("Username");
		
		GridLayout mainLayout = new GridLayout(2, 1);
		GridLayout loginLayout = new GridLayout(2,4);
		GridLayout chatLayout = new GridLayout(1, 0);
		
		mainPanel.setLayout(mainLayout);
		loginpanel.setLayout(loginLayout);
		chatPanel.setLayout(chatLayout);
		
		loginpanel.add(serverLabel);
		loginpanel.add(portLabel);
		loginpanel.add(usernameLabel);
		
		
		
		mainPanel.add(loginpanel);
		mainPanel.add(chatPanel);
		
		this.getContentPane().add(mainPanel);
		
	}

}
