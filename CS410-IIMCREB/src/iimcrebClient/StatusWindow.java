package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StatusWindow extends JFrame {
	//ClientController cc;
	
	public StatusWindow(ClientController cc, ContactsWindow cw)
	{
		//this.cc = cc;
		setTitle("Status");
		
		JPanel staPnl = new JPanel();
		staPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton staOnBtn = new JButton("Online");
		JButton staBuBtn = new JButton("Busy");
		JButton staOfBtn = new JButton("Offline");
		
		staOnBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cc.setStatus("Online");
				cw.updateStatus();
				dispose();
			}
			
		});
		
		staBuBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cc.setStatus("Busy");
				cw.updateStatus();
				dispose();
			}
			
		});
		
		staOfBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cc.setStatus("Offline");
				cw.updateStatus();
				dispose();
			}
			
		});
		
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