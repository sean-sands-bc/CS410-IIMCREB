package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterWindow extends JFrame {
	//ClientController cc;
	
	public RegisterWindow(ClientController cc)
	{
		//this.cc = cc;
		setTitle("Register");
		JPanel regPnl = new JPanel();
		regPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel regUsernameLbl = new JLabel("Username");
		JLabel regPasswordLbl = new JLabel("Password");
		JLabel regEmailLbl=new JLabel("Email");
		
		JTextField regUsernameTxt = new JTextField(20);
		JTextField regPasswordTxt = new JTextField(20);
		JTextField regEmailTxt=new JTextField(20);
		
		JButton regRegBtn = new JButton("Register");
		
		regRegBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("reg button");
				int result = cc.register(regUsernameTxt.getText(), regPasswordTxt.getText(), regEmailTxt.getText());
				System.out.print("reg result ");
				System.out.println(result);
				if(result == 0)
				{
					ErrorWindow ew = new ErrorWindow("successfully registered");
					dispose();
				}
				else if (result==1)
				{
					ErrorWindow ew = new ErrorWindow("username taken");
				}
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.weightx = 0.5;
		
		regPnl.add(regUsernameLbl, c);
		
		c.gridx=0;
		c.gridy = 1;
		regPnl.add(regPasswordLbl, c);
		
		c.gridx = 0;
		c.gridy = 2;
		regPnl.add(regEmailLbl, c);
		
		c.gridx = 1;
		c.gridy = 0;
		regPnl.add(regUsernameTxt, c);

		c.gridx=1;
		c.gridy = 1;
		regPnl.add(regPasswordTxt, c);
		
		c.gridx=1;
		c.gridy = 2;
		regPnl.add(regEmailTxt, c);
		
		c.gridx=1;
		c.gridy = 3;
		regPnl.add(regRegBtn, c);
		
		add(regPnl);
		setPreferredSize(new Dimension(350, 200));
		pack();
		setVisible(true);
	}
}