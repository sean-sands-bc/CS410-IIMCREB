package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class LoginWindow extends JFrame {
	ClientController cc;
	
	public LoginWindow(ClientController cc)
	{
		this.cc = cc;
		setTitle("Login");
		JPanel logPnl = new JPanel();
		logPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel logUsernameLbl = new JLabel("Username");
		JLabel logPasswordLbl = new JLabel("Password");
		
		JTextField logUsernameTxt = new JTextField(10);
		
		JTextField logPasswordTxt = new JTextField(10);
		
		JButton logLogBtn = new JButton("Login");
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.weightx = 0.5;
		
		logPnl.add(logUsernameLbl, c);
		
		c.gridy = 1;
		
		logPnl.add(logPasswordLbl, c);
		
		c.gridx = 1;
		c.gridy = 0;
		
		logPnl.add(logUsernameTxt, c);
		
		c.gridy = 1;
		
		logPnl.add(logPasswordTxt, c);
		
		c.gridy = 2;
		
		logPnl.add(logLogBtn, c);
		
		add(logPnl);
		setPreferredSize(new Dimension(200, 150));
		pack();
		setVisible(true);
	}
}
