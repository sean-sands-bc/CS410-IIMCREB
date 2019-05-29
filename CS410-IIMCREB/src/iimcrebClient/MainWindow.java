package iimcrebClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame{
	public MainWindow()
	{
		setName("IIMCREB");
		JPanel mainPnl = new JPanel();
		mainPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton mainRegBtn = new JButton("Register");
		
		mainRegBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)	//	chanage backend value and fill fields when clicked
			{
				RegisterWindow rw = new RegisterWindow();
			}
		});
		
		JButton mainLogBtn = new JButton("Login");
		
		mainLogBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)	//	chanage backend value and fill fields when clicked
			{
				LoginWindow lw = new LoginWindow();
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.weightx = 0.5;
		
		mainPnl.add(mainRegBtn,c);
		
		c.gridx = 1;
		
		mainPnl.add(mainLogBtn, c);
		
		add(mainPnl);
		setPreferredSize(new Dimension(200, 100));
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
