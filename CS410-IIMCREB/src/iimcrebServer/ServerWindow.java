package iimcrebServer;

import java.awt.Dimension;

import javax.swing.*;

public class ServerWindow extends JFrame {

	public ServerWindow()
	{
		//JPanel servPnl = new JPanel();	
		//add(servPnl);
		setPreferredSize(new Dimension(200, 100));
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
