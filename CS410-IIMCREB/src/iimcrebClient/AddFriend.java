package iimcrebClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriend extends JFrame {
    AddFriend(ClientController cc){
        setTitle("Add new friend");
        JPanel addpnl = new JPanel();
        JLabel addfrlable = new JLabel("User name");
        addpnl.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JTextField usernamet = new JTextField(10);
        JButton addfrbut = new JButton("add");

        addfrbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.addFriend(usernamet.getText());
            }
        });

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.5;
        gc.weightx = 0.5;

        addpnl.add(addfrlable, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        addpnl.add(usernamet, gc);

        gc.gridy = 2;

        addpnl.add(addfrbut, gc);


        add(addpnl);
        setPreferredSize(new Dimension(200, 150));
        pack();
        setVisible(true);

    }
}
