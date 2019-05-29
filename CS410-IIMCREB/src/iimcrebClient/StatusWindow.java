package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class StatusWindow extends JFrame {
	public StatusWindow()
	{
		setTitle("Status");
		
		JPanel staPnl = new JPanel();
		staPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton staOnBtn = new JButton("Online");
		JButton staBuBtn = new JButton("Busy");
		JButton staOfBtn = new JButton("Offline");
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.weightx = 0.5;
		
		staPnl.add(staOnBtn, c);
		
		c.gridy = 1;
		
		staPnl.add(staBuBtn, c);
		
		c.gridy = 2;
		
		staPnl.add(staOfBtn, c);
		
		add(staPnl);	//	add panel to frame
		setPreferredSize(new Dimension(100, 200));	//	preferred frame size
		pack();	//	pack frame
		setVisible(true);	//	show frame
	}
}
