package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ErrorWindow extends JFrame {
	
	public ErrorWindow(String message)
	{
		setTitle("Error");
		JPanel errPnl = new JPanel();
		errPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel errMessageLbl = new JLabel(message);
		JButton errOkBtn = new JButton("OK");
		
		errOkBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.weightx = 0.5;
		
		errPnl.add(errMessageLbl, c);
		
		c.gridy = 1;
		
		errPnl.add(errOkBtn, c);
		
		add(errPnl);
		setPreferredSize(new Dimension(200,200));
		pack();
		setVisible(true);
	}

}
