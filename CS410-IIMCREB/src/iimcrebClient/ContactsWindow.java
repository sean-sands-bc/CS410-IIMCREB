package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

public class ContactsWindow extends JFrame {
	ClientController cc;
	JButton staBtn;
	JMenuBar menuBar;
	JMenu messageHistory;
	
	public ContactsWindow(ClientController cc)
	{
		this.cc = cc;
		setTitle("Contacts");
		JPanel conPnl = new JPanel();
		ContactsWindow self = this;
		
		conPnl.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel unLbl = new JLabel(cc.getUsername());
		staBtn = new JButton(cc.getStatus());
		
		messageHistory = new JMenu("Messages");
		LinkedList<String> users=cc.getUsers();
		for(String s: users) {
			JMenuItem menuItem = new JMenuItem(s);
			menuItem.addActionListener((e)->{MessageWindow mW= new MessageWindow(cc,s);});
			messageHistory.add(menuItem);
		}
		
		staBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				StatusWindow sw = new StatusWindow(cc,self);
				
			}
			
		});
		
		c.gridx = 0;
		c.gridy = 0;
		
		conPnl.add(unLbl, c);
		
		c.gridx = 1;
		
		conPnl.add(staBtn, c);
		
		menuBar=new JMenuBar();
		menuBar.add(messageHistory);
		setJMenuBar(menuBar);
		
		add(conPnl);
		setPreferredSize(new Dimension(200, 500));
		pack();
		setVisible(true);
	}
	
	public void updateStatus()
	{
		staBtn.setText(cc.getStatus());
	}

}