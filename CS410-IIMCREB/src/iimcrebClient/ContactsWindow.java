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
	JButton addFr;
	JButton delFr;
	LinkedList<String> friList;

	
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



		staBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				StatusWindow sw = new StatusWindow(cc,self);
				
			}
			
		});

		addFr = new JButton("add friend");
		addFr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFriend af = new AddFriend(cc);
			}
		});

		delFr = new JButton("refresh");
		delFr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ContactsWindow cw = new ContactsWindow(cc);
				dispose();
				//DeleteFriend df = new DeleteFriend(cc);
			}
		});


		c.gridx = 0;
		c.gridy = 0;
		
		conPnl.add(unLbl, c);
		
		c.gridx = 1;
		
		conPnl.add(staBtn, c);

		c.gridy = 2;

		conPnl.add(delFr, c);

		c.gridy = 3;

		conPnl.add(addFr, c);

		add(conPnl);
	//	add(addFr);
	//	add(delFr);
		System.out.println("aaa");

		if(!cc.getFriends().isEmpty()) {
			System.out.println("ccc");

			try {
				friList = cc.getFriends();
				for (String friend : friList) {
					JButton fr = new JButton(friend);
					fr.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							MessageWindow mw = new MessageWindow(cc, friend);
						}
					});
					c.gridy++;
					conPnl.add(fr);
				}
			} catch (NullPointerException e) {

			}
		}
		setPreferredSize(new Dimension(200, 500));
		pack();
		setVisible(true);
		System.out.println("bbb");
	}
	
	public void updateStatus()
	{
		staBtn.setText(cc.getStatus());
	}

}
