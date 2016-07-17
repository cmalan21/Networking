package ClientMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


public class ClientInterface extends JFrame {
	
	private final int WIDTH = 600;
	private final int HEIGHT = 300;
	
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
		JPanel statusPanel = new JPanel();
		JPanel chatPanel = new JPanel();
		
		JLabel serverLabel = new JLabel("Server IP Address");
		JLabel portLabel = new JLabel("Port");
		JLabel usernameLabel = new JLabel("Username");
		JLabel userStatus = new JLabel();
		
		JTextField serverInput = new JTextField(20);
		JTextField portInput = new JTextField();
		JTextField usernameInput = new JTextField();
		
		JTextArea chatArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(chatArea);
		
		GridLayout mainLayout = new GridLayout(3, 1);
		GridLayout loginLayout = new GridLayout(2,3);
		GridLayout chatLayout = new GridLayout(1, 0);
		
		ImageIcon icon = new ImageIcon("Resources\\offline.png"); 
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon newImageIcon = new ImageIcon(newImage);
		userStatus.setIcon(newImageIcon);
		statusPanel.setOpaque(true);
		statusPanel.setBackground(Color.WHITE);
		
		
		mainPanel.setLayout(mainLayout);
		loginpanel.setLayout(loginLayout);
		chatPanel.setLayout(chatLayout);
		
		loginpanel.add(serverLabel);
		loginpanel.add(portLabel);
		loginpanel.add(usernameLabel);
		loginpanel.add(serverInput);
		loginpanel.add(portInput);
		loginpanel.add(usernameInput);
		
		statusPanel.add(userStatus);
		
		chatPanel.add(scrollPane);
		
		mainPanel.add(loginpanel);
		mainPanel.add(statusPanel);
		mainPanel.add(chatPanel);
		
		this.getContentPane().add(mainPanel);
		
	}

}
