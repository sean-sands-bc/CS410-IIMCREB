package iimcrebClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class ContactsWindow extends JFrame {
	//ClientController cc;
	
	public ContactsWindow(ClientController cc)
	{
		//this.cc = cc;
		setTitle("Contacts");
		JPanel conPnl = new JPanel();
		
		add(conPnl);
		setPreferredSize(new Dimension(200, 500));
		pack();
		setVisible(true);
	}

}
