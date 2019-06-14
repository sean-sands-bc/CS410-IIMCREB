package iimcrebClient;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageWindow extends JFrame{
	private JFrame message;
	private ClientController cc;
	private String friend;
	
	public MessageWindow(ClientController cc, String friend) {
		message = new JFrame("IIMCREB Messaging with "+friend);
		this.cc=cc;
		this.friend=friend;
		
		JPanel msgPnl = new JPanel();
		msgPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.8;
		c.weighty = 0.9;
		c.fill = GridBagConstraints.BOTH;
		JTextArea chat = new JTextArea();
		chat.setEditable(false);
		
		updateMessages(chat);
		
		msgPnl.add(new JScrollPane(chat),c);
		
		JTextField send = new JTextField();
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.8;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		msgPnl.add(send,c);
		
		JButton sendBtn = new JButton("Send");
		sendBtn.addActionListener((e)->{cc.sendMsg(friend, send.getText()); send.setText(""); updateMessages(chat);});
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.2;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		msgPnl.add(sendBtn,c);
		
		JLabel aliceLbl = new JLabel("Alice");
		
		message.add(msgPnl);	//	add panel to frame
		message.setPreferredSize(new Dimension(500, 500));	//	preferred frame size
		message.pack();	//	pack frame
		message.setVisible(true);	//	show frame
		
		
		//Updates messages every five seconds
		
		Timer timer=new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateMessages(chat);
			}
		});
		timer.start();
		
		message.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	timer.stop();
		    }
		});
		
	}
	
	private void updateMessages(JTextArea chat) {
		String messages=cc.updateLog(friend);
		chat.setText(messages);
	}
//	private void updateMessages(JTextArea chat) {
//		StringBuilder allMessages=new StringBuilder();
//		HashMap<Timestamp, String> messages= cc.updateLog(friend);
//		LinkedList<Timestamp> keys=new LinkedList<Timestamp>();
//		for(Timestamp time: messages.keySet()) {
//			keys.add(time);
//		}
//		keys.sort(Comparator.naturalOrder());
//		for(Timestamp time: keys) {
//			String message=messages.get(time);
//			allMessages.append(message+"\n");
//		}
//		chat.setText(allMessages.toString());
//	}

	
	
}
