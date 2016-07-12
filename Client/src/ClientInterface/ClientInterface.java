package ClientInterface;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ClientInterface extends JFrame{
	
	private final int HEIGHT = 400;
	private final int WDTH = 600;
	
	public ClientInterface() {
		
		Dimension size = new Dimension(WDTH, HEIGHT);
		this.setSize(size);
		this.setTitle("ClientInterface");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}
