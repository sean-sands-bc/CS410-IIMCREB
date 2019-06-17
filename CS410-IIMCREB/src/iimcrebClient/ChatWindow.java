package iimcrebClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class ChatWindow extends JFrame {


    private JFrame message;
    JTextField send = new JTextField("");//the text going to be sent
    JPanel msgPnl = new JPanel();

    JTextArea chat = new JTextArea();



    ChatWindow(ClientController cc, String friendname) {
        message = new JFrame("IIMCREB Message");
        msgPnl.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.8;
        gc.weighty = 0.9;
        gc.fill = GridBagConstraints.BOTH;

        msgPnl.add(chat,gc);


        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 0.8;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        msgPnl.add(send,gc);

        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cc.sendMsg(send.getText(),friendname);
            send = new JTextField("");
            chat.append(cc.updateLog(friendname));
            }
        });


        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 0.2;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        msgPnl.add(sendBtn,gc);
        message.add(msgPnl);	//	add panel to frame
        message.setPreferredSize(new Dimension(500, 500));	//	preferred frame size
        message.pack();	//	pack frame
        message.setVisible(true);	//	show frame


            try{
            while(true){
                TimeUnit.SECONDS.sleep(15);
                chat.append(cc.updateLog(friendname));
            }
        }catch(InterruptedException ie){
        }



    }
}
